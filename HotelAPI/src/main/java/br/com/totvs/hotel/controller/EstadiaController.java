package br.com.totvs.hotel.controller;

import br.com.totvs.hotel.dto.estadia.EstadiaRequestDTO;
import br.com.totvs.hotel.dto.estadia.EstadiaResponseDTO;
import br.com.totvs.hotel.service.EstadiaService;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @GetMapping("/filtro")
    @ResponseStatus(HttpStatus.OK)
    public List<EstadiaResponseDTO> buscarEstadias(
            @RequestParam(value = "inicio", required = true)
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
            @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
            LocalDateTime dataInicio,
            @RequestParam(value = "fim", required = false)
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
            @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
            LocalDateTime dataFim
    ) {
        return estadiaService.buscarEstadias(dataInicio, dataFim);
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
