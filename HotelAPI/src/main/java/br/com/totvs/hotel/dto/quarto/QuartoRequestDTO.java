package br.com.totvs.hotel.dto.quarto;

import br.com.totvs.hotel.enumeration.quarto.CategoriaQuarto;
import br.com.totvs.hotel.enumeration.quarto.SituacaoQuarto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuartoRequestDTO {
    @Range(min = 1, max = 200, message = "numero precisa estar entre 1 e 200")
    @NotNull(message = "numero não pode ser nulo")
    private Integer numero;

    @NotBlank(message = "descricao não pode ser vazia")
    private String descricao;

    @NotNull(message = "categoria não pode ser nula")
    private CategoriaQuarto categoria;

    @Range(min = 1, max = 99999, message = "precoHora precisa estar entre 1 e 99999")
    @NotNull(message = "precoHora não pode ser nulo")
    private Double precoHora;

    @NotNull(message = "imagens não pode ser nula")
    @NotEmpty(message = "imagens não pode ser vazia")
    private List<String> imagens;

}
