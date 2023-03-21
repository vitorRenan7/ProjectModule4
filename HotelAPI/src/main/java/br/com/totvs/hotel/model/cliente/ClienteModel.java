package br.com.totvs.hotel.model.cliente;

import br.com.totvs.hotel.model.estadia.EstadiaModel;
import br.com.totvs.hotel.model.pessoa.PessoaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "table_cliente")
public class ClienteModel extends PessoaModel {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String celular;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente")
    private List<EstadiaModel> estadias = new ArrayList<EstadiaModel>();

}
