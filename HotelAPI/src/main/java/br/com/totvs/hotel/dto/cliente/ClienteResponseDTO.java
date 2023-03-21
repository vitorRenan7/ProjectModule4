package br.com.totvs.hotel.dto.cliente;

import br.com.totvs.hotel.dto.estadia.EstadiaClienteDTO;
import br.com.totvs.hotel.dto.pessoa.PessoaResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteResponseDTO extends PessoaResponseDTO {
    private String email;
    private String celular;

    private List<EstadiaClienteDTO> estadias;

}
