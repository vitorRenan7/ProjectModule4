package br.com.totvs.hotel.dto.estadia;

import br.com.totvs.hotel.dto.cliente.ClienteResponseDTO;
import br.com.totvs.hotel.dto.quarto.QuartoResponseDTO;
import br.com.totvs.hotel.enumeration.estadia.AndamentoEstadia;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EstadiaResponseDTO {
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime inicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime fim;

    private AndamentoEstadia andamento;

    private ClienteResponseDTO cliente;
    private QuartoResponseDTO quarto;

}
