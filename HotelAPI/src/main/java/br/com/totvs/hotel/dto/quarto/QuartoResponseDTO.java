package br.com.totvs.hotel.dto.quarto;

import br.com.totvs.hotel.dto.estadia.EstadiaQuartoDTO;
import br.com.totvs.hotel.enumeration.quarto.CategoriaQuarto;
import br.com.totvs.hotel.enumeration.quarto.SituacaoQuarto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuartoResponseDTO extends QuartoDTO {
    private List<String> imagens;
    private List<EstadiaQuartoDTO> estadias;

}
