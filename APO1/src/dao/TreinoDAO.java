package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import banco.DBConnection;
import model.Aluno;
import model.Treino;

public class TreinoDAO {

    private Connection conexao;

    public TreinoDAO() {
        this.conexao = DBConnection.getConnection();
    }


    // 1. Método chamado pelo TreinoControl para buscar o treino específico
    public Treino buscarTreinoAtual(int idAluno) {
        Treino treino = null;
        String sql = "{call sp_BuscarTreinoAtual(?)}";

        try {
            CallableStatement stmt = this.conexao.prepareCall(sql);
            stmt.setInt(1, idAluno);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Mapeando do Banco para o Objeto Java
                int idTreino = rs.getInt("idTreino");
                String descricao = rs.getString("descricao");
                
                // Convertendo java.sql.Date para java.time.LocalDate com verificação de nulo
                LocalDate dataInicio = rs.getDate("dataInicio") != null ? rs.getDate("dataInicio").toLocalDate() : null;
                LocalDate dataFim = rs.getDate("dataFim") != null ? rs.getDate("dataFim").toLocalDate() : null;

                treino = new Treino(idTreino, null, descricao, dataInicio, dataFim);
            }
            
            stmt.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Erro ao buscar treino: " + e.getMessage());
            e.printStackTrace();
        }

        return treino; // Retorna o objeto Treino preenchido ou null se não achar
    }
    
 // Método listarTodos() dentro do TreinoDAO

    public Treino listarTreino(Aluno aluno) {
    	int alunoID = aluno.getId();
        String sql = "{call sp_BuscarProprioTreino(?)}"; 
        Treino t1 = null;

        try {
            CallableStatement stmt = this.conexao.prepareCall(sql);
            stmt.setInt(1,alunoID);//Troca o ? pela var
            ResultSet rs = stmt.executeQuery();//Executa a query e armazena o resultado
            rs.next();//Aqui sem isso o cursosr aponta para o HEADEr


                int idTreino = rs.getInt("idTreino");
                String descricao = rs.getString("descricao");
                
                // 1. Pegamos o ID que veio do banco
                
                // 2. Criamos um Aluno "Proxy" (Só com ID, o resto null)
                // para vincular o treino a esse ID sem precisar buscar nome/cpf 

                LocalDate inicio = rs.getDate("dataInicio") != null ? rs.getDate("dataInicio").toLocalDate() : null;
                LocalDate fim = rs.getDate("dataFim") != null ? rs.getDate("dataFim").toLocalDate() : null;

                // 3. Passamos o objeto aluno para o treino
                t1 = new Treino(idTreino, descricao, inicio, fim, aluno);
                
            
            stmt.close(); rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return  t1;
    }
    
}