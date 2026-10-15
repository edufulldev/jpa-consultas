package edu.dev.jpa_consultas.repository;

import edu.dev.jpa_consultas.entity.Curso;

import java.math.BigDecimal;
import java.util.List;

public interface CursoCustomRepository {

    List<Curso> buscarComCriteriaDinamica(String titulo, BigDecimal precoMinimo);
}
