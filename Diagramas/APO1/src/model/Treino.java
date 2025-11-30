package model;

public class Treino {

    private int id;
    private String descricao; // O texto do treino (ex: "3x10 Supino...")
    private Aluno aluno;      // A quem esse treino pertence?

    // Construtor Vazio
    public Treino() {}

    // Construtor Cheio
    public Treino(int id, Aluno aluno, String descricao) {
        this.id = id;
        this.aluno = aluno;
        this.descricao = descricao;
    }

    // --- Getters e Setters ---
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    
    // Helper: retorna o texto formatado (opcional)
    public String getDescricaoFormatada() {
        return "Treino do Aluno: " + aluno.getNome() + "\n\n" + descricao;
    }
}