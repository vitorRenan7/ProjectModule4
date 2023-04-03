package br.com.totvs.hotel.dto.quarto;

import br.com.totvs.hotel.enumeration.quarto.CategoriaQuarto;
import br.com.totvs.hotel.enumeration.quarto.SituacaoQuarto;
import br.com.totvs.hotel.validation.annotation.EnumValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuartoFiltroDTO {
    @EnumValue(enumerator = CategoriaQuarto.class, message = "categoria precisa ser uma ECONOMICO, SUITE, PRESIDENCIAL")
    @NotBlank(message = "categoria não pode ser vazia")
    private String categoria;

    @EnumValue(enumerator = SituacaoQuarto.class, message = "situacao precisa ser uma DISPONIVEL, RESERVADO, OCUPADO")
    @NotBlank(message = "situacao não pode ser vazia")
    private String situacao;

}
