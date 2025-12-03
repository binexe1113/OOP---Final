package model;

import java.util.List;

public class Academia {
	private int idAcademia;
	private String nome;
	private String endereço;
	private String telefone;
	
	//Listas e objetos
	
	private List<Aluno> alunos;
	private List<Professor> professores;
	private List<Funcionario> funcionarios;
	
	//Getters e setters
	public int getIdAcademia() {
		return idAcademia;
	}
	public void setIdAcademia(int idAcademia) {
		this.idAcademia = idAcademia;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereço() {
		return endereço;
	}
	public void setEndereço(String endereço) {
		this.endereço = endereço;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public List<Aluno> getAlunos() {
		return alunos;
	}
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	public List<Professor> getProfessores() {
		return professores;
	}
	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	
	

}
