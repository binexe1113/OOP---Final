package control;

import java.util.ArrayList;
import java.util.List;

import dao.AlunoDAO;
import model.Aluno;

public class AlunoControl {

	public List<Aluno> listarTodos() {
        AlunoDAO alunoDAO = new AlunoDAO();
        try {
            return alunoDAO.listarTodos();
        } catch (Exception e) {
            // Em caso de erro, retorna lista vazia ou imprime o erro
            e.printStackTrace();
            return new ArrayList<>();
        }
    }}
