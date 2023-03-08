package br.com.totvs.hotel.dto.cliente;

import br.com.totvs.hotel.dto.pessoa.PessoaRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteRequestDTO extends PessoaRequestDTO {
    private String email;

}
