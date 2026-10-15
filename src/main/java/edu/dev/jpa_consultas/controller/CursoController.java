package edu.dev.jpa_consultas.controller;

import edu.dev.jpa_consultas.dto.CursoRelatorioDTO;
import edu.dev.jpa_consultas.entity.Curso;
import edu.dev.jpa_consultas.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping("/estatisticas")
    public ResponseEntity<Object[]> obterEstatisticasPrecos() {
        Object[] estatisticas = cursoRepository.obterEstatisticaPrecoCurso();
        return ResponseEntity.ok(estatisticas);
    }

    @GetMapping("/relatorio")
    public ResponseEntity<List<CursoRelatorioDTO>> obterRelatorioCursos() {
        List<CursoRelatorioDTO> relatorio = cursoRepository.gerarRelatorioCursos();
        return ResponseEntity.ok(relatorio);
    }

    @GetMapping("/pesquisa")
    public ResponseEntity<List<Curso>> pesquisarCursosDinamico(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) BigDecimal precoMinimo) {
        List<Curso> cursos = cursoRepository.buscarComCriteriaDinamica(titulo, precoMinimo);
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Curso>> buscarPorTituloEspecifico(@PathVariable String titulo) {
        List<Curso> cursos = cursoRepository.findByTituloEspecifico(titulo);
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}/alunos")
    public ResponseEntity<Curso> buscarCursoComAlunos(@PathVariable Long id) {
        Curso curso = cursoRepository.buscarCursoComAlunoPorId(id);
        if (curso != null) {
            return ResponseEntity.ok(curso);
        }
        return ResponseEntity.notFound().build();
    }
}
