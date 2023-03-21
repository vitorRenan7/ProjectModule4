package br.com.totvs.hotel.dto.estadia;

import br.com.totvs.hotel.dto.cliente.ClienteEstadiaDTO;
import br.com.totvs.hotel.dto.quarto.QuartoEstadiaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EstadiaResponseDTO extends EstadiaDTO {
    private ClienteEstadiaDTO cliente;
    private QuartoEstadiaDTO quarto;

}
