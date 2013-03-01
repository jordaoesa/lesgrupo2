package br.edu.ufcg.gerenciar;

public class Atividade {

	private Integer id;
	private Integer idAluno;
	private Integer series;
	private Integer repeticoes;
	private Integer peso;
	private String nomeExercicio;
	private String nomeMaquina;
	private String grupoMuscular;
	private String observacao;
	
	public Atividade(int series, int repeticoes, int peso, String nomeExercicio, String nomeMaquina, String grupoMuscular, String observacao) {
		this.series = series;
		this.repeticoes = repeticoes;
		this.peso = peso;
		this.nomeExercicio = nomeExercicio;
		this.nomeMaquina = nomeMaquina;
		this.grupoMuscular = grupoMuscular;
		this.observacao = observacao;
	}
	
	public Atividade(Integer id, Integer idAluno, int series, int repeticoes, int peso, String nomeExercicio, String nomeMaquina, String grupoMuscular, String observacao) {
		this.id = id;
		this.idAluno = idAluno;
		this.series = series;
		this.repeticoes = repeticoes;
		this.peso = peso;
		this.nomeExercicio = nomeExercicio;
		this.nomeMaquina = nomeMaquina;
		this.grupoMuscular = grupoMuscular;
		this.observacao = observacao;
	}

	public String getNomeExercicio() {
		return nomeExercicio;
	}
	public String getNomeMaquina() {
		return nomeMaquina;
	}
	public String getGrupoMuscular() {
		return grupoMuscular;
	}
	public int getSeries() {
		return series;
	}
	public int getRepeticoes() {
		return repeticoes;
	}
	public int getPeso() {
		return peso;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setNomeExercicio(String nomeExercicio) {
		this.nomeExercicio = nomeExercicio;
	}
	public void setNomeMaquina(String nomeMaquina) {
		this.nomeMaquina = nomeMaquina;
	}
	public void setGrupoExercicio(String grupoExercicio) {
		this.grupoMuscular = grupoExercicio;
	}
	public void setSeries(int series) {
		this.series = series;
	}
	public void setRepeticoes(int repeticoes) {
		this.repeticoes = repeticoes;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Integer getIdAluno() {
		return idAluno;
	}
	public void setIdAluno(Integer idAluno) {
		this.idAluno = idAluno;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Exercicio \n" +
				"Nome do Exercício: " + nomeExercicio + "\n"+
				"Nome da Máquina: " + nomeMaquina + "\n"+
				"Grupo Muscular: " + grupoMuscular+ "\n"+
				"Quantidade de Séries: " + series +"\n"+
				"Repetições por Série: " + repeticoes +"\n"+ 
				"Peso na Máquina: " + peso +"\n"+ 
				"Observação: " + observacao+"\n";
	}

}
