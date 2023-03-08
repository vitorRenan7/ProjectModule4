package br.com.totvs.hotel.dto.recepcionista;

import br.com.totvs.hotel.dto.pessoa.PessoaRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RecepcionistaRequestDTO extends PessoaRequestDTO {
    private Integer salario;

}
