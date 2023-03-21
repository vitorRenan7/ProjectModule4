package br.com.totvs.hotel.dto.estadia;

import br.com.totvs.hotel.enumeration.estadia.AndamentoEstadia;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EstadiaRequestDTO {
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    @Future(message = "inicio precisa ser uma data futura")
    @NotNull(message = "inicio não pode ser nulo")
    private LocalDateTime inicio;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    @Future(message = "fim precisa ser uma data futura")
    @NotNull(message = "fim não pode ser nulo")
    private LocalDateTime fim;

    @NotNull(message = "cliente não pode ser nulo")
    private Long cliente;

    @NotNull(message = "quarto não pode ser nulo")
    private Long quarto;

}
