package edu.dev.jpa_consultas.repository;

import edu.dev.jpa_consultas.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
