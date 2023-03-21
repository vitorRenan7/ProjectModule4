package br.com.totvs.hotel.dto.quarto;

import br.com.totvs.hotel.enumeration.quarto.CategoriaQuarto;
import br.com.totvs.hotel.enumeration.quarto.SituacaoQuarto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class QuartoDTO {
    private Long id;
    private Integer numero;
    private String descricao;
    private CategoriaQuarto categoria;
    private Double precoHora;
    private SituacaoQuarto situacao;

}
