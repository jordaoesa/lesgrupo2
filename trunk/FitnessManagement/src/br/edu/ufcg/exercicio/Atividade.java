package br.edu.ufcg.exercicio;

public class Atividade {

	public String nomeExercicio;
	public String nomeMaquina;
	public String grupoExercicio;
	public String series;
	public String repeticoes;
	public String peso;
	public String observacao;
	public String id = nomeExercicio+nomeMaquina+observacao;
	
	public Atividade(String nomeExercicio, String nomeMaquina, String grupoExercicio, String series, String repeticoes, String peso) {

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

	public String getGrupoExercicio() {
		return grupoExercicio;
	}

	public String getSeries() {
		return series;
	}

	public String getRepeticoes() {
		return repeticoes;
	}

	public String getPeso() {
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

	public void setGrupoExercicio(String grupoExercicio) {
		this.grupoExercicio = grupoExercicio;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public void setRepeticoes(String repeticoes) {
		this.repeticoes = repeticoes;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		return "Exercicio \n" +
				"Nome do Exerc�cio: " + nomeExercicio + "\n"+
				"Nome da M�quina: " + nomeMaquina + "\n"+
				"Grupo Muscular: " + grupoExercicio+ "\n"+
				"Quantidade de S�ries: " + series +"\n"+
				"Repeti��es por S�rie: " + repeticoes +"\n"+ 
				"Peso na M�quina: " + peso +"\n"+ 
				"Observa��o: " + observacao+"\n";
	}
	
	
	
	
}
