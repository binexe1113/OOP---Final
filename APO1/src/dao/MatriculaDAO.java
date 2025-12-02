package dao;

import java.sql.*;
import banco.DBConnection;
import model.Matricula;

public class MatriculaDAO {
    private Connection conexao;

    public MatriculaDAO() {
        this.conexao = DBConnection.getConnection();
    }
    
    /**
     * Salva uma nova matrícula no banco de dados
     *
     * returnn true se a operação foi bem-sucedida, false caso contrário.
     */

    public boolean salvar(Matricula matricula) {
    	// Chamada SQL para a Stored Procedure responsável por adicionar/atualizar a matrícula.
        String sql = "{call sp_AdicionarMatricula(?, ?, ?, ?)}";
        try {
        	// Prepara a chamada de procedimento armazenado.
            CallableStatement stmt = this.conexao.prepareCall(sql);
            
            // Mapeamento dos atributos do objeto Matricula para os parâmetros da Stored Procedure.
            // Parâmetro 1: ID do Aluno (obtido do objeto aninhado)
            stmt.setInt(1, matricula.getAluno().getId());
            // Parâmetro 2: ID do Plano (obtido do objeto aninhado)
            stmt.setInt(2, matricula.getPlano().getIdPlano());
            // Parâmetro 3: Data de Início (conversão de LocalDate para java.sql.Date)
            stmt.setDate(3, java.sql.Date.valueOf(matricula.getDataInicio()));
       		 // Parâmetro 4: Data de Fim (conversão de LocalDate para java.sql.Date)
            stmt.setDate(4, java.sql.Date.valueOf(matricula.getDataFim()));
            
            // Executa a Stored Procedure
            stmt.execute();
            //Fecha o statement
            stmt.close();
            
            return true; // Sucesso
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Falha
        }
    }
    
    
    public boolean verificaMatriculaAtiva(int idAluno) {
        // Conta quantas matrículas ativas (status = 1) existem para esse aluno
        String sql = "SELECT COUNT(*) FROM matricula WHERE idAluno = ? AND status = 1";
        
        try {
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idAluno);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                // Pega o valor da contagem (primeira coluna)
                int quantidade = rs.getInt(1);
                
                // Fecha recursos para evitar vazamento de memória
                rs.close();
                stmt.close();
                
                // Se quantidade > 0, retorna true (TEM matrícula ativa)
                return quantidade > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao verificar matrícula ativa: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false; // Retorna false caso não ache nada ou dê erro
    }
    
}