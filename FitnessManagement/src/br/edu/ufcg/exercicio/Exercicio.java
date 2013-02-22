package br.edu.ufcg.exercicio;

public class Exercicio {

	public String nomeExercicio;
	public String nomeMaquina;
	public GrupoExercicio grupoExercicio;
	public int series;
	public int repeticoes;
	public int peso;
	public String observacao;
	
	public Exercicio(String nomeExercicio, String nomeMaquina, GrupoExercicio grupoExercicio, int series, int repeticoes, int peso) {

		this.nomeExercicio = nomeExercicio;
		this.nomeMaquina = nomeMaquina;
		this.grupoExercicio = grupoExercicio;
		this.series = series;
		this.repeticoes = repeticoes;
		this.peso = peso;
	}

	public String getNomeExercicio() {
		return nomeExercicio;
	}

	public String getNomeMaquina() {
		return nomeMaquina;
	}

	public GrupoExercicio getGrupoExercicio() {
		return grupoExercicio;
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

	public void setGrupoExercicio(GrupoExercicio grupoExercicio) {
		this.grupoExercicio = grupoExercicio;
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

	@Override
	public String toString() {
		return "Exercicio " +
				"Nome do Exercício: " + nomeExercicio + 
				"Nome da Máquina: " + nomeMaquina + 
				"Grupo Muscular: " + grupoExercicio+ 
				"Quantidade de Séries: " + series +
				"Repetições por Série: " + repeticoes +
				"Peso na Máquina: " + peso + 
				"Observação: " + observacao;
	}
	
	
	
	
}
