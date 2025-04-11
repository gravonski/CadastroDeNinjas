package dev.java10x.CadastroDeNinjas.Missoes.java;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Deletar Missões por ID
@DeleteMapping("deletar/{id}")
public ResponseEntity<String> deletarMissoes (@PathVariable Long id) {
    if (deletarMissoes())
}




