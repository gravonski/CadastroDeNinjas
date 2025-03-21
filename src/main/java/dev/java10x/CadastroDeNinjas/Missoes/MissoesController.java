package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    @GetMapping("/listar")
    public String listarMissoes() {
        return "Listando todas as Missões";
    }

    @PostMapping("/criar")
    public String criarMissao() {
        return "Missões do ninja criadas";
    }
    
    @PutMapping("/alterar")
    public String alteraMissoes(){
        return "Missões alteradas";
    }

    @DeleteMapping("/deletar")
    public String deletaMissoes(){
        return "Missões deletadas";
    }
}
