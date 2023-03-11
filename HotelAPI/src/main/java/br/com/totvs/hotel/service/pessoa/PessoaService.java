package br.com.totvs.hotel.service.pessoa;

import br.com.totvs.hotel.dto.pessoa.PessoaRequestDTO;
import br.com.totvs.hotel.model.endereco.EnderecoModel;
import br.com.totvs.hotel.model.pessoa.PessoaModel;
import br.com.totvs.hotel.service.endereco.EnderecoService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
public abstract class PessoaService {
    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private Validator validator;

    @Autowired
    private ModelMapper modelMapper;

    protected void validarCampo(Object objeto, String campo) {
        Set<ConstraintViolation<Object>> violations = validator.validateProperty(objeto, campo);
        if (!violations.isEmpty()) {
            String message = violations.iterator().next().getMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
    }

    public <P extends PessoaModel, D extends PessoaRequestDTO> P criarPessoa(Class<P> tipoPessoa, D pessoaRequestDTO) {
        P pessoaModel = modelMapper.map(pessoaRequestDTO, tipoPessoa);
        EnderecoModel enderecoModel = enderecoService.criarEndereco(pessoaRequestDTO.getEndereco().getCep());
        modelMapper.map(pessoaRequestDTO.getEndereco(), enderecoModel);
        pessoaModel.setEndereco(enderecoModel);
        return pessoaModel;
    }

    public <P extends PessoaModel, D extends PessoaRequestDTO> P atualizarPessoa(D pessoaRequestDTO, P pessoaModel) {
        if (pessoaRequestDTO.getNome() != null) {
            validarCampo(pessoaRequestDTO, "nome");
        }
        if (pessoaRequestDTO.getSobrenome() != null) {
            validarCampo(pessoaRequestDTO, "sobrenome");
        }
        if (pessoaRequestDTO.getRg() != null) {
            validarCampo(pessoaRequestDTO, "rg");
        }
        if (pessoaRequestDTO.getCpf() != null) {
            validarCampo(pessoaRequestDTO, "cpf");
        }
        if (pessoaRequestDTO.getDataNascimento() != null) {
            validarCampo(pessoaRequestDTO, "dataNascimento");
        }
        if (pessoaRequestDTO.getEndereco() != null) {
            validarCampo(pessoaRequestDTO.getEndereco(), "cep");
            validarCampo(pessoaRequestDTO.getEndereco(), "numero");

            if (pessoaRequestDTO.getEndereco().getComplemento() != null) {
                validarCampo(pessoaRequestDTO.getEndereco(), "complemento");
            }
        }

        modelMapper.map(pessoaRequestDTO, pessoaModel);
        modelMapper.map(enderecoService.criarEndereco(pessoaRequestDTO.getEndereco().getCep()), pessoaModel.getEndereco());
        modelMapper.map(pessoaRequestDTO.getEndereco(), pessoaModel.getEndereco());
        return pessoaModel;
    }

}
