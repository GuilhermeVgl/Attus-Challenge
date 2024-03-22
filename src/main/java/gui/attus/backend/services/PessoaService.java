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

    public Optional<Pessoa> consultarPessoa(Long id) {
        return pessoaRepository.findById(id);
    }

    public Pessoa editarPessoa(Long id, Pessoa pessoaEditada) {
        Optional<Pessoa> pessoaEncontrada = pessoaRepository.findById(id);
        
        this.verificaDuplicidade(pessoaEditada);

        if (pessoaEncontrada.isPresent()) {
            Pessoa pessoa = pessoaEncontrada.get();
            pessoa.setNomeCompleto(pessoaEditada.getNomeCompleto());
            pessoa.setDataNascimento(pessoaEditada.getDataNascimento());
            return pessoaRepository.save(pessoa);
        }

        throw new IllegalArgumentException("Pessoa não encontrada com o ID fornecido: " + id);
    }

    public Pessoa criarPessoa(Pessoa pessoa){
        // Verifica duplicidade!
        this.verificaDuplicidade(pessoa);
        return pessoaRepository.save(pessoa);
    }

    public void verificaDuplicidade(Pessoa pessoa) {
        if (pessoaRepository.existsByNomeCompletoAndDataNascimento(pessoa.getNomeCompleto(), pessoa.getDataNascimento())) {
            throw new RuntimeException("Pessoa já cadastrada");
        }
    }
}
