package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.Missoes.java.MissoesDTO;
import dev.java10x.CadastroDeNinjas.Missoes.java.MissoesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("ninjas/ui")
public class NinjaControllerUI {

    private final NinjaService ninjaService;
    private final MissoesService missoesService;

    public NinjaControllerUI(NinjaService ninjaService, MissoesService missoesService) {
        this.ninjaService = ninjaService;
        this.missoesService = missoesService;
    }

    @PostMapping("/criar")
    public String criarNovoNinja(@ModelAttribute NinjaDTO ninjaDTO) {
        ninjaService.criarNovoNinja(ninjaDTO); // Salva com os dados enviados
        return "redirect:/ninjas/ui/todos"; // Redireciona para a listagem após salvar
    }

    @GetMapping("/criar")
    public String mostrarFormularioCriar(Model model) {
        model.addAttribute("ninja", new NinjaDTO()); // Para preencher os campos do formulário
        List<MissoesDTO> missoes = missoesService.listarMissoes(); // Carregar missões para o formulário
        model.addAttribute("missoes", missoes); // Adiciona missões ao modelo
        return "criarNinja"; // Nome da página HTML
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
        } else {
            model.addAttribute("mensagem", "Ninja não encontrado!");
            return "listarNinjas";
        }
    }

    @GetMapping("/editar/{id}")
    public String editarNinjaForm(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);
        List<MissoesDTO> missoes = missoesService.listarMissoes(); // Carregar missões para o formulário
        model.addAttribute("ninja", ninja);
        model.addAttribute("missoes", missoes); // Passar as missões ao modelo
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
