package br.com.totvs.hotel.dto.quarto;

import br.com.totvs.hotel.enumeration.quarto.CategoriaQuarto;
import br.com.totvs.hotel.validation.EnumValue;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuartoRequestDTO {
    @Pattern(regexp = "^\\d{1,3}$", message = "numero precisa ser um inteiro de 1 a 3 digitos")
    @NotBlank(message = "numero não pode ser vazio")
    private String numero;

    @Size(min = 1, max = 200, message = "descricao precisa ter entre 1 e 200 caracteres")
    @NotBlank(message = "descricao não pode ser vazia")
    private String descricao;

    @EnumValue(enumerator = CategoriaQuarto.class, message = "categoria precisa ser uma entre ECONOMICO, SUITE, PRESIDENCIAL")
    @NotBlank(message = "categoria não pode ser nula")
    private String categoria;

    @Pattern(regexp = "^(\\d{1,4}\\.)?(\\d){1,4}$", message = "precoHora precisa ser um decimal de 1 a 4 digitos")
    @NotBlank(message = "precoHora não pode ser vazio")
    private String precoHora;

    @Size(min = 1, max = 10, message = "imagens precisa ter entre 1 e 10 urls")
    @NotEmpty(message = "imagens não pode ser vazia")
    private List<String> imagens;

}
