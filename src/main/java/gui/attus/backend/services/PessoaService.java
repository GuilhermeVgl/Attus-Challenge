package gui.attus.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gui.attus.backend.entities.Pessoa;
import gui.attus.backend.repositories.PessoaRepository;

@Service
public class PessoaService {
    
    @Autowired
    private PessoaRepository pessoaRepository;
    
    public Iterable<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> getPessoa(Long id) {
        return pessoaRepository.findById(id);
    }

    public void editarPessoa(Pessoa pessoa){
        this.pessoaRepository.save(pessoa);
   }

   public void criarPessoa(Pessoa pessoa){
       this.pessoaRepository.save(pessoa);
   }
}
