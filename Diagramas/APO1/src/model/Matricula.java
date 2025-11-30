package model;
import java.time.LocalDate;

public class Matricula {
    private Aluno aluno;
    private Plano plano;
    private LocalDate dataInicio;

    public Matricula(Aluno aluno, Plano plano, LocalDate dataInicio) {
        this.aluno = aluno;
        this.plano = plano;
        this.dataInicio = dataInicio;
    }

    // Getters (se precisar)
    public Aluno getAluno() { return aluno; }
    public Plano getPlano() { return plano; }
}