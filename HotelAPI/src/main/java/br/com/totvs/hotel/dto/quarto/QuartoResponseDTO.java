package br.com.totvs.hotel.dto.quarto;

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
public class QuartoResponseDTO {
    private Long id;
    private Integer numero;
    private String descricao;
    private Double precoHora;
    private CategoriaQuarto categoria;
    private SituacaoQuarto situacao;
    private List<String> imagens;

}
