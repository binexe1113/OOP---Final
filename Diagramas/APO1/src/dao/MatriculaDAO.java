package dao;
import model.Matricula;

public class MatriculaDAO {
    public boolean salvar(Matricula matricula) {
        // Mock: Simula o INSERT no banco
        System.out.println("--- BANCO DE DADOS ---");
        System.out.println("Salvando matrícula do aluno: " + matricula.getAluno().getNome());
        System.out.println("No plano: " + matricula.getPlano().getNome());
        System.out.println("----------------------");
        return true; // Sucesso
    }
}