package dev.java10x.CadastroDeNinjas.Missoes.java;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {

    //Injeção de dependência
    private MissoesRepository missoesRepository;
    private MissoesMapper missoesMapper;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }

    //criar nova missao
    public MissoesDTO criarMissoes(MissoesDTO missoesDTO){
        MissoesModel missoes = missoesMapper.map(missoesDTO);
        missoes = missoesRepository.save(missoes);
        return missoesMapper.map(missoes);
    }

    //listar todas as missoes
    public List<MissoesDTO> listarMissoes(){
        List<MissoesModel> missoes = missoesRepository.findAll();
        return missoes.stream().map(missoesMapper::map).collect(Collectors.toList());
    }

    //listar missões por ID
    public MissoesDTO buscarMissoesPorId(Long id){
        Optional<MissoesModel> missoesPorId = missoesRepository.findById(id);
        return missoesPorId.map(missoesMapper::map).orElse(null);
    }

    //alterar missoes
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

    //deletar uma missão por ID
    public void deletarMissoesPorID(Long id){
        missoesRepository.deleteById(id);
    }
}
