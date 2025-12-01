package model;

import java.time.LocalDate;
import java.util.ArrayList; // Importação necessária para inicializar a lista
import java.util.List;

public class Matricula {
    private int idMatricula;
    private Aluno aluno;
    private Plano plano;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Boolean status; // Sugestão: true = Ativa, false = Inativa/Cancelada
    
    private List<Pagamento> pagamentos;

    // Construtor
    public Matricula(Aluno aluno, Plano plano, LocalDate dataInicio) {
        this.aluno = aluno;
        this.plano = plano;
        this.dataInicio = dataInicio;
        this.dataFim = dataInicio.plusYears(1);
    
        
        // Inicializa a lista vazia para evitar erro ao adicionar pagamentos depois
        this.pagamentos = new ArrayList<>();
    }

    // --- GETTERS E SETTERS ---

    // Aluno
    public Aluno getAluno() {
        return aluno;
    }
    
    

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    
    //id
    
    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int id) {
        this.idMatricula = id;
    }


    // Plano
    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    // Data Inicio
    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    // Data Fim
    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    // Status
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    // Pagamentos
    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }
}