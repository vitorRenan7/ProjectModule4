package br.com.totvs.hotel.dto.estadia;

import br.com.totvs.hotel.validation.Reserva;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EstadiaRequestDTO {
    @Reserva(pattern = "dd/MM/yyyy HH:mm", message = "inicio deve ser uma data válida no formato dd/MM/yyyy HH:mm")
    @NotBlank(message = "inicio não pode ser vazio")
    private String inicio;

    @Reserva(pattern = "dd/MM/yyyy HH:mm", message = "fim deve ser uma data válida no formato dd/MM/yyyy HH:mm")
    @NotBlank(message = "fim não pode ser vazio")
    private String fim;

    @NotNull(message = "cliente não pode ser nulo")
    @Pattern(regexp = "^\\d{1,8}$", message = "cliente deve ser um id de 1 a 8 digitos")
    private String cliente;

    @NotNull(message = "quarto não pode ser nulo")
    @Pattern(regexp = "^\\d{1,8}$", message = "quarto deve ser um id de 1 a 8 digitos")
    private String quarto;

}
