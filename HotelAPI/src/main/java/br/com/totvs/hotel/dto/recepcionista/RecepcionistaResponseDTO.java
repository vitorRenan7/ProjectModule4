package br.com.totvs.hotel.dto.recepcionista;

import br.com.totvs.hotel.dto.pessoa.PessoaResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RecepcionistaResponseDTO extends PessoaResponseDTO {
    private String usuario;

}
