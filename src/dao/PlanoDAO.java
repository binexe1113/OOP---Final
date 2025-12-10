package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import banco.DBConnection;
import model.Plano;

public class PlanoDAO {
    private Connection conexao;

    public PlanoDAO() {
        this.conexao = DBConnection.getConnection();
    }
    
 // Dentro de PlanoDAO.java

    public List<Plano> listarTodos() {
        List<Plano> listaPlanos = new ArrayList<>();
        String sql = "SELECT * FROM Plano"; // 

        try {
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Plano plano = new Plano();
                
                plano.setIdPlano(rs.getInt("idPlano")); 
                plano.setNome(rs.getString("nome"));   
                plano.setPreco(rs.getDouble("preco")); 
                
                listaPlanos.add(plano);
            }
            
            rs.close();
            stmt.close();

        } catch (Exception e) {
            System.err.println("Erro ao listar planos: " + e.getMessage());
            e.printStackTrace();
        }

        return listaPlanos;
    }
}