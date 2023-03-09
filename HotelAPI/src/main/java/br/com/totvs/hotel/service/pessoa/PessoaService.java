package br.com.totvs.hotel.service.pessoa;

import br.com.totvs.hotel.dto.pessoa.PessoaRequestDTO;
import br.com.totvs.hotel.model.endereco.EnderecoModel;
import br.com.totvs.hotel.model.pessoa.PessoaModel;
import br.com.totvs.hotel.service.endereco.EnderecoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class PessoaService {
    @Autowired
    private EnderecoService enderecoService;

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
        modelMapper.map(pessoaRequestDTO, pessoaModel);
        modelMapper.map(enderecoService.criarEndereco(pessoaRequestDTO.getEndereco().getCep()), pessoaModel.getEndereco());
        modelMapper.map(pessoaRequestDTO.getEndereco(), pessoaModel.getEndereco());
        return pessoaModel;
    }

}
