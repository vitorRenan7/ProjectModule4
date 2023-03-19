package br.com.totvs.hotel.dto.estadia;

import br.com.totvs.hotel.dto.quarto.QuartoEstadiaDTO;
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
public class EstadiaClienteDTO extends EstadiaDTO {
    private QuartoEstadiaDTO quarto;

}
