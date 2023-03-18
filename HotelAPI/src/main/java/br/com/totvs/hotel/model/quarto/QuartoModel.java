package br.com.totvs.hotel.model.quarto;

import br.com.totvs.hotel.enumeration.quarto.CategoriaQuarto;
import br.com.totvs.hotel.enumeration.quarto.SituacaoQuarto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "table_quarto")
public class QuartoModel {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Double precoHora;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaQuarto categoria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SituacaoQuarto situacao;

    @ElementCollection
    @CollectionTable(name = "table_quarto_imagens", joinColumns = @JoinColumn(name = "id_quarto"))
    @Column(name = "url", length = 65535)
    private List<String> imagens;

}
