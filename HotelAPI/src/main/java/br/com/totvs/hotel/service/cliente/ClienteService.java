package br.com.totvs.hotel.service.cliente;

import br.com.totvs.hotel.dto.cliente.ClienteRequestDTO;
import br.com.totvs.hotel.dto.cliente.ClienteResponseDTO;
import br.com.totvs.hotel.model.cliente.ClienteModel;
import br.com.totvs.hotel.model.endereco.EnderecoModel;
import br.com.totvs.hotel.repository.cliente.ClienteRepository;
import br.com.totvs.hotel.service.endereco.EnderecoService;
import org.modelmapper.Conditions;
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
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ModelMapper modelMapper;

    private List<ClienteModel> findAll() {
        return clienteRepository.findAll();
    }

    private ClienteModel findById(Long id) {
        Optional<ClienteModel> clienteModelOptional = clienteRepository.findById(id);

        if (clienteModelOptional.isPresent()) {
            return clienteModelOptional.get();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente %d não encontrado!".formatted(id));
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

    public ClienteResponseDTO buscarCliente(Long id) {
        return modelMapper.map(findById(id), ClienteResponseDTO.class);
    }

    public ResponseEntity<String> deletarClientes() {
        deleteAll();
        return ResponseEntity.ok("Clientes deletados com sucesso!");
    }

    public ResponseEntity<String> deletarCliente(Long id) {
        deleteById(id);
        return ResponseEntity.ok("Cliente %d deletado com sucesso!".formatted(id));
    }

    public ClienteResponseDTO criarCliente(ClienteRequestDTO clienteRequestDTO) {
        ClienteModel clienteModel = modelMapper.map(clienteRequestDTO, ClienteModel.class);
        EnderecoModel enderecoModel = enderecoService.criarEndereco(clienteRequestDTO.getCep());
        clienteModel.setEndereco(enderecoModel);
        return modelMapper.map(save(clienteModel), ClienteResponseDTO.class);
    }

    public ClienteResponseDTO atualizarCliente(Long id, ClienteRequestDTO clienteRequestDTO) {
        ClienteModel clienteModel = findById(id);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(clienteRequestDTO, clienteModel);

        EnderecoModel enderecoModel = clienteModel.getEndereco();
        modelMapper.map(enderecoService.criarEndereco(clienteRequestDTO.getCep()), enderecoModel);
        enderecoModel.setComplemento(clienteRequestDTO.getComplemento());

        return modelMapper.map(save(clienteModel), ClienteResponseDTO.class);
    }

}
