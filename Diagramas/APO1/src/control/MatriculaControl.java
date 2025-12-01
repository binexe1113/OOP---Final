package control;

import java.time.LocalDate;
import model.Aluno;
import model.Matricula;
import model.Plano;
import dao.AlunoDAO;
import dao.MatriculaDAO;
import dao.PlanoDAO;

public class MatriculaControl {

    // Método principal chamado pela View
    public Boolean adicionarMatricula(String cpfAluno, int idPlano, LocalDate dataInicio) {
        
        // 1. MC->>AD: buscarPorId(cpfAluno)
        // No diagrama está "buscarPorId", mas como passamos CPF, buscarPorCpf é o correto no código.
        AlunoDAO alunoDAO = new AlunoDAO();
        Aluno objAluno = alunoDAO.buscarPorCpf(cpfAluno);
        
        if (objAluno == null) {
            return false;
        }

        // 2. MC->>PD: buscarPorId(idPlano)
        PlanoDAO planoDAO = new PlanoDAO();
        Plano objPlano = planoDAO.buscarPorId(idPlano);

        if (objPlano == null) {
            return false;
        }

        // 3. MC->>M: <<create>> (objAluno, objPlano, dataInicio)
        Matricula novaMatricula = new Matricula(objAluno, objPlano, dataInicio);

        // 4. MC->>MD: salvar(novaMatricula)
        MatriculaDAO matriculaDAO = new MatriculaDAO();
        boolean sucesso = matriculaDAO.salvar(novaMatricula);

        // Feedback final para a View exibir
        if (sucesso) {
            return true;
        } else {
            return false;
        }
    }
}