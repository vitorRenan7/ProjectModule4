package br.com.totvs.hotel.dto.pessoa;

import br.com.totvs.hotel.dto.endereco.EnderecoRequestDTO;
import br.com.totvs.hotel.validation.Age;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PessoaRequestDTO {
    @NotBlank(message = "nome não pode ser vazio")
    private String nome;

    @NotBlank(message = "sobrenome não pode ser vazio")
    private String sobrenome;

    @NotBlank(message = "rg não pode ser vazio")
    private String rg;

    @CPF(message = "cpf informado é inválido")
    @NotBlank(message = "cpf não pode ser vazio")
    private String cpf;

    @Age(message = "dataNascimento informada é inválida")
    @NotNull(message = "dataNascimento não pode ser nulo")
    private LocalDate dataNascimento;

    @NotNull(message = "endereco não pode ser nulo")
    private EnderecoRequestDTO endereco;

}
