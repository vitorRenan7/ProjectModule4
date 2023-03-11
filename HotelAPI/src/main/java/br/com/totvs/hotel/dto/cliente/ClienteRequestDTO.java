package br.com.totvs.hotel.dto.cliente;

import br.com.totvs.hotel.dto.pessoa.PessoaRequestDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteRequestDTO extends PessoaRequestDTO {
    @Email(message = "email informado é inválido")
    @NotBlank(message = "email não pode ser vazio")
    private String email;

}
