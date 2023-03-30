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
    @CEP(message = "em endereco, o cep informado é inválido")
    @NotBlank(message = "em endereco, o cep não pode ser vazio")
    private String cep;

    @NotNull(message = "em endereco, o numero não pode ser vazio")
    @Range(min = 1, max = 100000, message = "em endereco, o numero informado é inválido pois deve estar entre 1 e 100000")
    private Integer numero;

    @Size(min = 0, max = 200, message = "em endereco, o complemento deve conter até 200 caracteres")
    private String complemento;

}
