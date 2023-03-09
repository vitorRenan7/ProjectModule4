package br.com.totvs.hotel.controller.cliente;

import br.com.totvs.hotel.dto.cliente.ClienteRequestDTO;
import br.com.totvs.hotel.dto.cliente.ClienteResponseDTO;
import br.com.totvs.hotel.service.cliente.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteResponseDTO> buscarClientes() {
        return clienteService.buscarClientes();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDTO buscarCliente(@PathVariable("id") Long id) {
        return clienteService.buscarCliente(id);
    }

    @DeleteMapping("/")
    public ResponseEntity<String> deletarClientes() {
        return clienteService.deletarClientes();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCliente(@PathVariable("id") Long id) {
        return clienteService.deletarCliente(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO criarCliente(@RequestBody ClienteRequestDTO clienteRequestDTO) {
        return clienteService.criarCliente(clienteRequestDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ClienteResponseDTO atualizarCliente(@PathVariable("id") Long id, @RequestBody ClienteRequestDTO clienteRequestDTO) {
        return clienteService.atualizarCliente(id, clienteRequestDTO);
    }

}
