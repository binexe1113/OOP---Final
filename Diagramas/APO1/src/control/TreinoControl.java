package control;

import java.util.List; 
import model.Aluno;
import model.Treino;
import dao.AlunoDAO;
import dao.TreinoDAO;


public class TreinoControl {

    // --- Métodos usados pela View do Professor ---
    public List<Aluno> listarAlunosParaProfessor() {
        // Chama o AlunoDAO e retorna a lista
        return new AlunoDAO().listarTodos(); 
    }

    // --- Métodos usados TANTO pela View do Aluno quanto do Professor ---
    public String consultarTreino(int idAluno) {
        // A lógica é a mesma: dado um ID de aluno, me dê o treino dele.
    	
        List<Treino> todos = new TreinoDAO().listarTodosTreinos();
        
        // A lógica do Loop do diagrama fica aqui
        for (Treino t : todos) {
            if (t.getAluno().getId() == idAluno) {
                return t.getDescricao();
            }
        }
        return "Nenhum treino encontrado.";
    }
}