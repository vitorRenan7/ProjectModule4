package br.com.totvs.hotel.dto.pessoa;

import br.com.totvs.hotel.dto.endereco.EnderecoRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PessoaRequestDTO {
    private String nome;
    private String sobrenome;
    private String rg;
    private String cpf;
    private LocalDate dataNascimento;
    private EnderecoRequestDTO endereco;

}
