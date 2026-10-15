package edu.dev.jpa_consultas.repository;

import edu.dev.jpa_consultas.entity.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CursoCustomRepositoryImpl implements CursoCustomRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Curso> buscarComCriteriaDinamica(String titulo, BigDecimal precoMinimo) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Curso> query = cb.createQuery(Curso.class);
        Root<Curso> curso = query.from(Curso.class);

        List<Predicate> predicates = new ArrayList<>();

        if(titulo != null && !titulo.isBlank()) {
            predicates.add(cb.like(cb.lower(curso.get("titulo")), "%" + titulo.toLowerCase() + "%"));
        }

        if(precoMinimo != null) {
            predicates.add(cb.greaterThanOrEqualTo(curso.get("preco"), precoMinimo));
        }

        query.where(predicates.toArray(new Predicate[0]));


        return entityManager.createQuery(query).getResultList();
    }
}
