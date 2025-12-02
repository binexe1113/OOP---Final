package model;

import java.time.LocalDate;

public class Treino {

    private int idTreino;
    private String descricao; // O texto do treino (ex: "3x10 Supino...")
    private LocalDate dataInicio;
    private LocalDate dataFim;
    
    // Associações
    private Professor professor;
    private Aluno aluno;      

    // Construtor Vazio
    public Treino() {}

    // Construtor Cheio ()
    public Treino(int idTreino, Aluno aluno, String descricao, LocalDate dataInicio, LocalDate dataFim) {
        this.idTreino = idTreino;
        this.aluno = aluno;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    // --- GETTERS E SETTERS ---

    public Treino(int idTreino, String descricao, LocalDate dataInicio, LocalDate dataFim) {
        this.idTreino = idTreino;
        this.descricao = descricao;
        this.dataInicio = dataInicio; 
        this.dataFim = dataFim;
    }
    
    public Treino(int idTreino, String descricao, LocalDate dataInicio, LocalDate dataFim, Aluno aluno) {
        this.idTreino = idTreino;
        this.descricao = descricao;
        this.dataInicio = dataInicio; 
        this.dataFim = dataFim;
        this.aluno = aluno;
    }

    
    public int getIdTreino() {
        return idTreino;
    }

    public void setIdTreino(int idTreino) {
        this.idTreino = idTreino;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    
    
}