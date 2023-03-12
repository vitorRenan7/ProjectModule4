package br.com.totvs.hotel.model.estadia;

import br.com.totvs.hotel.model.cliente.ClienteModel;
import br.com.totvs.hotel.model.quarto.QuartoModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "table_estadia")
public class EstadiaModel {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private LocalDateTime inicio;

    @Column(nullable = false)
    private LocalDateTime fim;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_cliente", nullable = false)
    private ClienteModel cliente;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_quarto", nullable = false)
    private QuartoModel quarto;

}
