package model;

public class Plano {
    private int idPlano;
    private String nome;
    private double preco;
    private String descricao;
    private Boolean status;
    
    private Matricula matricula;
    
    

    public Plano(int idPlano, String nome, double preco, String descricao, Boolean status) {
        this.idPlano = idPlano;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.status = status;
    }
    
    //Construtor usado para montar o objeto plano que sera inerido dentro da matricula, as outras informações seriam irrelevantes
    public Plano(int id, String nome, double preco) {
        this.idPlano = id;
        this.nome = nome;
        this.preco = preco;
    }

    //=======GETTERS E SETTERS=========


	public int getIdPlano() {
		return idPlano;
	}



	public void setIdPlano(int idPlano) {
		this.idPlano = idPlano;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public double getPreco() {
		return preco;
	}



	public void setPreco(double preco) {
		this.preco = preco;
	}



	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	public Boolean getStatus() {
		return status;
	}



	public void setStatus(Boolean status) {
		this.status = status;
	}



	public Matricula getMatricula() {
		return matricula;
	}



	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}
}
