package br.edu.ufcg.aluno;

public class Aluno implements Comparable<Aluno> {

	private Integer id;
	private Integer idade;
	private String nome;
	private String endereco;
	private String telefone;
	private String sexo;
	
	public Aluno() {
	}
	
	public Aluno(String nome, String endereco, String sexo, String telefone, Integer idade){
		this.nome = nome;
		this.endereco = endereco;
		this.sexo = sexo;
		this.telefone = telefone;
		this.idade = idade;
	}
	
	public Aluno(Integer id, String nome, String endereco, String sexo, String telefone, Integer idade){
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.sexo = sexo;
		this.telefone = telefone;
		this.idade = idade;
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
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	@Override
	public int compareTo(Aluno another) {
		if(getId() < another.getId()) return -1;
		else if(getId() > another.getId()) return 1;
		return 0;
	}

}
