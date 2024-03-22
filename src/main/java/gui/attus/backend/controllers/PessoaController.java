package gui.attus.backend.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gui.attus.backend.entities.Pessoa;
import gui.attus.backend.services.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    
    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/consultar")
    public Iterable<Pessoa> listarPessoas() {
        return pessoaService.listarPessoas();
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> consultarPessoa(@PathVariable Long id) {
        Optional<Pessoa> pessoaOptional = pessoaService.consultarPessoa(id);
        if (pessoaOptional.isPresent()) {
            return ResponseEntity.ok(pessoaOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criarPessoa(@RequestBody Pessoa pessoa) {
        try {
            Pessoa pessoaCriada = pessoaService.criarPessoa(pessoa);
            return ResponseEntity.ok(pessoaCriada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Pessoa> editarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoaAtualizada) {
        ResponseEntity<Pessoa> responseEntity;
        try {
            Pessoa pessoa = pessoaService.editarPessoa(id, pessoaAtualizada);
            responseEntity = ResponseEntity.ok(pessoa);
        } catch (IllegalArgumentException e) {
            responseEntity = ResponseEntity.badRequest().body(null);
        }
        return responseEntity;
    }
}
