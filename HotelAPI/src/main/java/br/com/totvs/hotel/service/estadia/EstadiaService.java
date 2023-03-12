package br.com.totvs.hotel.service.estadia;

import br.com.totvs.hotel.dto.estadia.EstadiaRequestDTO;
import br.com.totvs.hotel.dto.estadia.EstadiaResponseDTO;
import br.com.totvs.hotel.model.cliente.ClienteModel;
import br.com.totvs.hotel.model.estadia.EstadiaModel;
import br.com.totvs.hotel.model.quarto.QuartoModel;
import br.com.totvs.hotel.repository.estadia.EstadiaRepository;
import br.com.totvs.hotel.service.application.ApplicationService;
import br.com.totvs.hotel.service.cliente.ClienteService;
import br.com.totvs.hotel.service.quarto.QuartoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public List<EstadiaResponseDTO> buscarEstadias() {
        return findAll().stream().map(estadiaModel -> modelMapper.map(estadiaModel, EstadiaResponseDTO.class)).collect(Collectors.toList());
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
        ClienteModel clienteModel = clienteService.findById(estadiaRequestDTO.getCliente());
        QuartoModel quartoModel = quartoService.findById(estadiaRequestDTO.getQuarto());
        estadiaModel.setCliente(clienteModel);
        estadiaModel.setQuarto(quartoModel);

        return modelMapper.map(save(estadiaModel), EstadiaResponseDTO.class);
    }

    public EstadiaResponseDTO atualizarEstadia(Long id, EstadiaRequestDTO estadiaRequestDTO) {
        applicationService.validarCampo(estadiaRequestDTO, estadiaRequestDTO.getInicio(), "inicio");
        applicationService.validarCampo(estadiaRequestDTO, estadiaRequestDTO.getFim(), "fim");
        applicationService.validarCampo(estadiaRequestDTO, estadiaRequestDTO.getCliente(), "cliente");
        applicationService.validarCampo(estadiaRequestDTO, estadiaRequestDTO.getQuarto(), "quarto");

        EstadiaModel estadiaModel = findById(id);

        if (estadiaRequestDTO.getCliente() != null) {
            ClienteModel clienteModel = clienteService.findById(estadiaRequestDTO.getCliente());
            estadiaModel.setCliente(clienteModel);
        }

        if (estadiaRequestDTO.getQuarto() != null) {
            QuartoModel quartoModel = quartoService.findById(estadiaRequestDTO.getQuarto());
            estadiaModel.setQuarto(quartoModel);
        }

        modelMapper.map(estadiaRequestDTO, estadiaModel);
        return modelMapper.map(save(estadiaModel), EstadiaResponseDTO.class);
    }

}
