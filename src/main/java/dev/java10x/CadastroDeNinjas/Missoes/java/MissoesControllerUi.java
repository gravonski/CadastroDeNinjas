package dev.java10x.CadastroDeNinjas.Missoes.java;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaDTO;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("missoes/ui")
public class MissoesControllerUi {

    private final MissoesService missoesService;
    private final NinjaService ninjaService; // Injeção do NinjaService

    public MissoesControllerUi(MissoesService missoesService, NinjaService ninjaService) {
        this.missoesService = missoesService;
        this.ninjaService = ninjaService; // Injeção de dependência do NinjaService
    }

    @GetMapping("/cadastrar")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("missoes", new MissoesDTO());
        return "cadastrarMissao"; // Nome da página HTML
    }

    @PostMapping("/cadastrar")
    public String salvarNovaMissao(@ModelAttribute MissoesDTO missoes) {
        missoesService.salvarNovaMissao(missoes);
        return "redirect:/missoes/ui/listar";
    }

    @GetMapping("/listar")
    public String listarMissoes(Model model) {
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        model.addAttribute("missoes", missoes);
        return "listarMissoes";
    }

    @GetMapping("/listar/{id}")
    public String listarMissoesPorId(@PathVariable Long id, Model model) {
        MissoesDTO missoes = missoesService.buscarMissoesPorId(id);
        if (missoes != null) {
            model.addAttribute("missoes", missoes);
            return "detalhesMissoes";
        } else {
            model.addAttribute("mensagem", "Missão não encontrada");
            return "listarMissoes";
        }
    }

    @GetMapping("/alterar/{id}")
    public String alteraMissoes(@PathVariable Long id, Model model) {
        MissoesDTO missaoAlterada = missoesService.buscarMissoesPorId(id);
        model.addAttribute("missoes", missaoAlterada);
        return "editarMissoes";
    }

    @PostMapping("/alterar/{id}")
    public String salvaMissoes(@PathVariable Long id, @ModelAttribute MissoesDTO missoes) {
        missoesService.atualizarMissoes(id, missoes);
        return "redirect:/missoes/ui/listar";
    }

    @DeleteMapping("/deletar/{id}")
    public String deletarMissoes(@PathVariable Long id) {
        missoesService.deletarMissoes(id);
        return "redirect:/missoes/ui/listar";
    }

    @GetMapping("/adicionar-ninja/{id}")
    public String exibirFormularioAdicionarNinja(@PathVariable Long id, Model model) {
        MissoesDTO missao = missoesService.buscarMissoesPorId(id);
        List<NinjaDTO> todosNinjas = ninjaService.listarNinjas(); // Alterado para NinjaDTO
        model.addAttribute("missoes", missao);
        model.addAttribute("todosNinjas", todosNinjas);
        return "adicionarNinjaMissao"; // Nome da página HTML
    }

    @PostMapping("/adicionar-ninja/{id}")
    public String adicionarNinjaNaMissao(@PathVariable Long id, @RequestParam Long ninjaId) {
        missoesService.adicionarNinjaNaMissao(id, ninjaId);
        return "redirect:/missoes/ui/listar/" + id;
    }
}
