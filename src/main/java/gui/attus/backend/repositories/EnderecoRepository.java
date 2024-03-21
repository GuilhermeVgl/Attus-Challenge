package gui.attus.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gui.attus.backend.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
    
}
