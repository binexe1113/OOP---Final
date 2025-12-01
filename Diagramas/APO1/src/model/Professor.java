package model;

public class Professor {
    
    private int idProf;
    private String nome;
    private String cpf;
    private double valorPorAula;
    
    // Associações
    private Treino treino;
    private Aula aula;
    private AvaliacaoFisica avaliacaoFisica;

    // Construtor Vazio
    public Professor() {
    }

    // --- GETTERS E SETTERS ---

    public int getIdProf() {
        return idProf;
    }

    public void setIdProf(int idProf) {
        this.idProf = idProf;
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

    public double getValorPorAula() {
        return valorPorAula;
    }

    public void setValorPorAula(double valorPorAula) {
        this.valorPorAula = valorPorAula;
    }

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public AvaliacaoFisica getAvaliacaoFisica() {
        return avaliacaoFisica;
    }

    public void setAvaliacaoFisica(AvaliacaoFisica avaliacaoFisica) {
        this.avaliacaoFisica = avaliacaoFisica;
    }

}