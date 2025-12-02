package dao;

import java.sql.*;
import banco.DBConnection;
import model.Plano;

public class PlanoDAO {
    private Connection conexao;

    public PlanoDAO() {
        this.conexao = DBConnection.getConnection();
    }
    
    //Método busca o plano a partir do ID no BD
    public Plano buscarPorId(int idPlano) {
    	//Começa com NULL, para o case de não achar NADA!
        Plano plano = null;
        
        // O '?' é um placeholder para o parâmetro que passaremos depois.
        String sql = "{call sp_BuscarPlanoPorID(?)}";
        try {
        	// Prepara a chamada da procedure usando a conexão atual
            // CallableStatement é usado especificamente para Stored Procedures
            CallableStatement stmt = this.conexao.prepareCall(sql);
         // Substitui o primeiro '?' pelo valor da variável idPlano (int)
            stmt.setInt(1, idPlano);
            //Executa a consulta e armazena o resultado no ResultSet(tabela temp na memoria)
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) { //Se entrou aqui, encontrou o plano
                plano = new Plano( //Cria o objeto pegando os dados das colunas do banco
                    rs.getInt("idPlano"), //Coluna idPlano
                    rs.getString("nome"), // Coluna nome
                    rs.getDouble("preco") // coluna preco
                );
            }
            //Fecha os recursos para liberar memoria e conxões no bd
            stmt.close(); 
            rs.close();
        } catch (SQLException e) {
        	//Se der erro IMPRIME no console
            e.printStackTrace();
        }
        //retorna o objeto montado (OU NULL)
        return plano;
    }
}