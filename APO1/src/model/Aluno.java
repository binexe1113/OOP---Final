package model;

import java.util.List;

public class Aluno {
    
    private int idAluno;
    private String nome;
    private String cpf;
    private int idade;
    private String email;
    private String telefone;
    
    // Objetos e Listas
    private List<AvaliacaoFisica> avaliacoes; 
    private Matricula matricula; 
    private Treino treino;       
    
    // Construtor Vazio
    public Aluno() {
    }

    public Aluno(int id, String nome, String cpf, int idade, String email, String telefone, List<AvaliacaoFisica> avaliacoes, Matricula matricula, Treino treino) {
        this.idAluno = id;
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;       
        this.email = email;       
        this.telefone = telefone; 
        this.avaliacoes = avaliacoes; 
        this.matricula = matricula;   
        this.treino = treino;         
    }
    
    public Aluno(int id, String nome, String cpf) {
        this.idAluno = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    // --- Getters e Setters ---

    public int getId() {
        return idAluno;
    }

    public void setId(int id) {
        this.idAluno = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<AvaliacaoFisica> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<AvaliacaoFisica> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }

    
    //OVERRIDE PARA O COMBOBOX
    
    @Override
    public String toString() {
       return this.cpf + " - " + this.nome;
    }

}