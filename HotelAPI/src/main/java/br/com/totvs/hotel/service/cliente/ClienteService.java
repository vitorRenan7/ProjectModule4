package br.com.totvs.hotel.service.cliente;

import br.com.totvs.hotel.dto.cliente.ClienteRequestDTO;
import br.com.totvs.hotel.dto.cliente.ClienteResponseDTO;
import br.com.totvs.hotel.model.cliente.ClienteModel;
import br.com.totvs.hotel.repository.cliente.ClienteRepository;
import br.com.totvs.hotel.service.application.ApplicationService;
import br.com.totvs.hotel.service.endereco.EnderecoService;
import br.com.totvs.hotel.service.pessoa.PessoaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService extends PessoaService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ModelMapper modelMapper;

    public List<ClienteModel> findAll() {
        return clienteRepository.findAll();
    }

    public ClienteModel findById(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private void deleteAll() {
        clienteRepository.deleteAll();
    }

    private void deleteById(Long id) {
        clienteRepository.delete(findById(id));
    }

    private ClienteModel save(ClienteModel clienteModel) {
        return clienteRepository.save(clienteModel);
    }

    public List<ClienteResponseDTO> buscarClientes() {
        return findAll().stream().map(clienteModel -> modelMapper.map(clienteModel, ClienteResponseDTO.class)).collect(Collectors.toList());
    }

    public List<ClienteResponseDTO> buscarClientes(String nome) {
        return clienteRepository.buscarClientesPorNomeCompleto(nome).stream().map(clienteModel -> modelMapper.map(clienteModel, ClienteResponseDTO.class)).collect(Collectors.toList());
    }

    public ClienteResponseDTO buscarCliente(Long id) {
        return modelMapper.map(findById(id), ClienteResponseDTO.class);
    }

    public void deletarClientes() {
        deleteAll();
    }

    public void deletarCliente(Long id) {
        findById(id);
        deleteById(id);
    }

    public ClienteResponseDTO criarCliente(ClienteRequestDTO clienteRequestDTO) {
        ClienteModel clienteModel = super.criarPessoa(ClienteModel.class, clienteRequestDTO);
        return modelMapper.map(save(clienteModel), ClienteResponseDTO.class);
    }

    public ClienteResponseDTO atualizarCliente(Long id, ClienteRequestDTO clienteRequestDTO) {
        applicationService.validarCampo(clienteRequestDTO, clienteRequestDTO.getEmail(), "email");
        ClienteModel clienteModel = super.atualizarPessoa(clienteRequestDTO, findById(id));
        return modelMapper.map(save(clienteModel), ClienteResponseDTO.class);
    }

}
