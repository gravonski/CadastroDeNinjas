package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.Missoes.java.MissoesModel;
import jakarta.persistence.*;
import lombok.*;

//Entity: Transforma uma classe comum em uma entidade no BD
@Entity
@Table(name = "tb_cadastro")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "missoes")
@Getter
@Setter
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(unique = true)
    private String email;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "idade")
    private Integer idade;

    @Column(name = "rank")
    private String rank;

    @ManyToOne
    @JoinColumn(name = "id_missoes")
    private MissoesModel missoes;

}
