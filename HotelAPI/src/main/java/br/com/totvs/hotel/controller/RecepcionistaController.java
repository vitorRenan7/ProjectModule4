package br.com.totvs.hotel.controller;

import br.com.totvs.hotel.dto.recepcionista.RecepcionistaRequestDTO;
import br.com.totvs.hotel.dto.recepcionista.RecepcionistaResponseDTO;
import br.com.totvs.hotel.service.RecepcionistaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("recepcionistas")
public class RecepcionistaController {
    @Autowired
    private RecepcionistaService recepcionistaService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RecepcionistaResponseDTO> buscarRecepcionistas() {
        return recepcionistaService.buscarRecepcionistas();
    }

    @GetMapping("/filtro")
    @ResponseStatus(HttpStatus.OK)
    public List<RecepcionistaResponseDTO> buscarRecepcionistas(@RequestParam("nome") String nome) {
        return recepcionistaService.buscarRecepcionistas(nome);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RecepcionistaResponseDTO buscarRecepcionista(@PathVariable("id") Long id) {
        return recepcionistaService.buscarRecepcionista(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarRecepcionistas() {
        recepcionistaService.deletarRecepcionistas();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarRecepcionista(@PathVariable("id") Long id) {
        recepcionistaService.deletarRecepcionista(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecepcionistaResponseDTO criarRecepcionista(@Valid @RequestBody RecepcionistaRequestDTO recepcionistaRequestDTO) {
        return recepcionistaService.criarRecepcionista(recepcionistaRequestDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RecepcionistaResponseDTO atualizarRecepcionista(@PathVariable("id") Long id, @RequestBody RecepcionistaRequestDTO recepcionistaRequestDTO) {
        return recepcionistaService.atualizarRecepcionista(id, recepcionistaRequestDTO);
    }

}
