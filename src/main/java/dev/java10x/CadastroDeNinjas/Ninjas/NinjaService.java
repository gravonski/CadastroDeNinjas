package dev.java10x.CadastroDeNinjas.Ninjas;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {

    private final NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }

    //listar todos os ninjas
    public List<NinjaModel> listarNinjas() {
        return ninjaRepository.findAll();
    }

    //listar os ninjas por ID
    public NinjaModel listarNinjasPorId(Long id) {
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
        return ninjaPorId.orElse(null);
    }

    //criar um novo ninja
    public NinjaModel criarNovoNinja(NinjaModel ninjaModel) {
        return ninjaRepository.save(ninjaModel);
    }

    //deletar o ninja por id
    public NinjaModel deletarNinjasPorId(Long id) {
        ninjaRepository.deleteById(id);
        return listarNinjasPorId(id);
    }

    //alterar ninjas
    public NinjaModel atualizarNinjasPorId(Long id, NinjaModel ninjaRequisicao) {
        if (ninjaRepository.existsById(id)) {
            ninjaRequisicao.setId(id);
            return ninjaRepository.save(ninjaRequisicao);
        } else {
            return null;
        }
    }
}