package br.com.totvs.hotel.dto.estadia;

import br.com.totvs.hotel.validation.annotation.Reserva;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EstadiaFiltroDTO {
    @Reserva(pattern = "dd/MM/yyyy HH:mm", message = "inicio precisa ser uma data válida no formato dd/MM/yyyy HH:mm")
    @NotBlank(message = "inicio não pode ser vazio")
    private String inicio;

    @Reserva(pattern = "dd/MM/yyyy HH:mm", message = "fim precisa ser uma data válida no formato dd/MM/yyyy HH:mm")
    @NotBlank(message = "fim não pode ser vazio")
    private String fim;

}
