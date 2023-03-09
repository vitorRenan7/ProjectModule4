package br.com.totvs.hotel.dto.cliente;

import br.com.totvs.hotel.dto.endereco.EnderecoResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteResponseDTO {
    private String nome;
    private String sobrenome;
    private String rg;
    private String cpf;
    private LocalDate dataNascimento;
    private EnderecoResponseDTO endereco;

}
