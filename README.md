# 🚀 JPA & Consultas Avançadas com Java 17 e Spring Boot

Projeto desenvolvido para colocar em prática conceitos avançados de persistência de dados, mapeamento objeto-relacional (ORM),
otimização de consultas e arquitetura de repositórios utilizando **Java 17**, **Spring Boot 3**, **Spring Data JPA** e **H2 Database**.

## 🛠️ Tecnologias e Ferramentas Utilizadas
* **Java 17** (Records, Streams, etc.)
* **Spring Boot 3.3.5**
* **Spring Data JPA & Hibernate**
* **H2 Database** (Banco de dados em memória)
* **Maven**

## 📚 Funcionalidades e Conceitos Implementados

1. **Mapeamento de Entidades e Relacionamentos:**
   * Entidades modeladas (`Aluno`, `Curso`) com mapeamento de chaves e colunas customizadas.
   * Relacionamento **Muitos-para-Muitos (`@ManyToMany`) bidirecional** com tabela associativa e métodos auxiliares de sincronização.
2. **Persistência e Transacionalidade:**
   * Gestão correta de persistência em cascata e tratamento de entidades para evitar erros de estado (*detached entities*).
3. **Consultas Avançadas com JPQL e Agregações:**
   * Funções de agregação (`MIN`, `MAX`, `AVG`, `SUM`).
   * Relatórios personalizados utilizando o recurso **`SELECT NEW`** para projeção direta em **DTOs (Java Records)**.
4. **Otimização e Boas Práticas:**
   * Configuração de estratégias **`LAZY`** e **`EAGER`**.
   * Resolução do problema de `LazyInitializationException` através de consultas planejadas com **`JOIN FETCH`**.
   * Uso de **Named Queries** estáticas mapeadas nas entidades.
5. **Consultas Dinâmicas:**
   * Consultas JPQL com parâmetros opcionais.
   * Implementação de Repositório Customizado utilizando a **Criteria API** da JPA (`CriteriaBuilder`, `CriteriaQuery`, `Predicate`).
6. **API REST:**
   * Endpoints expostos via `RestController` para validação e testes de todas as consultas e relatórios.


## 📡 Endpoints Disponíveis

| Verbo | Rota | Descrição |
| :--- | :--- | :--- |
| `GET` | `/api/cursos/estatisticas` | Retorna estatísticas agregadas de preço (Min, Max, Avg, Sum). |
| `GET` | `/api/cursos/relatorio` | Gera relatório de cursos projetado em DTO (`SELECT NEW`). |
| `GET` | `/api/cursos/pesquisa` | Pesquisa dinâmica opcional utilizando a Criteria API. |
| `GET` | `/api/cursos/titulo/{titulo}` | Busca curso por título utilizando *Named Query*. |
| `GET` | `/api/cursos/{id}/alunos` | Busca curso e alunos associados utilizando `JOIN FETCH`. |

