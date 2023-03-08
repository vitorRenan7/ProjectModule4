package br.com.totvs.hotel.model.recepcionista;

import br.com.totvs.hotel.model.pessoa.PessoaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "table_recepcionista")
public class RecepcionistaModel extends PessoaModel {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Integer salario;

}
