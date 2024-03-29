package br.com.totvs.hotel.service;

import br.com.totvs.hotel.dto.recepcionista.RecepcionistaLoginDTO;
import br.com.totvs.hotel.dto.recepcionista.RecepcionistaRequestDTO;
import br.com.totvs.hotel.dto.recepcionista.RecepcionistaResponseDTO;
import br.com.totvs.hotel.model.RecepcionistaModel;
import br.com.totvs.hotel.repository.RecepcionistaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecepcionistaService extends PessoaService {
    @Autowired
    private RecepcionistaRepository recepcionistaRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ModelMapper modelMapper;

    private List<RecepcionistaModel> findAll() {
        return recepcionistaRepository.findAll();
    }

    private RecepcionistaModel findById(Long id) {
        return recepcionistaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "recepcionista com id %d não encontrado".formatted(id)));
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

    public List<RecepcionistaResponseDTO> buscarRecepcionistas(String nome) {
        return recepcionistaRepository.buscarRecepcionistasPorNomeCompleto(nome).stream().map(recepcionistaModel -> modelMapper.map(recepcionistaModel, RecepcionistaResponseDTO.class)).collect(Collectors.toList());
    }

    public RecepcionistaResponseDTO buscarRecepcionista(Long id) {
        return modelMapper.map(findById(id), RecepcionistaResponseDTO.class);
    }

    public RecepcionistaResponseDTO buscarUsuarioSenhaRecepcionista(RecepcionistaLoginDTO recepcionistaLoginDTO) {
        String usuario = recepcionistaLoginDTO.getUsuario();
        String senha = recepcionistaLoginDTO.getSenha();
        RecepcionistaModel recepcionistaModel = recepcionistaRepository.buscarUsuarioSenhaRecepcionista(usuario, senha);

        if (recepcionistaModel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "recepcionista com usuario e senha informados não encontrado");
        }
        return modelMapper.map(recepcionistaModel, RecepcionistaResponseDTO.class);
    }

    public void deletarRecepcionistas() {
        deleteAll();
    }

    public void deletarRecepcionista(Long id) {
        findById(id);
        deleteById(id);
    }

    public RecepcionistaResponseDTO criarRecepcionista(RecepcionistaRequestDTO recepcionistaRequestDTO) {
        RecepcionistaModel recepcionistaModel = super.criarPessoa(RecepcionistaModel.class, recepcionistaRequestDTO);
        RecepcionistaModel recepcionistaExistente = recepcionistaRepository.buscarUsuarioRecepcionista(recepcionistaModel.getUsuario());

        if (recepcionistaExistente != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "o usuario %s já está cadastrado".formatted(recepcionistaRequestDTO.getUsuario()));
        }

        return modelMapper.map(save(recepcionistaModel), RecepcionistaResponseDTO.class);
    }

    public RecepcionistaResponseDTO atualizarRecepcionista(Long id, RecepcionistaRequestDTO recepcionistaRequestDTO) {
        applicationService.validarCampo(recepcionistaRequestDTO, recepcionistaRequestDTO.getUsuario(), "usuario");
        applicationService.validarCampo(recepcionistaRequestDTO, recepcionistaRequestDTO.getSenha(), "senha");
        RecepcionistaModel recepcionistaModel = super.atualizarPessoa(recepcionistaRequestDTO, findById(id));
        return modelMapper.map(save(recepcionistaModel), RecepcionistaResponseDTO.class);
    }

}
