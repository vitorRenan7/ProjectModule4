package br.com.totvs.hotel.dto.pessoa;

import br.com.totvs.hotel.dto.endereco.EnderecoRequestDTO;
import br.com.totvs.hotel.validation.Idade;
import br.com.totvs.hotel.validation.RG;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class PessoaRequestDTO {
    @Size(min = 1, max = 200, message = "nome precisa ter até 200 caracteres")
    @NotBlank(message = "nome não pode ser vazio")
    private String nome;

    @Size(min = 1, max = 200, message = "sobrenome precisa ter até 200 caracteres")
    @NotBlank(message = "sobrenome não pode ser vazio")
    private String sobrenome;

    @RG(message = "rg informado é inválido")
    @NotBlank(message = "rg não pode ser vazio")
    private String rg;

    @CPF(message = "cpf informado é inválido")
    @NotBlank(message = "cpf não pode ser vazio")
    private String cpf;

    @Idade(message = "dataNascimento informada é inválida")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NotNull(message = "dataNascimento não pode ser nula")
    private LocalDate dataNascimento;

    @Valid
    @NotNull(message = "endereco não pode ser nulo")
    private EnderecoRequestDTO endereco;

}
