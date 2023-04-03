package br.com.totvs.hotel.dto.pessoa;

import br.com.totvs.hotel.dto.endereco.EnderecoRequestDTO;
import br.com.totvs.hotel.validation.annotation.Idade;
import br.com.totvs.hotel.validation.annotation.RG;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class PessoaRequestDTO {
    @Size(min = 1, max = 200, message = "nome precisa ter entre 1 e 200 caracteres")
    @NotBlank(message = "nome não pode ser vazio")
    private String nome;

    @Size(min = 1, max = 200, message = "sobrenome precisa ter entre 1 e 200 caracteres")
    @NotBlank(message = "sobrenome não pode ser vazio")
    private String sobrenome;

    @RG(message = "rg informado é inválido")
    @NotBlank(message = "rg não pode ser vazio")
    private String rg;

    @CPF(message = "cpf informado é inválido")
    @NotBlank(message = "cpf não pode ser vazio")
    private String cpf;

    @Idade(minimum = 1, maximum = 200, message = "dataNascimento deve ser válida no formato dd/MM/yyyy e possuir uma idade entere 1 e 200 anos")
    @NotBlank(message = "dataNascimento não pode ser vazia")
    private String dataNascimento;

    @Valid
    @NotNull(message = "endereco não pode ser nulo")
    private EnderecoRequestDTO endereco;

}
