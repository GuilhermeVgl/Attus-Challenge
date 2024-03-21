package gui.attus.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gui.attus.backend.entities.Endereco;
import gui.attus.backend.repositories.EnderecoRepository;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;
    
    public List<Endereco> listarEnderecos() {
        return enderecoRepository.findAll();
    }

}
