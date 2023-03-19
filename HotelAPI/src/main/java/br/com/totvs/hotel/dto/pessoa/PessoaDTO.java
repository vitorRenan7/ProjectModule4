package br.com.totvs.hotel.dto.pessoa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class PessoaDTO {
    private Long id;
    private String nome;
    private String sobrenome;
    private String rg;
    private String cpf;

}
