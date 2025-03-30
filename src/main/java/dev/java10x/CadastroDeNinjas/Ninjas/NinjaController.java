package dev.java10x.CadastroDeNinjas.Ninjas;

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
    public NinjaDTO criarNovoNinja(@RequestBody NinjaDTO ninja) {
        return ninjaService.criarNovoNinja(ninja);
    }

    //Mostrar todos os Ninjas(READ)
    @GetMapping("/todos")
    public List<NinjaDTO> listarNinjas() {
        return ninjaService.listarNinjas();
    }

    //Mostrar Ninja por ID(READ)
    @GetMapping("/todos/{id}")
    public NinjaDTO listarNinjasPorId(@PathVariable Long id) {
        return ninjaService.listarNinjasPorId(id);
    }

    //Alterar dados dos Ninjas(UPTADE)
    @PutMapping("/alterar/{id}")
    public NinjaDTO alterarNinjaID(@PathVariable Long id, @RequestBody NinjaDTO ninjaRequisicao) {
        return ninjaService.atualizarNinjasPorId(id, ninjaRequisicao);
    }

    //Deletar um Ninja(DELETE)
    @DeleteMapping("/deletar/{id}")
    public void deletarNinjasPorId(@PathVariable Long id) {
        ninjaService.deletarNinjasPorId(id);
    }
}
