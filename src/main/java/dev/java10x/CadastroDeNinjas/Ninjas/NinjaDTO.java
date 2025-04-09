package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.Missoes.java.MissoesModel;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NinjaDTO {

    private Long id;

    private String nome;

    private String email;

    private String imgUrl;

    private int idade;

    private String rank;

    private MissoesModel missoes;

}


