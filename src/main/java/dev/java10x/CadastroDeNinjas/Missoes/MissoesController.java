package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    //Injeção de Dependência

    private final MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    //Listagem das Missões
    @GetMapping("/listar")
    public List<MissoesModel> listarMissoes(){
        return missoesService.listarMissoes();
    }

    //Criação das Missões
    @PostMapping("/criar")
    public MissoesModel missoesNinja(@RequestBody MissoesModel missoesModel){
        return missoesService.criarMissoes(missoesModel);
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
