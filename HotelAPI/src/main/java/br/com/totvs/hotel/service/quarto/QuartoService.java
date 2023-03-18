package br.com.totvs.hotel.service.quarto;

import br.com.totvs.hotel.dto.quarto.QuartoRequestDTO;
import br.com.totvs.hotel.dto.quarto.QuartoResponseDTO;
import br.com.totvs.hotel.model.quarto.QuartoModel;
import br.com.totvs.hotel.repository.quarto.QuartoRepository;
import br.com.totvs.hotel.service.application.ApplicationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuartoService {
    @Autowired
    private QuartoRepository quartoRepository;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ModelMapper modelMapper;

    public List<QuartoModel> findAll() {
        return quartoRepository.findAll();
    }

    public QuartoModel findById(Long id) {
        return quartoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private void deleteAll() {
        quartoRepository.deleteAll();
    }

    private void deleteById(Long id) {
        quartoRepository.deleteById(id);
    }

    private QuartoModel save(QuartoModel quartoModel) {
        return quartoRepository.save(quartoModel);
    }

    public List<QuartoResponseDTO> buscarQuartos() {
        return findAll().stream().map(quartoModel -> modelMapper.map(quartoModel, QuartoResponseDTO.class)).collect(Collectors.toList());
    }

    public QuartoResponseDTO buscarQuarto(Long id) {
        return modelMapper.map(findById(id), QuartoResponseDTO.class);
    }

    public void deletarQuartos() {
        deleteAll();
    }

    public void deletarQuarto(Long id) {
        findById(id);
        deleteById(id);
    }

    public QuartoResponseDTO criarQuarto(QuartoRequestDTO quartoRequestDTO) {
        QuartoModel quartoModel = save(modelMapper.map(quartoRequestDTO, QuartoModel.class));
        return modelMapper.map(quartoModel,QuartoResponseDTO.class);
    }

    public QuartoResponseDTO atualizarQuarto(Long id, QuartoRequestDTO quartoRequestDTO) {
        applicationService.validarCampo(quartoRequestDTO, quartoRequestDTO.getNumero(), "numero");
        applicationService.validarCampo(quartoRequestDTO, quartoRequestDTO.getDescricao(), "descricao");
        applicationService.validarCampo(quartoRequestDTO, quartoRequestDTO.getPrecoHora(), "precoHora");
        applicationService.validarCampo(quartoRequestDTO, quartoRequestDTO.getCategoria(), "categoria");
        applicationService.validarCampo(quartoRequestDTO, quartoRequestDTO.getSituacao(), "situacao");
        applicationService.validarCampo(quartoRequestDTO, quartoRequestDTO.getImagens(), "imagens");

        QuartoModel quartoModel = findById(id);

        if (quartoRequestDTO.getImagens() != null) {
            quartoModel.getImagens().clear();
        }

        modelMapper.map(quartoRequestDTO, quartoModel);
        return modelMapper.map(save(quartoModel), QuartoResponseDTO.class);
    }

}
