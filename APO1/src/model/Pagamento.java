package model;

import java.time.LocalDate; // Usado para lidar com datas

public class Pagamento {

    // Atributos do Diagrama
    private int idPagamento;
    private LocalDate dataPagamento;
    private LocalDate dataVencimento;
    private double valor;
    private String metodoPagamento;
    private boolean status; // true = pago, false = pendente


    // Construtor Vazio
    public Pagamento() {
    }




    // Source > Generate Getters and Setters)
    
    public int getIdPagamento() { return idPagamento; }
    public void setIdPagamento(int idPagamento) { this.idPagamento = idPagamento; }

    public LocalDate getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(LocalDate dataPagamento) { this.dataPagamento = dataPagamento; }

    public LocalDate getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(LocalDate dataVencimento) { this.dataVencimento = dataVencimento; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public String getMetodoPagamento() { return metodoPagamento; }
    public void setMetodoPagamento(String metodoPagamento) { this.metodoPagamento = metodoPagamento; }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

}