package dev.java10x.CadastroDeNinjas.Missoes.java;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("missoes/ui")
public class MissoesControllerUi {

    private final MissoesService missoesService;

    public MissoesControllerUi(MissoesService missoesService) {
        this.missoesService = missoesService;
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
}
