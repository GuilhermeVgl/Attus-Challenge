package gui.attus.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gui.attus.backend.entities.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
    
    List<Endereco> findByPessoaId(Long pessoaId);    
}
