package dev.java10x.CadastroDeNinjas.Missoes;

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

    //Criação das Missões
    @PostMapping("/criar")
    public MissoesDTO missoesNinja(@RequestBody MissoesDTO missoes){
        return missoesService.criarMissoes(missoes);
    }

    //Listagem das Missões
    @GetMapping("/listar")
    public List<MissoesDTO> listarMissoes(){
        return missoesService.listarMissoes();
    }

    //Listagem de Ninjas por ID
    @GetMapping("/listar/{id}")
    public MissoesDTO listarNinjasPorId(@PathVariable Long id){
        return missoesService.buscarMissoesPorId(id);
    }

    //Alteração das Missões
    @PutMapping("/alterar")
    public String alteraMissoes(){
        return "Missões alteradas";
    }

    //Deletar Missões
    @DeleteMapping("/deletar/{id}")
    public void deletarMissoes (@PathVariable Long id){
        missoesService.deletarMissoesPorID(id);
    }
}
