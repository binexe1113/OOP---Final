package control;

import java.util.ArrayList;
import java.util.List;
import model.Aluno;
import model.Treino;
import dao.AlunoDAO;
import dao.TreinoDAO;

public class TreinoControl {

    // Cache: Guardamos a lista na memória da classe Control
    private List<Aluno> listaCacheAlunos;

    public TreinoControl() {
        this.listaCacheAlunos = new ArrayList<>();
    }

    // 1. Vai no banco UMA VEZ e guarda na memória
    public List<Aluno> carregarListaDoBanco(int idProfessor) {
        AlunoDAO dao = new AlunoDAO();
        // Atualiza o cache
        this.listaCacheAlunos = dao.listarAlunosComTreino(idProfessor);
        return this.listaCacheAlunos;
    }

    // 2. Procura SOMENTE NA MEMÓRIA (Zero Banco de Dados)
    public Treino consultarTreinoNaMemoria(int idAlunoAlvo) {
        if (this.listaCacheAlunos == null || this.listaCacheAlunos.isEmpty()) {
            return null;
        }

        // Loop "manual" procurando o aluno pelo ID
        for (Aluno a : this.listaCacheAlunos) {
            if (a.getId() == idAlunoAlvo) {
                // Achou o aluno? Retorna o treino que já está dentro dele!
                return a.getTreino();
            }
        }
        return null; // Não achou na lista
    }
    
    public Treino consultarTreinoPorLoop(int idAlunoLogado) {
        TreinoDAO dao = new TreinoDAO();
        
        // 1. Busca todos os treinos (cada um tem um objeto Aluno dentro)
        List<Treino> todosTreinos = dao.listarTodos();
        
        // 2. Loop
        for (Treino t : todosTreinos) {
            
            // 3. Navegação: Pega o Aluno do treino, depois pega o ID dele
            if (t.getAluno().getId() == idAlunoLogado) {
                return t; // Achou!
            }
        }
        
        return null;
    }
}