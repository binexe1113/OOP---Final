package dao;

import java.sql.*;
import java.time.LocalDate; // Necessário para converter Date do SQL
import banco.DBConnection;
import model.Pagamento;

public class PagamentoDAO {
    private Connection conexao;

    public PagamentoDAO() {
        this.conexao = DBConnection.getConnection();
    }
    
    //Método busca o pagamento a partir do ID no BD
    public Pagamento buscarPorId(int idPagamento) {
        //Começa com NULL, para o case de não achar NADA!
        Pagamento pagamento = null;
        
        // O '?' é um placeholder para o parâmetro que passaremos depois.
        String sql = "{call sp_BuscarPagamentoPorID(?)}";
        
        try {
            // Prepara a chamada da procedure usando a conexão atual
            // CallableStatement é usado especificamente para Stored Procedures
            CallableStatement stmt = this.conexao.prepareCall(sql);
            
            // Substitui o primeiro '?' pelo valor da variável idPagamento (int)
            stmt.setInt(1, idPagamento);
            
            //Executa a consulta e armazena o resultado no ResultSet(tabela temp na memoria)
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) { //Se entrou aqui, encontrou o pagamento
                pagamento = new Pagamento( //Cria o objeto pegando os dados das colunas do banco
                    rs.getInt("idPagamento"),       // Coluna idPagamento
                    rs.getDate("dataPagamento").toLocalDate(), // Convertendo SQL Date para LocalDate
                    rs.getDate("dataVencimento").toLocalDate(), // Convertendo SQL Date para LocalDate
                    rs.getDouble("valor"),          // Coluna valor
                    rs.getString("metodoPagamento"), // Coluna metodoPagamento
                    rs.getBoolean("status")         // Coluna status (0 ou 1 no banco vira false/true)
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
        return pagamento;
    }
}