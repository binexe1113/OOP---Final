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
    //ROTINA TREINOS PROFESSOR BUSCAR TREINO ASSOCIADO AO ALUNO
    
    // 1. Vai no banco UMA VEZ e guarda na memória
    //*Vai ao AlunoDAO uma única vez para buscar todos os alunos 
    //(e seus treinos anexados) vinculados a um professor e armazena o resultado em listaCacheAlunos
    public List<Aluno> listarAlunosComTreino(int idProfessor) {
        AlunoDAO dao = new AlunoDAO();
        // Atualiza o cache com a lista 
        this.listaCacheAlunos = dao.listarAlunosComTreino(idProfessor);
        return this.listaCacheAlunos;
    }

    // 2. Procura SOMENTE NA MEMÓRIA (Zero Banco de Dados)
    //Procura o treino do aluno na memoria
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
    
    
    //ROTINA ALUNO CONSULTAR PROPRIO TREINO
    public Treino consultarTreinoPorLoop(int idAlunoLogado) {
        TreinoDAO dao = new TreinoDAO();
        
        // 1. Busca todos os treinos (cada um tem um objeto Aluno dentro), DAO lista todos os treinos do BD
        List<Treino> todosTreinos = dao.listarTodosTreinos();
        
        // 2. Loop que itera sobre a lista fornecida pela DAO para encontrar o treino cujo aluno corresponde ao ID logado
        for (Treino t : todosTreinos) {
            
            // 3. Navegação: Pega o Aluno do treino, depois pega o ID dele
            if (t.getAluno().getId() == idAlunoLogado) {
                return t; // Achou!
            }
        }
        
        return null;
    }
    /**
    * Implementa a lógica de formatação de apresentação da descrição do treino.
    * Transforma uma string de descrição separada por ", " em uma lista formatada
    * com quebras de linha e traços.
    */
    public String gerarDescricaoFormatada(Treino treino) {
        if (treino == null || treino.getDescricao() == null) {
            return "Nenhum treino encontrado.";
        }

        String textoBruto = treino.getDescricao();

        // Troca a vírgula + espaço (", ") por uma quebra de linha + traço ("\n- ")
        String textoFormatado = "- " + textoBruto.replace(", ", "\n- ");

        return textoFormatado;
    }
}
