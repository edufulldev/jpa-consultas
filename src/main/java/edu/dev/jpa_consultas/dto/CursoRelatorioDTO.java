package edu.dev.jpa_consultas.dto;

import java.math.BigDecimal;

public record CursoRelatorioDTO(
        String tituloCurso,
        BigDecimal preco
) {
}
