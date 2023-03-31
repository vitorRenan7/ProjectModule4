package br.com.totvs.hotel.dto.endereco;

import br.com.totvs.hotel.validation.CEP;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnderecoRequestDTO {
    @CEP(message = "em endereco, o cep informado é inválido")
    @NotBlank(message = "em endereco, o cep não pode ser vazio")
    private String cep;

    @Pattern(regexp = "^\\d{1,4}$", message = "em endereco, o numero precisa ser um inteiro de 1 a 4 digitos ")
    @NotBlank(message = "em endereco, o numero não pode ser vazio")
    private String numero;

    @Size(min = 0, max = 200, message = "em endereco, o complemento deve conter até 200 caracteres")
    private String complemento;

}
