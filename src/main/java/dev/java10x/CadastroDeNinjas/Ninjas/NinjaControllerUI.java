package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("ninjas/ui")
public class NinjaControllerUI {

    private final NinjaService ninjaService;

    public NinjaControllerUI(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/todos")
    public String listarNinjas(Model model) {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        model.addAttribute("ninjas", ninjas);
        return "listarNinjas";
    }

    @GetMapping("/todos/{id}")
    public String listarNinjasPorId(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);
        if (ninja != null) {
            model.addAttribute("ninja", ninja);
            return "detalhesNinja";
        } else
            model.addAttribute("mensagem", "Ninja não encontrado!");
            return "listarNinjas";
    }

    @GetMapping("/editar/{id}")
    public String editarNinjaForm(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);
        model.addAttribute("ninja", ninja);
        return "editarNinja";
    }

    @PostMapping("/editar/{id}")
    public String salvarEdicao(@PathVariable Long id, @ModelAttribute NinjaDTO ninjaAtualizado) {
        ninjaService.atualizarNinjasPorId(id, ninjaAtualizado);
        return "redirect:/ninjas/ui/todos";
    }



    @GetMapping("/deletar/{id}")
    public String deletarNinjasPorId(@PathVariable Long id) {
        ninjaService.deletarNinjasPorId(id);
        return "redirect:/ninjas/ui/todos";
    }

}
