package br.com.totvs.hotel.dto.cliente;

import br.com.totvs.hotel.dto.pessoa.PessoaRequestDTO;
import br.com.totvs.hotel.validation.Celular;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteRequestDTO extends PessoaRequestDTO {
    @NotBlank(message = "email não pode ser vazio")
    @Email(message = "email informado é inválido")
    private String email;

    @NotBlank(message = "celular não pode ser vazio")
    @Celular(message = "celular informado inválido")
    @Size(min = 8, max = 15, message = "celular precisa ter entre 8 e 15 caracteres")
    private String celular;

}
