package model;

import java.time.LocalDateTime;
import java.util.ArrayList; // Necessário para iniciar a lista
import java.util.List;

public class Aula {
    
    private int idAula;
    private String nome;
    private LocalDateTime horario;
    private int capacidadeMaxima;
    
    // Associações
    private Professor professor;
    private List<Aluno> alunos;

    // Construtor Vazio
    public Aula() {
        // Inicializa a lista vazia para evitar erros
        this.alunos = new ArrayList<>();
    }

    // Construtor Cheio (Opcional, para facilitar testes)
    public Aula(int idAula, String nome, LocalDateTime horario, int capacidadeMaxima, Professor professor) {
        this.idAula = idAula;
        this.nome = nome;
        this.horario = horario;
        this.capacidadeMaxima = capacidadeMaxima;
        this.professor = professor;
        this.alunos = new ArrayList<>();
    }

    // --- MÉTODOS ÚTEIS ---
    
    // Método auxiliar para adicionar um aluno (evita ter que pegar a lista inteira)
    public boolean adicionarAluno(Aluno aluno) {
        if (this.alunos.size() < capacidadeMaxima) {
            this.alunos.add(aluno);
            return true; // Adicionado com sucesso
        }
        return false; // Aula cheia
    }

    // --- GETTERS E SETTERS ---

    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

}