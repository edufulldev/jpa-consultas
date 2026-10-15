package edu.dev.jpa_consultas;

import edu.dev.jpa_consultas.entity.Aluno;
import edu.dev.jpa_consultas.entity.Curso;
import edu.dev.jpa_consultas.repository.AlunoRepository;
import edu.dev.jpa_consultas.repository.CursoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@SpringBootApplication
public class JpaConsultasApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaConsultasApplication.class, args);
	}

	@Bean
    CommandLineRunner initDatabase(AlunoRepository alunoRepository, CursoRepository cursoRepository) {
		return args -> {
			// 1. Criando os Alunos
			Aluno aluno1 = new Aluno("Eduardo César", "eduardo@email.com");
			Aluno aluno2 = new Aluno("Ana Carla", "anacarla@email.com");

			aluno1 = alunoRepository.save(aluno1);
			aluno2 = alunoRepository.save(aluno2);

			// 2. Criando os Cursos
			// 2. Criando os Cursos com preços
			Curso cursoJava = new Curso("Java 17 e Spring Boot", new BigDecimal("299.90"));
			Curso cursoJPA = new Curso("JPA Avançado e Hibernate", new BigDecimal("199.90"));

			// 3. Associando alunos aos cursos usando o método auxiliar (Lado proprietário)
			cursoJava.addAluno(aluno1);
			cursoJava.addAluno(aluno2);

			cursoJPA.addAluno(aluno1);

			// 4. Salvando os cursos (como configuramos CascadeType.PERSIST, os alunos e a tabela de associação são salvos juntos)
			cursoRepository.save(cursoJava);
			cursoRepository.save(cursoJPA);

			// Testando o relatório com SELECT NEW
			System.out.println("--- RELATÓRIO DE CURSOS (DTO) ---");
			cursoRepository.gerarRelatorioCursos().forEach(dto ->
					System.out.println("Curso: " + dto.tituloCurso() + " | Preço: R$ " + dto.preco())
			);
		};
	}

}
