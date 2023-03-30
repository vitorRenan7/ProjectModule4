package br.com.totvs.hotel.service;

import br.com.totvs.hotel.dto.estadia.EstadiaRequestDTO;
import br.com.totvs.hotel.dto.estadia.EstadiaResponseDTO;
import br.com.totvs.hotel.enumeration.estadia.AndamentoEstadia;
import br.com.totvs.hotel.enumeration.quarto.SituacaoQuarto;
import br.com.totvs.hotel.model.ClienteModel;
import br.com.totvs.hotel.model.EstadiaModel;
import br.com.totvs.hotel.model.QuartoModel;
import br.com.totvs.hotel.repository.EstadiaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadiaService {
    @Autowired
    private EstadiaRepository estadiaRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private QuartoService quartoService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ModelMapper modelMapper;

    private List<EstadiaModel> findAll() {
        return estadiaRepository.findAll();
    }

    private EstadiaModel findById(Long id) {
        return estadiaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    private void deleteAll() {
        estadiaRepository.deleteAll();
    }

    private void deleteById(Long id) {
        estadiaRepository.deleteById(id);
    }

    private EstadiaModel save(EstadiaModel estadiaModel) {
        return estadiaRepository.save(estadiaModel);
    }

    private boolean conflitoHorario(LocalDateTime x1, LocalDateTime x2, LocalDateTime y1, LocalDateTime y2) {
        return ((x1.isEqual(y1) || x1.isBefore(y1)) && (x2.isEqual(y1) || x2.isAfter(y1))) ||
                ((x1.isEqual(y2) || x1.isBefore(y2)) && (x2.isEqual(y2) || x2.isAfter(y2))) ||
                ((x1.isEqual(y1) || x1.isAfter(y1)) && (x2.isEqual(y2) || x2.isBefore(y2))) ||
                ((x1.isEqual(y1) || x1.isBefore(y1)) && (x2.isEqual(y2) || x2.isAfter(y2)));
    }

    private void validarCliente(ClienteModel clienteModel, EstadiaModel estadiaModel) {
        for (EstadiaModel estadia : clienteModel.getEstadias()) {
            if (conflitoHorario(estadia.getInicio(), estadia.getFim(), estadiaModel.getInicio(), estadiaModel.getFim()) && !estadia.getId().equals(estadiaModel.getId())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Cliente com ID %d já possui o horário indicado reservado. inicio: %s fim: %s".formatted(clienteModel.getId(), estadia.getInicio(), estadia.getFim()));
            }
        }
    }

    private void validarQuarto(QuartoModel quartoModel, EstadiaModel estadiaModel) {
        for (EstadiaModel estadia : quartoModel.getEstadias()) {
            if (conflitoHorario(estadia.getInicio(), estadia.getFim(), estadiaModel.getInicio(), estadiaModel.getFim()) && !estadia.getId().equals(estadiaModel.getId())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Quarto com ID %d já possui o horário indicado reservado. inicio: %s fim: %s".formatted(quartoModel.getId(), estadia.getInicio(), estadia.getFim()));
            }
        }
    }

    private void validarEstadia(EstadiaModel estadiaModel) {
        if (estadiaModel.getInicio().isAfter(estadiaModel.getFim())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data fim precisa ser posterior à data inicio. inicio: %s fim: %s".formatted(estadiaModel.getInicio(), estadiaModel.getFim()));
        }
        if (Duration.between(estadiaModel.getInicio(), estadiaModel.getFim()).toHours() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estadia precisa ser de, no mínimo, 1 hora. inicio: %s fim: %s".formatted(estadiaModel.getInicio(), estadiaModel.getFim()));
        }
    }

    public List<EstadiaResponseDTO> buscarEstadias() {
        return findAll().stream().map(estadiaModel -> modelMapper.map(estadiaModel, EstadiaResponseDTO.class)).collect(Collectors.toList());
    }

    public List<EstadiaResponseDTO> buscarEstadias(LocalDateTime dataInicio, LocalDateTime dataFim) {
        dataFim = dataFim != null ? dataFim : LocalDateTime.now().plusYears(1);
        return estadiaRepository.buscarEstadiasPorData(dataInicio, dataFim).stream().map(estadiaModel -> modelMapper.map(estadiaModel, EstadiaResponseDTO.class)).collect(Collectors.toList());
    }

    public EstadiaResponseDTO buscarEstadia(Long id) {
        return modelMapper.map(findById(id), EstadiaResponseDTO.class);
    }

    public void deletarEstadias() {
        deleteAll();
    }

    public void deletarEstadia(Long id) {
        findById(id);
        deleteById(id);
    }

    public EstadiaResponseDTO criarEstadia(EstadiaRequestDTO estadiaRequestDTO) {
        EstadiaModel estadiaModel = modelMapper.map(estadiaRequestDTO, EstadiaModel.class);
        ClienteModel clienteModel = clienteService.findById(Long.parseLong(estadiaRequestDTO.getCliente()));
        QuartoModel quartoModel = quartoService.findById(Long.parseLong(estadiaRequestDTO.getQuarto()));

        validarEstadia(estadiaModel);
        validarCliente(clienteModel, estadiaModel);
        validarQuarto(quartoModel, estadiaModel);

        clienteModel.getEstadias().add(estadiaModel);
        estadiaModel.setCliente(clienteModel);

        quartoModel.getEstadias().add(estadiaModel);
        estadiaModel.setQuarto(quartoModel);

        estadiaModel.setAndamento(AndamentoEstadia.RESERVADA);
        quartoModel.setSituacao(SituacaoQuarto.RESERVADO);

        return modelMapper.map(save(estadiaModel), EstadiaResponseDTO.class);
    }

    public EstadiaResponseDTO atualizarEstadia(Long id, EstadiaRequestDTO estadiaRequestDTO) {
        applicationService.validarCampo(estadiaRequestDTO, estadiaRequestDTO.getInicio(), "inicio");
        applicationService.validarCampo(estadiaRequestDTO, estadiaRequestDTO.getFim(), "fim");
        applicationService.validarCampo(estadiaRequestDTO, estadiaRequestDTO.getCliente(), "cliente");
        applicationService.validarCampo(estadiaRequestDTO, estadiaRequestDTO.getQuarto(), "quarto");

        EstadiaModel estadiaModel = findById(id);

        if (estadiaRequestDTO.getCliente() != null) {
            estadiaModel.getCliente().getEstadias().remove(estadiaModel);

            ClienteModel clienteModel = clienteService.findById(Long.parseLong(estadiaRequestDTO.getCliente()));
            clienteModel.getEstadias().add(estadiaModel);
            estadiaModel.setCliente(clienteModel);
        }

        if (estadiaRequestDTO.getQuarto() != null) {
            estadiaModel.getQuarto().getEstadias().remove(estadiaModel);

            QuartoModel quartoModel = quartoService.findById(Long.parseLong(estadiaRequestDTO.getQuarto()));
            quartoModel.getEstadias().add(estadiaModel);
            estadiaModel.setQuarto(quartoModel);
        }

        modelMapper.map(estadiaRequestDTO, estadiaModel);
        validarEstadia(estadiaModel);
        validarCliente(estadiaModel.getCliente(), estadiaModel);
        validarQuarto(estadiaModel.getQuarto(), estadiaModel);
        return modelMapper.map(save(estadiaModel), EstadiaResponseDTO.class);
    }

}
