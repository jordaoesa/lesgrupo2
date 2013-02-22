package br.edu.ufcg.exercicio;

public class Exercicio {

	public String nomeExercicio;
	public String nomeMaquina;
	public GrupoExercicio grupoExercicio;
	public int series;
	public int repeticoes;
	public int peso;
	public String observacao;
	public String id = nomeExercicio+nomeMaquina+observacao;
	
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
	
	public String getID(){
		return id;
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
		return "Exercicio \n" +
				"Nome do Exercício: " + nomeExercicio + "\n"+
				"Nome da Máquina: " + nomeMaquina + "\n"+
				"Grupo Muscular: " + grupoExercicio+ "\n"+
				"Quantidade de Séries: " + series +"\n"+
				"Repetições por Série: " + repeticoes +"\n"+ 
				"Peso na Máquina: " + peso +"\n"+ 
				"Observação: " + observacao+"\n";
	}
	
	
	
	
}
