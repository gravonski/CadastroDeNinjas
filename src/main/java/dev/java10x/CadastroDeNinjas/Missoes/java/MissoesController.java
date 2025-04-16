package dev.java10x.CadastroDeNinjas.Missoes.java;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Criar uma nova missão", description = "Rota que cria uma nova missão e insere no Banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão criada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Não foi possível criar a missão!")
    })
    public ResponseEntity<String> missoesNinja(
            @Parameter(description = "Dados da missão a ser criada")
            @RequestBody MissoesDTO missoes) {
        MissoesDTO novaMissao = missoesService.criarMissoes(missoes);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missão criada com sucesso!");
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todas as missões", description = "Rota que lista todas as missões cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missões listadas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi possível encontrar as missões :(")
    })
    public ResponseEntity<List<MissoesDTO>> listarMissoes() {
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Listar missão por ID", description = "Rota que lista uma missão específica a partir do ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão listada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi possível encontrar a missão :(")
    })
    public ResponseEntity<?> listarNinjasPorId(
            @Parameter(description = "ID da missão a ser listada")
            @PathVariable Long id) {
        MissoesDTO missoes = missoesService.buscarMissoesPorId(id);
        if(missoes != null){
            return ResponseEntity.ok(missoes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID " + id + " não encontrada!");
        }
    }

    @PutMapping("/alterar/{id}")
    @Operation(summary = "Alterar missão por ID", description = "Rota que altera uma missão específica a partir do ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão alterada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi possível alterar a missão :(")
    })
    public ResponseEntity<String> alteraMissoes(
            @Parameter(description = "ID da missão a ser alterada")
            @PathVariable Long id,
            @Parameter(description = "Dados atualizados da missão")
            @RequestBody MissoesDTO missoes) {
        MissoesDTO missaoAlterada = missoesService.atualizarMissoes(id, missoes);
        if(missaoAlterada != null){
            return ResponseEntity.ok("Missão alterada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi possível alterar a Missão com ID " + id);
        }
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar missão por ID", description = "Rota que deleta uma missão específica a partir do ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi possível encontrar a missão :(")
    })
    public ResponseEntity<String> deletarMissoes(
            @Parameter(description = "ID da missão a ser deletada")
            @PathVariable Long id) {
        if (missoesService.buscarMissoesPorId(id) != null) {
            missoesService.deletarMissoes(id);
            return ResponseEntity.ok("Missão deletada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID " + id + " não encontrada");
        }
    }
}
