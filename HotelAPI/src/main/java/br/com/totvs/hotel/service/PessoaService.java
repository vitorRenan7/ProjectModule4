package br.com.totvs.hotel.service;

import br.com.totvs.hotel.dto.pessoa.PessoaRequestDTO;
import br.com.totvs.hotel.model.EnderecoModel;
import br.com.totvs.hotel.model.PessoaModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class PessoaService {
    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ModelMapper modelMapper;

    public <P extends PessoaModel, D extends PessoaRequestDTO> P criarPessoa(Class<P> tipoPessoa, D pessoaRequestDTO) {
        P pessoaModel = modelMapper.map(pessoaRequestDTO, tipoPessoa);
        EnderecoModel enderecoModel = enderecoService.criarEndereco(pessoaRequestDTO.getEndereco().getCep());
        modelMapper.map(pessoaRequestDTO.getEndereco(), enderecoModel);
        pessoaModel.setEndereco(enderecoModel);
        return pessoaModel;
    }

    public <P extends PessoaModel, D extends PessoaRequestDTO> P atualizarPessoa(D pessoaRequestDTO, P pessoaModel) {
        applicationService.validarCampo(pessoaRequestDTO, pessoaRequestDTO.getNome(), "nome");
        applicationService.validarCampo(pessoaRequestDTO, pessoaRequestDTO.getSobrenome(), "sobrenome");
        applicationService.validarCampo(pessoaRequestDTO, pessoaRequestDTO.getRg(), "rg");
        applicationService.validarCampo(pessoaRequestDTO, pessoaRequestDTO.getCpf(), "cpf");
        applicationService.validarCampo(pessoaRequestDTO, pessoaRequestDTO.getDataNascimento(), "dataNascimento");
        applicationService.validarCampo(pessoaRequestDTO.getEndereco(), pessoaRequestDTO.getEndereco().getCep(), "cep");
        applicationService.validarCampo(pessoaRequestDTO.getEndereco(), pessoaRequestDTO.getEndereco().getNumero(), "numero");
        applicationService.validarCampo(pessoaRequestDTO.getEndereco(), pessoaRequestDTO.getEndereco().getComplemento(), "complemento");

        modelMapper.map(pessoaRequestDTO, pessoaModel);
        modelMapper.map(enderecoService.criarEndereco(pessoaRequestDTO.getEndereco().getCep()), pessoaModel.getEndereco());
        modelMapper.map(pessoaRequestDTO.getEndereco(), pessoaModel.getEndereco());
        return pessoaModel;
    }

}
