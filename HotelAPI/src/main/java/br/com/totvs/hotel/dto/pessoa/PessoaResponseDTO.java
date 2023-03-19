package br.com.totvs.hotel.dto.pessoa;

import br.com.totvs.hotel.dto.endereco.EnderecoPessoaDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class PessoaResponseDTO extends PessoaDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private EnderecoPessoaDTO endereco;

}
