package dao;
import model.Plano;

public class PlanoDAO {
    public Plano buscarPorId(int id) {
        // Mock: Simula planos no banco
        if (id == 1) return new Plano(1, "Musculação Mensal", 89.90);
        if (id == 2) return new Plano(2, "Plano Anual Gold", 79.90);
        return null; // Não achou
    }
}