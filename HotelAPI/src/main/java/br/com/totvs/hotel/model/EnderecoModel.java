package br.com.totvs.hotel.model;

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
@Table(name = "table_endereco")
public class EnderecoModel {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = true)
    private String complemento;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String localidade;

    @Column(nullable = false)
    private String uf;

    @Column(nullable = false)
    private String ibge;

    @Column(nullable = false)
    private String gia;

    @Column(nullable = false)
    private String ddd;

    @Column(nullable = false)
    private String siafi;

}
