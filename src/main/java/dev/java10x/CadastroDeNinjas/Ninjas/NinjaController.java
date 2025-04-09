package dev.java10x.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation (summary = "Mensagem de boas vindas", description = "Essa rota da uma mensagem de boas-vindas para quem acessa ela!")
    public String boasVindas() {
        return "Essa é a minha primeira mensagem nessa rota";
    }

    //Criar Ninjas(CREATE)
    @PostMapping("/criar")
    @Operation (summary = "Criar os Ninjas", description = "Rota que cria um novo ninja e insere no Banco de dados")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "201", description = "Ninja criado com sucesso!"),
            @ApiResponse (responseCode = "400", description = "Não foi possível criar o ninja!")
    })
    public ResponseEntity<String> criarNovoNinja(@RequestBody NinjaDTO ninja) {
        NinjaDTO novoNinja = ninjaService.criarNovoNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + novoNinja.getNome() + " (ID:" + novoNinja.getId() + ")");

    }

    //Mostrar todos os Ninjas(READ)
    @GetMapping("/todos")
    @Operation (summary = "Listar todos Ninjas", description = "Rota que realiza a listagem de todos os ninjas cadastrados")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200", description = "Ninjas listados com sucesso"),
            @ApiResponse (responseCode = "404", description = "Não foi possível encontrar os ninjas :(")
    })
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    //Mostrar Ninja por ID(READ)
    @GetMapping("/todos/{id}")
    @Operation (summary = "Listar um Ninja por Id", description = "Rota que lista um Ninja especifíco a partir do ID próprio")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200", description = "Ninja listado com sucesso"),
            @ApiResponse (responseCode = "404", description = "Não foi possível encontrar o ninja :(")
    })
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
    @Operation (summary = "Alterar um Ninja por Id", description = "Rota que altera um Ninja especifíco a partir do ID próprio")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200", description = "Ninja alterado com sucesso"),
            @ApiResponse (responseCode = "404", description = "Não foi possível listar o ninja :(")
    })
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
