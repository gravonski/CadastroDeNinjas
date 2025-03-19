package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class NinjaController {

    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Essa é a minha primeira mensagem nessa rota";
    }

    //Adicionar Ninjas(CREATE)
    @PostMapping("/criar")
    public String criarNinja() {
        return "Ninja criado";
    }

    //Mostrar Ninja por ID(READ)
    @GetMapping("/todosID")
    public String mostrarNinjasId() {
        return "Mostrando Ninjas por ID";
    }

    //Mostrar todos os Ninjas(READ)
    @GetMapping("/todos")
    public String mostrarNinjas() {
        return "Mostrando Ninjas";
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
