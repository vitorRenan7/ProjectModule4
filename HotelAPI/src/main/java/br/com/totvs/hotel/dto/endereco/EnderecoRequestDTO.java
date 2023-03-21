package br.com.totvs.hotel.dto.endereco;

import br.com.totvs.hotel.validation.CEP;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnderecoRequestDTO {
    @CEP(message = "cep informado é inválido")
    @NotBlank(message = "cep não pode ser vazio")
    private String cep;

    @NotNull(message = "numero não pode ser vazio")
    @Range(min = 1, max = 9999, message = "numero informado é inválido")
    private Integer numero;

    @Size(min = 0, max = 200, message = "complemento deve conter até 200 caracteres")
    private String complemento;

}
