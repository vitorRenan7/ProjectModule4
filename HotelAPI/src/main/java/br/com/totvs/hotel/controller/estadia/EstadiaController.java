package br.com.totvs.hotel.controller.estadia;

import br.com.totvs.hotel.dto.estadia.EstadiaRequestDTO;
import br.com.totvs.hotel.dto.estadia.EstadiaResponseDTO;
import br.com.totvs.hotel.service.estadia.EstadiaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("estadias")
public class EstadiaController {
    @Autowired
    private EstadiaService estadiaService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<EstadiaResponseDTO> buscarEstadias() {
        return estadiaService.buscarEstadias();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EstadiaResponseDTO buscarEstadia(@PathVariable("id") Long id) {
        return estadiaService.buscarEstadia(id);
    }

    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarEstadias() {
        estadiaService.deletarEstadias();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarEstadia(@PathVariable("id") Long id) {
        estadiaService.deletarEstadia(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public EstadiaResponseDTO criarEstadia(@Valid @RequestBody EstadiaRequestDTO estadiaRequestDTO) {
        return estadiaService.criarEstadia(estadiaRequestDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public EstadiaResponseDTO atualizarEstadia(@PathVariable("id") Long id, @RequestBody EstadiaRequestDTO estadiaRequestDTO) {
        return estadiaService.atualizarEstadia(id, estadiaRequestDTO);
    }

}
