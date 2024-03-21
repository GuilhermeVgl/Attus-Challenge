package gui.attus.backend.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gui.attus.backend.entities.Pessoa;
import gui.attus.backend.services.PessoaService;

@RestController
public class PessoaController {
    
    @Autowired
    private PessoaService pessoaService;

    @CrossOrigin
    @GetMapping("/pessoas")
    public Iterable<Pessoa> listarPessoas() {
        return pessoaService.listarPessoas();
    }

    @CrossOrigin
    @GetMapping("/pessoas/{id}")
    public Optional<Pessoa> getPessoa(@PathVariable("id")Long id) {
        return pessoaService.getPessoa(id);
    }

    @CrossOrigin
    @PostMapping("/pessoas/criar-pessoa")
    public void criarPessoa(@RequestBody Pessoa pessoa){
        pessoaService.criarPessoa(pessoa);
    }

    @CrossOrigin
    @PutMapping("/pessoas/editar-pessoa")
    public void editarPessoa(@RequestBody Pessoa pessoa){
        pessoaService.editarPessoa(pessoa);
    }
}
