package dev.java10x.CadastroDeNinjas.Ninjas;

import jakarta.persistence.Id;
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

    //Adicionar Ninjas(CREATE)
    @PostMapping("/criar")
    public NinjaModel criarNovoNinja(@RequestBody NinjaModel ninjaModel) {
        return ninjaService.criarNovoNinja(ninjaModel);
    }

    //Mostrar todos os Ninjas(READ)
    @GetMapping("/todos")
    public List<NinjaModel> listarNinjas() {
        return ninjaService.listarNinjas();
    }

    //Mostrar Ninja por ID(READ)
    @GetMapping("/todos/{id}")
    public NinjaModel listarNinjasPorId(@PathVariable Long id) {
        return ninjaService.listarNinjasPorId(id);
    }

    //Alterar dados dos Ninjas(UPTADE)
    @PutMapping("/alterar/{id}")
    public NinjaModel alterarNinjaID(@PathVariable Long id, @RequestBody NinjaModel ninjaRequisicao) {
        return ninjaService.atualizarNinjasPorId(id, ninjaRequisicao);
    }

    //Deletar um Ninja(DELETE)
    @DeleteMapping("/deletar/{id}")
    public void deletarNinjasPorId(@PathVariable Long id) {
        ninjaService.deletarNinjasPorId(id);
    }
}
