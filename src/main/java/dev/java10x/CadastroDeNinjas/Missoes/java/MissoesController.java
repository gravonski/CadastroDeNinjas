package dev.java10x.CadastroDeNinjas.Missoes.java;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private final MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @PostMapping("/criar")
    public ResponseEntity<String> missoesNinja(@RequestBody MissoesDTO missoes){
        MissoesDTO novaMissao = missoesService.criarMissoes(missoes);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missão criada com sucesso!");
    }

    @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> listarMissoes() {
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

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

    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alteraMissoes(@PathVariable Long id, @RequestBody MissoesDTO missoes){
        MissoesDTO missaoAlterada = missoesService.atualizarMissoes(id, missoes);
        if(missaoAlterada != null){
            return ResponseEntity.ok("Missão alterada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi possível alterar a Missão com ID " + id);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissoes(@PathVariable Long id) {
        if (missoesService.buscarMissoesPorId(id) != null) {
            missoesService.deletarMissoes(id);
            return ResponseEntity.ok("Missão deletada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com ID " + id + " não encontrado");
        }
    }
}
