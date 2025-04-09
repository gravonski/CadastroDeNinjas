package dev.java10x.CadastroDeNinjas.Ninjas;
import org.springframework.stereotype.Service;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaDTO;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaRepository;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaMapper;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService {

    private final NinjaRepository ninjaRepository;
    private final NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    //criar um novo ninja
    public NinjaDTO criarNovoNinja(NinjaDTO ninjaDTO) {
        NinjaModel ninja = ninjaMapper.map(ninjaDTO);
        ninja = ninjaRepository.save(ninja);
        return ninjaMapper.map(ninja);
    }

    //listar todos os ninjas
    public List<NinjaDTO> listarNinjas() {
        List<NinjaModel> ninjas = ninjaRepository.findAll();
        return ninjas.stream().map(ninjaMapper::map).collect(Collectors.toList());
    }

    //listar os ninjas por ID
    public NinjaDTO listarNinjasPorId(Long id) {
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
        return ninjaPorId.map(ninjaMapper::map).orElse(null);
    }

    //alterar ninjas
    public NinjaDTO atualizarNinjasPorId(Long id, NinjaDTO ninjaRequisicao) {
        Optional<NinjaModel> ninjaExistente = ninjaRepository.findById(id);
        if (ninjaExistente.isPresent()) {
            NinjaModel ninjaAtualizado = ninjaMapper.map(ninjaRequisicao);
            ninjaAtualizado.setId(id);
            NinjaModel atualizado = ninjaRepository.save(ninjaAtualizado);
            return ninjaMapper.map(atualizado);
        } else
            return null;
    }

    //deletar o ninja por id
    public void deletarNinjasPorId(Long id) {
        ninjaRepository.deleteById(id);
    }
}