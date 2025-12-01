package dao;

import java.sql.*;
import banco.DBConnection;
import model.Matricula;

public class MatriculaDAO {
    private Connection conexao;

    public MatriculaDAO() {
        this.conexao = DBConnection.getConnection();
    }

    public boolean salvar(Matricula matricula) {
        String sql = "{call sp_AdicionarMatricula(?, ?, ?, ?)}";
        try {
            CallableStatement stmt = this.conexao.prepareCall(sql);
            // Aqui desconstruímos o objeto para enviar ao banco relacional
            stmt.setInt(1, matricula.getAluno().getId());
            stmt.setInt(2, matricula.getPlano().getIdPlano());
            stmt.setDate(3, java.sql.Date.valueOf(matricula.getDataInicio()));
            stmt.setDate(4, java.sql.Date.valueOf(matricula.getDataFim()));
            
            stmt.execute();
            stmt.close();
            return true; // Sucesso
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Falha
        }
    }
}