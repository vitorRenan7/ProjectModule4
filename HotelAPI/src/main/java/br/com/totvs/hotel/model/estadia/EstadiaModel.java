package br.com.totvs.hotel.model.estadia;

import br.com.totvs.hotel.enumeration.estadia.AndamentoEstadia;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AndamentoEstadia andamento;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_cliente")
    private ClienteModel cliente;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_quarto")
    private QuartoModel quarto;

}
