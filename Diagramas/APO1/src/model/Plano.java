package model;

public class Plano {
    private int id;
    private String nome;
    private double valor;

    public Plano(int id, String nome, double valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }
    
    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    // toString para testes futuros
    @Override public String toString() { return nome; }
}