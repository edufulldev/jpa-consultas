package edu.dev.jpa_consultas.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cursos")
@NamedQuery(
        name = "Curso.findByTituloEspecifico",
        query = "SELECT c FROM Curso c WHERE c.titulo = :titulo"
)
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false)
    private BigDecimal preco;

    @JsonManagedReference
    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
            name = "curso_aluno",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private Set<Aluno> alunos = new HashSet<>();

    protected Curso() {}

    public Curso(String titulo, BigDecimal preco) {

        this.titulo = titulo;
        this.preco = preco;
    }

    public void addAluno(Aluno aluno) {
        this.alunos.add(aluno);
        aluno.getCursos().add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }

}
