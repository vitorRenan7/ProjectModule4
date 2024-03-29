package br.com.totvs.hotel.dto.endereco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnderecoPessoaDTO {
    private String cep;
    private String uf;
    private String bairro;
    private String logradouro;
    private Integer numero;
    private String complemento;

}
