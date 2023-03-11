package br.com.totvs.hotel.dto.recepcionista;

import br.com.totvs.hotel.dto.pessoa.PessoaRequestDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RecepcionistaRequestDTO extends PessoaRequestDTO {
    @NotNull(message = "salario não pode ser nulo")
    @Range(min = 1, max = 1000000, message = "salario precisa estar entre 1 e 1000000")
    private Integer salario;

}
