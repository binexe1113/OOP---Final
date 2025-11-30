package model;

public class Aluno {
    
    private int id;
    private String nome;
    private String cpf;
    
    // Construtor Vazio (Boa prática)
    public Aluno() {}

    // Construtor Cheio (Usado pelo DAO)
    public Aluno(int id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    // --- Getters e Setters ---
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCpf() { return cpf; }

    // --- O PULO DO GATO ---
    // Sobrescrevemos o toString para que, ao jogar o objeto no ComboBox,
    // apareça o nome dele e não um código estranho de memória.
    @Override
    public String toString() {
        return this.nome;
    }
}