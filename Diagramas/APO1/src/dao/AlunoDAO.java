package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import banco.DBConnection;
import model.Aluno;
import model.Treino;

public class AlunoDAO {

    private Connection conexao;

    public AlunoDAO() {
        this.conexao = DBConnection.getConnection();
    }
    
 // Dentro do AlunoDAO.java

    public List<Aluno> listarAlunosComTreino(int idProfessor) {
        List<Aluno> lista = new ArrayList<>();
        String sql = "{call sp_ListarAlunosETreinosPorProfessor(?)}";

        try {
            CallableStatement stmt = this.conexao.prepareCall(sql);
            stmt.setInt(1, idProfessor);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // 1. Cria o Aluno
                Aluno a = new Aluno(
                    rs.getInt("idAluno"),
                    rs.getString("nome"),
                    rs.getString("cpf")
                );

                // 2. Cria o Treino (usando os dados da mesma linha do SQL)
                // Lembre de tratar datas nulas se necessário
                java.sql.Date dbInicio = rs.getDate("dataInicio");
                java.sql.Date dbFim = rs.getDate("dataFim");

                Treino t = new Treino(
                    rs.getInt("idTreino"),
                    rs.getString("descricao"),
                    dbInicio != null ? dbInicio.toLocalDate() : null,
                    dbFim != null ? dbFim.toLocalDate() : null
                );

                // 3. ASSOCIAÇÃO: Coloca o treino dentro do aluno
                a.setTreino(t);

                // 4. Adiciona na lista
                lista.add(a);
            }
            stmt.close(); rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Aluno> listarAlunosPorProfessor(int idProfessor) {
        List<Aluno> listaAlunos = new ArrayList<>();
        
        // Chama a procedure que criamos acima
        String sql = "{call sp_ListarAlunosPorProfessor(?)}";

        try {
            CallableStatement stmt = this.conexao.prepareCall(sql);
            stmt.setInt(1, idProfessor); // Passa o ID do professor para o filtro

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Captura os dados retornados pelo SELECT da Procedure
                int id = rs.getInt("idAluno");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                // String email = rs.getString("email"); // Se quiser usar o email

                // Cria o objeto usando aquele construtor menor que fizemos
                Aluno aluno = new Aluno(id, nome, cpf);
                
                listaAlunos.add(aluno);
            }
            
            stmt.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Erro ao listar alunos: " + e.getMessage());
            e.printStackTrace();
        }

        return listaAlunos;
    }
    

    public Aluno buscarPorCpf(String cpf) {
        Aluno aluno = null;
        String sql = "{call sp_BuscarAlunoPorCPF(?)}";

        try {
            CallableStatement stmt = this.conexao.prepareCall(sql);
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Cria o objeto Aluno apenas com os dados essenciais para a matrícula
                aluno = new Aluno(
                    rs.getInt("idAluno"),
                    rs.getString("nome"),
                    rs.getString("cpf")
                );
            }
            stmt.close(); rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aluno;
    }
}