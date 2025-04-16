package dev.java10x.CadastroDeNinjas.Missoes.java;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaDTO;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Mostrar formulário de cadastro de missão", description = "Exibe o formulário para o usuário cadastrar uma nova missão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Formulário de cadastro exibido corretamente"),
            @ApiResponse(responseCode = "400", description = "Erro ao exibir o formulário")
    })
    @GetMapping("/cadastrar")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("missoes", new MissoesDTO());
        return "cadastrarMissao"; // Nome da página HTML
    }

    @Operation(summary = "Salvar nova missão", description = "Salva uma nova missão no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao salvar a missão")
    })
    @PostMapping("/cadastrar")
    public String salvarNovaMissao(
            @Parameter(description = "Dados da missão a ser cadastrada")
            @ModelAttribute MissoesDTO missoes) {
        missoesService.salvarNovaMissao(missoes);
        return "redirect:/missoes/ui/listar";
    }

    @Operation(summary = "Listar todas as missões", description = "Exibe a lista de todas as missões cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de missões exibida corretamente"),
            @ApiResponse(responseCode = "400", description = "Erro ao listar as missões")
    })
    @GetMapping("/listar")
    public String listarMissoes(Model model) {
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        model.addAttribute("missoes", missoes);
        return "listarMissoes";
    }

    @Operation(summary = "Mostrar detalhes de uma missão", description = "Exibe os detalhes de uma missão específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalhes da missão exibidos corretamente"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada")
    })
    @GetMapping("/listar/{id}")
    public String listarMissoesPorId(
            @Parameter(description = "ID da missão a ser exibida")
            @PathVariable Long id, Model model) {
        MissoesDTO missoes = missoesService.buscarMissoesPorId(id);
        if (missoes != null) {
            model.addAttribute("missoes", missoes);
            return "detalhesMissoes";
        } else {
            model.addAttribute("mensagem", "Missão não encontrada");
            return "listarMissoes";
        }
    }

    @Operation(summary = "Alterar missão", description = "Exibe o formulário para alterar os dados de uma missão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Formulário de alteração exibido corretamente"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada")
    })
    @GetMapping("/alterar/{id}")
    public String alteraMissoes(
            @Parameter(description = "ID da missão a ser alterada")
            @PathVariable Long id, Model model) {
        MissoesDTO missaoAlterada = missoesService.buscarMissoesPorId(id);
        model.addAttribute("missoes", missaoAlterada);
        return "editarMissoes";
    }

    @Operation(summary = "Salvar alterações na missão", description = "Salva as alterações feitas em uma missão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão alterada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao alterar a missão")
    })
    @PostMapping("/alterar/{id}")
    public String salvaMissoes(
            @Parameter(description = "ID da missão a ser atualizada")
            @PathVariable Long id,
            @Parameter(description = "Dados atualizados da missão")
            @ModelAttribute MissoesDTO missoes) {
        missoesService.atualizarMissoes(id, missoes);
        return "redirect:/missoes/ui/listar";
    }

    @Operation(summary = "Deletar missão", description = "Remove uma missão específica do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada")
    })
    @DeleteMapping("/deletar/{id}")
    public String deletarMissoes(
            @Parameter(description = "ID da missão a ser deletada")
            @PathVariable Long id) {
        missoesService.deletarMissoes(id);
        return "redirect:/missoes/ui/listar";
    }

    @Operation(summary = "Adicionar ninja à missão", description = "Exibe o formulário para adicionar um ninja à missão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Formulário para adicionar ninja exibido corretamente"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada")
    })
    @GetMapping("/adicionar-ninja/{id}")
    public String exibirFormularioAdicionarNinja(
            @Parameter(description = "ID da missão à qual o ninja será adicionado")
            @PathVariable Long id, Model model) {
        MissoesDTO missao = missoesService.buscarMissoesPorId(id);
        List<NinjaDTO> todosNinjas = ninjaService.listarNinjas(); // Alterado para NinjaDTO
        model.addAttribute("missoes", missao);
        model.addAttribute("todosNinjas", todosNinjas);
        return "adicionarNinjaMissao"; // Nome da página HTML
    }

    @Operation(summary = "Adicionar ninja na missão", description = "Adiciona um ninja à missão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja adicionado à missão com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão ou Ninja não encontrados")
    })
    @PostMapping("/adicionar-ninja/{id}")
    public String adicionarNinjaNaMissao(
            @Parameter(description = "ID da missão à qual o ninja será adicionado")
            @PathVariable Long id,
            @Parameter(description = "ID do ninja a ser adicionado à missão")
            @RequestParam Long ninjaId) {
        missoesService.adicionarNinjaNaMissao(id, ninjaId);
        return "redirect:/missoes/ui/listar/" + id;
    }
}
