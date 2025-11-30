package dao;

import java.util.ArrayList;
import java.util.List;
import model.Aluno;
import model.Treino;

public class TreinoDAO {

    public List<Treino> listarTodosTreinos() {
        // Cria uma lista vazia
        List<Treino> lista = new ArrayList<>();

        // --- MOCK: Criando dados falsos para teste ---
        
        // Cria alunos (tem que ter o mesmo ID que vamos digitar no login)
        Aluno a1 = new Aluno(1, "João da Silva", "123");
        Aluno a2 = new Aluno(2, "Maria Oliveira", "124");

        // Cria treinos associados a esses alunos
        Treino t1 = new Treino(100, a1, "TREINO A (Hipertrofia):\n- Supino Reto: 4x10\n- Crucifixo: 3x12\n- Tríceps Corda: 4x15");
        Treino t2 = new Treino(101, a2, "TREINO B (Emagrecimento):\n- Esteira: 20min\n- Agachamento: 4x15\n- Polichinelo: 3x50");

        // Adiciona na lista
        lista.add(t1);
        lista.add(t2);

        return lista;
    }
}