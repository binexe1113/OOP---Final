package dao;

import java.sql.*;
import banco.DBConnection;
import model.Plano;

public class PlanoDAO {
    private Connection conexao;

    public PlanoDAO() {
        this.conexao = DBConnection.getConnection();
    }

    public Plano buscarPorId(int idPlano) {
        Plano plano = null;
        String sql = "{call sp_BuscarPlanoPorID(?)}";
        try {
            CallableStatement stmt = this.conexao.prepareCall(sql);
            stmt.setInt(1, idPlano);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                plano = new Plano(
                    rs.getInt("idPlano"),
                    rs.getString("nome"),
                    rs.getDouble("preco")
                );
            }
            stmt.close(); rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plano;
    }
}