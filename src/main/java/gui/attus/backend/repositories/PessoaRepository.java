package gui.attus.backend.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gui.attus.backend.entities.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
    boolean existsByNomeCompletoAndDataNascimento(String nomeCompleto, LocalDate dataNascimento);
}