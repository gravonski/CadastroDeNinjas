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

    //Adicionar Ninjas(CREATE)
    @PostMapping("/criar")
    public String criarNinja() {
        return "Ninja criado";
    }

    //Mostrar todos os Ninjas(READ)
    @GetMapping("/todos")
    public List<NinjaModel> listarNinjas() {
        return ninjaService.listarNinjas();
    }

    //Mostrar Ninja por ID(READ)
    @GetMapping("/todosID")
    public String mostrarNinjasId() {
        return "Mostrando Ninjas por ID";
    }

    //Alterar dados dos Ninjas(UPTADE)
    @PutMapping("/alterarID")
    public String alterarNinjasId() {
        return "Alterando Ninjas por ID";
    }

    //Deletar um Ninja(DELETE)
    @DeleteMapping("/deletarID")
    public String deletarNinjasId() {
        return "Deletando Ninjas por ID";
    }
}
