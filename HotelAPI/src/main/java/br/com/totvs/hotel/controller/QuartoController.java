package br.com.totvs.hotel.controller;

import br.com.totvs.hotel.dto.quarto.QuartoRequestDTO;
import br.com.totvs.hotel.dto.quarto.QuartoResponseDTO;
import br.com.totvs.hotel.enumeration.quarto.CategoriaQuarto;
import br.com.totvs.hotel.enumeration.quarto.SituacaoQuarto;
import br.com.totvs.hotel.service.QuartoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quartos")
public class QuartoController {
    @Autowired
    private QuartoService quartoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<QuartoResponseDTO> buscarQuartos() {
        return quartoService.buscarQuartos();
    }

    @GetMapping("/filtro")
    @ResponseStatus(HttpStatus.OK)
    public List<QuartoResponseDTO> buscarQuartos(
            @RequestParam(value = "categoria", required = false)
            CategoriaQuarto categoria,

            @RequestParam(value = "situacao", required = false)
            SituacaoQuarto situacao) {
        return quartoService.buscarQuartos(categoria, situacao);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public QuartoResponseDTO buscarQuarto(@PathVariable("id") Long id) {
        return quartoService.buscarQuarto(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarQuartos() {
        quartoService.deletarQuartos();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarQuarto(@PathVariable("id") Long id) {
        quartoService.deletarQuarto(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuartoResponseDTO criarQuarto(@Valid @RequestBody QuartoRequestDTO quartoRequestDTO) {
        return quartoService.criarQuarto(quartoRequestDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public QuartoResponseDTO atualizarQuarto(@PathVariable("id") Long id, @RequestBody QuartoRequestDTO quartoRequestDTO) {
        return quartoService.atualizarQuarto(id, quartoRequestDTO);
    }

}
