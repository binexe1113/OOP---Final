package dao;

import java.util.ArrayList;
import java.util.List;
import model.Aluno;

public class AlunoDAO {

    // Método auxiliar para criar os dados falsos (assim não repetimos código)
    private List<Aluno> getListaSimulada() {
        List<Aluno> lista = new ArrayList<>();
        // Note que adicionei os CPFs no final (123, 456, etc)
        lista.add(new Aluno(1, "João da Silva", "123"));
        lista.add(new Aluno(2, "Maria Oliveira", "456"));
        lista.add(new Aluno(3, "Carlos Eduardo", "789"));
        return lista;
    }

    public List<Aluno> listarTodos() {
        return getListaSimulada();
    }

    // --- NOVO MÉTODO: BUSCA POR CPF (MOCK) ---
    public Aluno buscarPorCpf(String cpfProcurado) {
        List<Aluno> todos = getListaSimulada();
        
        // Percorre a lista procurando alguém com esse CPF
        for (Aluno a : todos) {
            if (a.getCpf().equals(cpfProcurado)) {
                return a; // Achou! Retorna o aluno
            }
        }
        
        return null; // Percorreu tudo e não achou ninguém
    }
    
    // Mantive o por ID caso o professor precise
    public Aluno buscarPorId(int id) {
        for (Aluno a : getListaSimulada()) {
            if (a.getId() == id) return a;
        }
        return null;
    }
}