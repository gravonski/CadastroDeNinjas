package dev.java10x.CadastroDeNinjas.Missoes.java;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {

    private final MissoesRepository missoesRepository;
    private final MissoesMapper missoesMapper;
    private final NinjaRepository ninjaRepository;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper, NinjaRepository ninjaRepository) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
        this.ninjaRepository = ninjaRepository;
    }

    public MissoesModel buscarMissaoModelPorId(Long id) {
        return missoesRepository.findById(id).orElse(null);
    }


    // Salvar uma nova missão
    public void salvarNovaMissao(MissoesDTO missoesDTO) {
        MissoesModel missoes = missoesMapper.map(missoesDTO);
        missoesRepository.save(missoes);
    }

    // Adicionar ninja a uma missão
    public void adicionarNinjaNaMissao(Long missaoId, Long ninjaId) {
        MissoesModel missao = missoesRepository.findById(missaoId).orElseThrow();
        NinjaModel ninja = ninjaRepository.findById(ninjaId).orElseThrow();
        missao.getNinja().add(ninja);
        missoesRepository.save(missao);
    }

    // Criar nova missão
    public MissoesDTO criarMissoes(MissoesDTO missoesDTO){
        MissoesModel missoes = missoesMapper.map(missoesDTO);
        missoes = missoesRepository.save(missoes);
        return missoesMapper.map(missoes);
    }

    // Listar todas as missões
    public List<MissoesDTO> listarMissoes(){
        List<MissoesModel> missoes = missoesRepository.findAll();
        return missoes.stream().map(missoesMapper::map).collect(Collectors.toList());
    }

    // Buscar missão por ID
    public MissoesDTO buscarMissoesPorId(Long id){
        Optional<MissoesModel> missoesPorId = missoesRepository.findById(id);
        return missoesPorId.map(missoesMapper::map).orElse(null);
    }

    // Atualizar missão
    public MissoesDTO atualizarMissoes(Long id, MissoesDTO missoesDTO){
        Optional<MissoesModel> missoesPorIdExistente = missoesRepository.findById(id);
        if (missoesPorIdExistente.isPresent()) {
            MissoesModel missoesAtualizada = missoesMapper.map(missoesDTO);
            missoesAtualizada.setId(id);
            MissoesModel atualizado = missoesRepository.save(missoesAtualizada);
            return missoesMapper.map(atualizado);
        } else {
            return null;
        }
    }

    // Deletar missão por ID
    public void deletarMissoes(Long id){
        missoesRepository.deleteById(id);
    }
}
