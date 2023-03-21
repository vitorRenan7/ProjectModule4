package br.com.totvs.hotel.dto.recepcionista;

import br.com.totvs.hotel.dto.pessoa.PessoaRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RecepcionistaRequestDTO extends PessoaRequestDTO {
    @NotBlank(message = "usuario não pode ser vazio")
    @Size(min = 3, max = 30, message = "usuario precisar ter entre 3 e 30 caracteres")
    private String usuario;

    @NotBlank(message = "senha não pode ser vazia")
    @Size(min = 8, max = 30, message = "senha precisa ter entre 8 e 30 caracteres")
    private String senha;

}
