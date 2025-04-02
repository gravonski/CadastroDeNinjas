package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    //Injeção de dependência
    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Essa é a minha primeira mensagem nessa rota";
    }

    //Criar Ninjas(CREATE)
    @PostMapping("/criar")
    public ResponseEntity<String> criarNovoNinja(@RequestBody NinjaDTO ninja) {
        NinjaDTO novoNinja = ninjaService.criarNovoNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + novoNinja.getNome() + " (ID:" + novoNinja.getId() + ")");

    }

    //Mostrar todos os Ninjas(READ)
    @GetMapping("/todos")
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    //Mostrar Ninja por ID(READ)
    @GetMapping("/todos/{id}")
    public ResponseEntity<?> listarNinjasPorId(@PathVariable Long id) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);
        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com ID " + id + " não encontrado");
        }
    }

    //Alterar dados dos Ninjas(UPTADE)
    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterarNinjaID(@PathVariable Long id, @RequestBody NinjaDTO ninjaRequisicao) {
        NinjaDTO ninjaAlterado = ninjaService.atualizarNinjasPorId(id, ninjaRequisicao);
        if (ninjaAlterado != null) {
            return ResponseEntity.ok("Ninja alterado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi possível alterar o Ninja com ID " + id);
        }

    }

    //Deletar um Ninja(DELETE)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjasPorId(@PathVariable Long id) {
        if (ninjaService.listarNinjasPorId(id) != null) {
            ninjaService.deletarNinjasPorId(id);
            return ResponseEntity.ok("Ninja de id " + id + " deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com ID " + id + " não encontrado");
        }

    }
}
