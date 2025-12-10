package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import banco.DBConnection;
import model.Professor;

public class ProfessorDAO {
	
    private Connection conexao;


	public ProfessorDAO() {
        this.conexao = DBConnection.getConnection();
    }


    public Professor buscarPorCpf(String cpf) {
        Professor professor= null;
        String sql = "{call sp_BuscarProfessorPorCPF(?)}";

        try {
            CallableStatement stmt = this.conexao.prepareCall(sql);
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Cria o objeto Professor apenas com os dados essenciais 
            	professor = new Professor(
                    rs.getInt("idProf"),
                    rs.getString("nome"),
                    rs.getString("cpf")
                );
            }
            stmt.close(); rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(professor);//DEBUG
        return professor;
    }
}