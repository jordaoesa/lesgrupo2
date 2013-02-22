package br.edu.ufcg.aluno;

public class Aluno {
	
	private Integer id;
	private String nome;
	private String endereco;
	private String sexo;
	
	public Aluno() {
	}
	
	public Aluno(String nome, String endereco, String sexo){
		this.nome = nome;
		this.endereco = endereco;
		this.sexo = sexo;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Nome: " + getNome() + "\nEndere�o: " + getEndereco() + "\nSexo: " + getSexo();
	}

}