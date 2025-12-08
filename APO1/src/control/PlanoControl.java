package control;

import java.util.ArrayList;
import java.util.List;

import dao.PlanoDAO;
import model.Plano;

public class PlanoControl {

	public List<Plano> listarTodos() {
        PlanoDAO PlanoDAO = new PlanoDAO();
        try {
        	List<Plano> x = PlanoDAO.listarTodos();
        	System.out.println(x);
            return x;
        } catch (Exception e) {
            // Em caso de erro, retorna lista vazia ou imprime o erro
            e.printStackTrace();
            System.out.println("ERRO NA DAO PLANO"); //DEBUGG
            return new ArrayList<>();
        }
	}
}