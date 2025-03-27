package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {

    //Injeção de dependência
    private MissoesRepository missoesRepository;

    public MissoesService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
    }

    //listar todas as missoes
    public List<MissoesModel> listarMissoes(){
        return missoesRepository.findAll();
    }

    //listar missões por ID
    public MissoesModel buscarMissoesPorId(Long id){
        Optional<MissoesModel> missoes = missoesRepository.findById(id);
        return missoes.orElse(null);
    }

    //criar nova missao
    public MissoesModel criarMissoes(MissoesModel missoesModel){
        return missoesRepository.save(missoesModel);
    }

    //deletar uma missão por ID
    public MissoesModel deletarMissoesPorID(Long id){
        missoesRepository.deleteById(id);
        return buscarMissoesPorId(id);
    }
}
