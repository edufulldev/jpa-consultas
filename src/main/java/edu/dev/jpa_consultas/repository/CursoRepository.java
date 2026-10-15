package edu.dev.jpa_consultas.repository;

import edu.dev.jpa_consultas.dto.CursoRelatorioDTO;
import edu.dev.jpa_consultas.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long>, CursoCustomRepository {

    @Query("SELECT MIN(c.preco), MAX(c.preco), AVG(c.preco), SUM(c.preco) FROM Curso c")
    Object[] obterEstatisticaPrecoCurso();

    @Query("SELECT new edu.dev.jpa_consultas.dto.CursoRelatorioDTO(c.titulo, c.preco) FROM Curso c")
    List<CursoRelatorioDTO> gerarRelatorioCursos();

    @Query("SELECT c FROM Curso c WHERE c.preco >= :precoMinimo ORDER BY c.preco ASC")
    List<Curso> buscarCursoPorPrecoMinimo(@Param("precoMinimo")BigDecimal precoMinimo);

    List<Curso> findByTituloEspecifico(@Param("titulo") String titulo);

    @Query("SELECT c FROM Curso c WHERE (:titulo IS NULL OR c.titulo LIKE %:titulo%) " +
            "AND (:precoMinimo IS NULL OR c.preco >= :precoMinimo)")
    List<Curso> buscarCursoComParametroOpcionais(@Param("titulo") String titulo, @Param("precoMinimo")BigDecimal precoMinimo);

    @Query("SELECT c FROM Curso c JOIN FETCH c.alunos WHERE c.id = :id")
    Curso buscarCursoComAlunoPorId(@Param("id") Long id);


}