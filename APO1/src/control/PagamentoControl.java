package control;

import java.util.ArrayList;
import java.util.List;

import dao.AlunoDAO;
import dao.PagamentoDAO;
import model.Aluno;
import model.Pagamento;

public class PagamentoControl {

	public List<Pagamento> listarTodos() {
        PagamentoDAO PagamentoDAO = new PagamentoDAO();
        try {
        	List<Pagamento> listaPagamentos = PagamentoDAO.listarTodos();
        	System.out.println(listaPagamentos);//DEBUGGINS 
            return listaPagamentos;
        } catch (Exception e) {
            // Em caso de erro, retorna lista vazia ou imprime o erro
            e.printStackTrace();
            System.out.println("ERRO NA DAO PLANO"); //DEBUGG
            return new ArrayList<>();
        }
	}
}
