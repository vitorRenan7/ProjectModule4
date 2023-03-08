package br.com.totvs.hotel.service.recepcionista;

import br.com.totvs.hotel.dto.recepcionista.RecepcionistaRequestDTO;
import br.com.totvs.hotel.dto.recepcionista.RecepcionistaResponseDTO;
import br.com.totvs.hotel.model.endereco.EnderecoModel;
import br.com.totvs.hotel.model.recepcionista.RecepcionistaModel;
import br.com.totvs.hotel.repository.recepcionista.RecepcionistaRepository;
import br.com.totvs.hotel.service.endereco.EnderecoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecepcionistaService {
    @Autowired
    private RecepcionistaRepository recepcionistaRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ModelMapper modelMapper;

    private List<RecepcionistaModel> findAll() {
        return recepcionistaRepository.findAll();
    }

    private RecepcionistaModel findById(Long id) {
        Optional<RecepcionistaModel> optionalRecepcionistaModel = recepcionistaRepository.findById(id);

        if (optionalRecepcionistaModel.isPresent()) {
            return optionalRecepcionistaModel.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recepcionista %d não encontrado!".formatted(id));
    }

    private void deleteAll() {
        recepcionistaRepository.deleteAll();
    }

    private void deleteById(Long id) {
        recepcionistaRepository.deleteById(id);
    }

    private RecepcionistaModel save(RecepcionistaModel recepcionistaModel) {
        return recepcionistaRepository.save(recepcionistaModel);
    }

    public List<RecepcionistaResponseDTO> buscarRecepcionistas() {
        return findAll().stream().map(recepcionistaModel -> modelMapper.map(recepcionistaModel, RecepcionistaResponseDTO.class)).collect(Collectors.toList());
    }

    public RecepcionistaResponseDTO buscarRecepcionista(Long id) {
        return modelMapper.map(findById(id), RecepcionistaResponseDTO.class);
    }

    public ResponseEntity<String> deletarRecepcionistas() {
        deleteAll();
        return ResponseEntity.ok("Recepcionistas deletados com sucesso!");
    }

    public ResponseEntity<String> deletarRecepcionista(Long id) {
        findById(id);
        deleteById(id);
        return ResponseEntity.ok("Recepcionista %d deletado com sucesso!".formatted(id));
    }

    public RecepcionistaResponseDTO cirarRecepcionista(RecepcionistaRequestDTO recepcionistaRequestDTO) {
        RecepcionistaModel recepcionistaModel = modelMapper.map(recepcionistaRequestDTO, RecepcionistaModel.class);
        EnderecoModel enderecoModel = enderecoService.criarEndereco(recepcionistaRequestDTO.getEndereco().getCep());
        modelMapper.map(recepcionistaRequestDTO.getEndereco(), enderecoModel);
        recepcionistaModel.setEndereco(enderecoModel);
        return modelMapper.map(save(recepcionistaModel), RecepcionistaResponseDTO.class);
    }

    public RecepcionistaResponseDTO atualizarRecepcionista(Long id, RecepcionistaRequestDTO recepcionistaRequestDTO) {
        RecepcionistaModel recepcionistaModel = modelMapper.map(recepcionistaRequestDTO, RecepcionistaModel.class);
        modelMapper.map(recepcionistaRequestDTO, recepcionistaModel);

        EnderecoModel enderecoModel = recepcionistaModel.getEndereco();
        modelMapper.map(enderecoService.criarEndereco(recepcionistaRequestDTO.getEndereco().getCep()), enderecoModel);
        modelMapper.map(recepcionistaRequestDTO.getEndereco(), enderecoModel);
        return modelMapper.map(save(recepcionistaModel), RecepcionistaResponseDTO.class);
    }

}
