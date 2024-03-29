package br.com.totvs.hotel.service;

import br.com.totvs.hotel.dto.quarto.QuartoFiltroDTO;
import br.com.totvs.hotel.dto.quarto.QuartoRequestDTO;
import br.com.totvs.hotel.dto.quarto.QuartoResponseDTO;
import br.com.totvs.hotel.enumeration.quarto.CategoriaQuarto;
import br.com.totvs.hotel.enumeration.quarto.SituacaoQuarto;
import br.com.totvs.hotel.model.QuartoModel;
import br.com.totvs.hotel.repository.QuartoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

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
        return quartoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "quarto com id %d não encontrado".formatted(id)));
    }

    private void deleteAll() {
        quartoRepository.deleteAll();
    }

    private void deleteById(Long id) {
        quartoRepository.deleteById(id);
    }

    public QuartoModel save(QuartoModel quartoModel) {
        return quartoRepository.save(quartoModel);
    }

    public List<QuartoResponseDTO> buscarQuartos() {
        return findAll().stream().map(quartoModel -> modelMapper.map(quartoModel, QuartoResponseDTO.class)).collect(Collectors.toList());
    }

    public List<QuartoResponseDTO> buscarQuartos(QuartoFiltroDTO quartoFiltroDTO) {
        applicationService.validarCampos(quartoFiltroDTO);

        QuartoModel quartoModel = new QuartoModel();
        modelMapper.map(quartoFiltroDTO, quartoModel);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().
                withIgnoreNullValues().
                withIgnoreCase().
                withMatcher("categoria", exact()).
                withMatcher("situacao", exact());

        Example<QuartoModel> example = Example.of(quartoModel, exampleMatcher);
        return quartoRepository.findAll(example).stream().map(quarto -> modelMapper.map(quarto, QuartoResponseDTO.class)).collect(Collectors.toList());
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
        QuartoModel quartoModel = modelMapper.map(quartoRequestDTO, QuartoModel.class);
        quartoModel.setSituacao(SituacaoQuarto.DISPONIVEL);
        return modelMapper.map(save(quartoModel), QuartoResponseDTO.class);
    }

    public QuartoResponseDTO atualizarQuarto(Long id, QuartoRequestDTO quartoRequestDTO) {
        applicationService.validarCampos(quartoRequestDTO);
        QuartoModel quartoModel = findById(id);

        if (quartoRequestDTO.getImagens() != null) {
            quartoModel.getImagens().clear();
        }

        modelMapper.map(quartoRequestDTO, quartoModel);
        return modelMapper.map(save(quartoModel), QuartoResponseDTO.class);
    }

}
