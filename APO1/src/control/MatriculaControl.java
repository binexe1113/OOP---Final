package control;

import java.time.LocalDate;
import model.Aluno;
import model.Matricula;
import model.Pagamento;
import model.Plano;
import dao.AlunoDAO;
import dao.MatriculaDAO;
import dao.PagamentoDAO;
import dao.PlanoDAO;

public class MatriculaControl {

    // Método principal chamado pela View
    public Boolean adicionarMatricula(Aluno aluno, Plano plano, LocalDate dataInicio, Pagamento pagamento) {
    	
    	Matricula matricula = new Matricula(aluno, plano, dataInicio,pagamento);
    	
    	MatriculaDAO dao = new MatriculaDAO();
    	
    	return dao.salvar(matricula);
    	
    
    }
}