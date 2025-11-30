package control;

import java.time.LocalDate;
import dao.AlunoDAO;
import dao.MatriculaDAO;
import dao.PlanoDAO;
import model.Aluno;
import model.Matricula;
import model.Plano;

public class MatriculaControl {

    // CORREÇÃO 1: Mudei os parâmetros para bater com a View (String cpf, int idPlano, Data)
    // CORREÇÃO 2: Mudei o retorno para boolean, pois a View espera true/false
    public boolean adicionarMatricula(String cpfAluno, int idPlano, LocalDate dataInicio) {
        
        // 1. Instancia os DAOs
        AlunoDAO alunoDAO = new AlunoDAO();
        PlanoDAO planoDAO = new PlanoDAO();
        MatriculaDAO matriculaDAO = new MatriculaDAO();
        
        // 2. Busca os objetos
        // CORREÇÃO 3: Aluno busca por CPF (String), Plano busca por ID (int)
        Aluno objAluno = alunoDAO.buscarPorCpf(cpfAluno);
        Plano objPlano = planoDAO.buscarPorId(idPlano);
        
        // Validação: Se não achou um dos dois, retorna falso
        if (objAluno == null || objPlano == null) {
            // Dica: Você pode imprimir no console para ajudar a debugar
            if (objAluno == null) System.out.println("Aluno com CPF " + cpfAluno + " não encontrado.");
            if (objPlano == null) System.out.println("Plano com ID " + idPlano + " não encontrado.");
            
            return false;
        }
        
        // 3. Cria a Matrícula
        Matricula novaMatricula = new Matricula(objAluno, objPlano, dataInicio);
        
        // 4. Salva e retorna o resultado (true ou false)
        return matriculaDAO.salvar(novaMatricula);
    }
}