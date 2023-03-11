package br.com.totvs.hotel.controller.cliente;

import br.com.totvs.hotel.dto.cliente.ClienteRequestDTO;
import br.com.totvs.hotel.dto.cliente.ClienteResponseDTO;
import br.com.totvs.hotel.service.cliente.ClienteService;
import jakarta.validation.Valid;
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarClientes() {
        clienteService.deletarClientes();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable("id") Long id) {
        clienteService.deletarCliente(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO criarCliente(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        return clienteService.criarCliente(clienteRequestDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ClienteResponseDTO atualizarCliente(@PathVariable("id") Long id, @RequestBody ClienteRequestDTO clienteRequestDTO) {
        return clienteService.atualizarCliente(id, clienteRequestDTO);
    }

}
