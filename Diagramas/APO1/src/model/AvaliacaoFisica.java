package model;

import java.time.LocalDate;

public class AvaliacaoFisica {

    // Atributos do Diagrama
    private int idAvaliacao;
    private LocalDate data;
    private float peso;
    private float altura;
    private float percentualGordura;
    private float massaMuscular;
    private String medidas; // Pode ser um texto descritivo ou JSON
    private LocalDate proximaAvaliacao;

    // Associação solicitada: Avaliação tem ligação com Professor
    private Professor professor;

    // Construtor Vazio
    public AvaliacaoFisica() {
    }



    // --- GETTERS E SETTERS ---
    
    public int getIdAvaliacao() { return idAvaliacao; }
    public void setIdAvaliacao(int idAvaliacao) { this.idAvaliacao = idAvaliacao; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public float getPeso() { return peso; }
    public void setPeso(float peso) { this.peso = peso; }

    public float getAltura() { return altura; }
    public void setAltura(float altura) { this.altura = altura; }

    public float getPercentualGordura() { return percentualGordura; }
    public void setPercentualGordura(float percentualGordura) { this.percentualGordura = percentualGordura; }

    public float getMassaMuscular() { return massaMuscular; }
    public void setMassaMuscular(float massaMuscular) { this.massaMuscular = massaMuscular; }

    public String getMedidas() { return medidas; }
    public void setMedidas(String medidas) { this.medidas = medidas; }

    public LocalDate getProximaAvaliacao() { return proximaAvaliacao; }
    public void setProximaAvaliacao(LocalDate proximaAvaliacao) { this.proximaAvaliacao = proximaAvaliacao; }

    public Professor getProfessor() { return professor; }
    public void setProfessor(Professor professor) { this.professor = professor; }
}