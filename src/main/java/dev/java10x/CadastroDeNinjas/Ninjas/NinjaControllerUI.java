package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.Missoes.java.MissoesDTO;
import dev.java10x.CadastroDeNinjas.Missoes.java.MissoesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Criação de ninja", description = "Cria um novo ninja e cadastra ele no Banco de Dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro na criação do Ninja")
    })
    @PostMapping("/criar")
    public String criarNovoNinja(
            @Parameter(description = "Dados do ninja a serem cadastrados no sistema")
            @ModelAttribute NinjaDTO ninjaDTO) {
        ninjaService.criarNovoNinja(ninjaDTO); // Salva com os dados enviados
        return "redirect:/ninjas/ui/todos"; // Redireciona para a listagem após salvar
    }

    @Operation(summary = "Mostrar formulário de criação", description = "Rota que mostra o formulário para preenchimento do usuário para a criação de um novo ninja")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Formulário exibido corretamente"),
            @ApiResponse(responseCode = "400", description = "Não foi possível cadastrar o ninja no momento")
    })
    @GetMapping("/criar")
    public String mostrarFormularioCriar(Model model) {
        model.addAttribute("ninja", new NinjaDTO()); // Para preencher os campos do formulário
        List<MissoesDTO> missoes = missoesService.listarMissoes(); // Carregar missões para o formulário
        model.addAttribute("missoes", missoes); // Adiciona missões ao modelo
        return "criarNinja"; // Nome da página HTML
    }

    @Operation(summary = "Listar todos os ninjas", description = "Rota que exibe a lista de todos os ninjas cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de ninjas exibida corretamente"),
            @ApiResponse(responseCode = "400", description = "Erro ao listar os ninjas")
    })
    @GetMapping("/todos")
    public String listarNinjas(Model model) {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        model.addAttribute("ninjas", ninjas);
        return "listarNinjas";
    }

    @Operation(summary = "Mostrar detalhes de um ninja", description = "Rota que exibe os detalhes de um ninja específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalhes do ninja exibidos corretamente"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado")
    })
    @GetMapping("/todos/{id}")
    public String listarNinjasPorId(
            @Parameter(description = "ID do ninja cujos detalhes serão exibidos")
            @PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);
        if (ninja != null) {
            model.addAttribute("ninja", ninja);
            return "detalhesNinja";
        } else {
            model.addAttribute("mensagem", "Ninja não encontrado!");
            return "listarNinjas";
        }
    }

    @Operation(summary = "Editar ninja", description = "Rota que exibe o formulário para editar os dados de um ninja")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Formulário de edição exibido corretamente"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado")
    })
    @GetMapping("/editar/{id}")
    public String editarNinjaForm(
            @Parameter(description = "ID do ninja a ser editado")
            @PathVariable Long id,
            @Parameter(description = "Modelo para edição dos dados do ninja")
            Model model) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);
        List<MissoesDTO> missoes = missoesService.listarMissoes(); // Carregar missões para o formulário
        model.addAttribute("ninja", ninja);
        model.addAttribute("missoes", missoes); // Passar as missões ao modelo
        return "editarNinja";
    }

    @Operation(summary = "Salvar edição de ninja", description = "Rota para salvar as alterações feitas no ninja")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar o ninja")
    })
    @PostMapping("/editar/{id}")
    public String salvarEdicao(
            @Parameter(description = "ID do ninja a ser atualizado")
            @PathVariable Long id,
            @Parameter(description = "Dados atualizados do ninja")
            @ModelAttribute NinjaDTO ninjaAtualizado) {
        ninjaService.atualizarNinjasPorId(id, ninjaAtualizado);
        return "redirect:/ninjas/ui/todos";
    }

    @Operation(summary = "Deletar ninja", description = "Rota para deletar um ninja específico do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado")
    })
    @GetMapping("/deletar/{id}")
    public String deletarNinjasPorId(
            @Parameter(description = "ID do ninja a ser deletado")
            @PathVariable Long id) {
        ninjaService.deletarNinjasPorId(id);
        return "redirect:/ninjas/ui/todos";
    }
}
