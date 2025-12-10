package model;

public class Gerente extends Funcionario {
	
	private String nivelAcesso;
	private Double bonificacao;
	
	//getters e setters
	public String getNivelAcesso() {
		return nivelAcesso;
	}
	public void setNivelAcesso(String nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}
	public Double getBonificacao() {
		return bonificacao;
	}
	public void setBonificacao(Double bonificacao) {
		this.bonificacao = bonificacao;
	}

}
