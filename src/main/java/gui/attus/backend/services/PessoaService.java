package gui.attus.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gui.attus.backend.entities.Pessoa;
import gui.attus.backend.repositories.PessoaRepository;

@Service
public class PessoaService {
    
    @Autowired
    private PessoaRepository pessoaRepository;
    
    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

}
