package dao;

import java.sql.*;
import java.time.LocalDate; // Necessário para converter Date do SQL
import java.util.ArrayList;
import java.util.List;

import banco.DBConnection;
import model.Pagamento;

public class PagamentoDAO {
    private Connection conexao;

    public PagamentoDAO() {
        this.conexao = DBConnection.getConnection();
    }
    


    public List<Pagamento> listarTodos() {
        List<Pagamento> listaPagamentos = new ArrayList<>();
        String sql = "SELECT * FROM Pagamento"; // '

        try {
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pagamento pag = new Pagamento();
                
                pag.setValor(rs.getInt("valor")); 
                
                pag.setMetodoPagamento(rs.getString("metodoPagamento")); 
                
             // O rs.getTimestamp pega data e hora. O .toLocalDateTime converte para o Java 8+.
                java.sql.Timestamp ts = rs.getTimestamp("dataPagamento");      
                pag.setDataPagamento(ts.toLocalDateTime());
                
                //ADICIONAR NA LISTA NE 
                listaPagamentos.add(pag);
            }
            
            rs.close();
            stmt.close();

        } catch (Exception e) {
            System.err.println("Erro ao listar pagamentos: " + e.getMessage());
            e.printStackTrace();
        }

        return listaPagamentos;
    	}
    }