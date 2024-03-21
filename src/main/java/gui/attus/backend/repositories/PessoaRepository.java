package gui.attus.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gui.attus.backend.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
    
}
