package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    //Listagem das Missões
    @GetMapping("/listar")
    public String listarMissoes() {
        return "Listando todas as Missões";
    }

    //Criação das Missões
    @PostMapping("/criar")
    public String criarMissao() {
        return "Missões do ninja criadas";
    }

    //Alteração das Missões
    @PutMapping("/alterar")
    public String alteraMissoes(){
        return "Missões alteradas";
    }

    //Deletar Missões
    @DeleteMapping("/deletar")
    public String deletaMissoes(){
        return "Missões deletadas";
    }
}
