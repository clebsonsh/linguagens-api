package br.com.alura.linguagens.api;

import com.mongodb.lang.Nullable;
import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LinguagemController {

    @Autowired
    private LinguagemRepository repositorio;

    @GetMapping("/linguagens")
    public List<Linguagem> obterLinguagens() {
        List<Linguagem> linguagens = repositorio.findAll();
        return linguagens;
    }

    @GetMapping("/linguagens/{id}")
    public ResponseEntity obterLinguagem(@PathVariable String id) {
        Optional<Linguagem> linguagem = repositorio.findById(id);

        if (linguagem.isPresent()) {
            return ResponseEntity.ok(linguagem);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/linguagens")
    public Linguagem cadastraLinguagem(@RequestBody Linguagem linguagem) {
        Linguagem linguagemSalva = repositorio.save(linguagem);
        return linguagemSalva;
    }

    @DeleteMapping("/linguagens/{id}")
    public void deletarLinguaguem(@PathVariable("id") String id) {
        Linguagem linguagem = repositorio.findById(id)
                .orElse(new Linguagem());

        repositorio.delete(linguagem);
    }

}
