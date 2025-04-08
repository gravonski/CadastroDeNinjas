package dev.java10x.CadastroDeNinjas.Missoes.java;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> missoesNinja(@RequestBody MissoesDTO missoes){
        MissoesDTO novaMissao = missoesService.criarMissoes(missoes);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missão criada com sucesso!");
    }

    //Listagem das Missões
    @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> listarMissoes() {
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    //Listagem de Ninjas por ID
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarNinjasPorId(@PathVariable Long id){
        MissoesDTO missoes = missoesService.buscarMissoesPorId(id);
        if(missoes != null){
            return ResponseEntity.ok(missoes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID " + id + " não encontrada!");
        }
    }

    //Alteração das Missões
    @PutMapping("/alterar")
    public String alteraMissoes(){
        return "Missões alteradas";
    }

    //Deletar Missões
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissoes (@PathVariable Long id){
        MissoesDTO missoes = missoesService.buscarMissoesPorId(id);
        if(missoes != null){
            return ResponseEntity.ok("Missão deletada com sucesso!");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID " + id + " não encontrada!");
        }
    }
}
