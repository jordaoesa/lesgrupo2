package br.edu.ufcg.gerenciar;

import android.text.Editable;
import android.widget.Toast;

public class Atividade {

	private String nomeAtividade = "";
	private String numeroSeries = "";
	private String numeroRepeticoes = "";
	private String horaDeExecucao = "";
	private String minDeExecucao = "";
	private String segDeExecucao = "";
	private String observacaoAtividade = "";
	private String diaSemana = "";
	private int idAluno;
	private int idAtividade;
	

	public Atividade(String nomeAtividade, String numeroSeries,String numeroRepeticoes, String horaDeExecucao,
					 String minDeExecucao, String segDeExecucao,String observacaoAtividade,String diaSemana, int idAluno) throws Exception {
		verificaDadosEntrada(nomeAtividade, numeroSeries, numeroRepeticoes, horaDeExecucao, minDeExecucao, segDeExecucao, observacaoAtividade);
		this.nomeAtividade = nomeAtividade;
		this.numeroSeries = numeroSeries;
		this.numeroRepeticoes = numeroRepeticoes;
		this.horaDeExecucao = horaDeExecucao;
		this.minDeExecucao = minDeExecucao;
		this.segDeExecucao = segDeExecucao;
		this.observacaoAtividade = observacaoAtividade;
		this.diaSemana = diaSemana;
		this.idAluno = idAluno;
	}
	
	public Atividade(int idAtividade,String nomeAtividade, String numeroSeries,String numeroRepeticoes, String horaDeExecucao,
			 String minDeExecucao, String segDeExecucao,String observacaoAtividade,String diaSemana, int idAluno) throws Exception {
		verificaDadosEntrada(nomeAtividade, numeroSeries, numeroRepeticoes, horaDeExecucao, minDeExecucao, segDeExecucao, observacaoAtividade);
		this.nomeAtividade = nomeAtividade;
		this.numeroSeries = numeroSeries;
		this.numeroRepeticoes = numeroRepeticoes;
		this.horaDeExecucao = horaDeExecucao;
		this.minDeExecucao = minDeExecucao;
		this.segDeExecucao = segDeExecucao;
		this.observacaoAtividade = observacaoAtividade;
		this.diaSemana = diaSemana;
		this.idAluno = idAluno;
		this.idAtividade= idAtividade;
	}
		
	public String getAtividadeResume() {
		return this.nomeAtividade+"\n"+numeroSeries+"/"+numeroRepeticoes+"/"+horaDeExecucao+":"+minDeExecucao+":"+segDeExecucao;
	}
	public String getAtividadeFull() {
		return "*Nome da Atividade:\n"+
				this.nomeAtividade+"\n"+
				"*Numero de Séries: " + this.numeroSeries+"\n"+
				"*Numero de Repetições: " + this.numeroRepeticoes+"\n"+
				"*Tempo de Execução do treino:\n"+
				this.horaDeExecucao+"h "+this.minDeExecucao+"m "+this.segDeExecucao+"s \n\n"+
				"*Observação:\n"+
				this.observacaoAtividade;
				
	}
	private void verificaDadosEntrada(String nomeAtividade, String numeroSeries,String numeroRepeticoes, String horaDeExecucao,
			 String minDeExecucao, String segDeExecucao,String observacaoAtividade) throws Exception {
		
		if(nomeAtividade==null || nomeAtividade.trim().equals("")){
			throw new Exception("Nome da Atividade inválido");
		}else if(numeroSeries==null || numeroSeries.trim().equals("") || !verificaNumMaiorQueZero(numeroSeries)){
			throw new Exception("Número de séries inválido");
		}else if(numeroRepeticoes==null || numeroRepeticoes.trim().equals("") || !verificaNumMaiorQueZero(numeroRepeticoes)){
			throw new Exception("Número de repetições inválido");
		}else if(horaDeExecucao==null || horaDeExecucao.trim().equals("") || !verificaNumMaiorQueZero(horaDeExecucao)){
			throw new Exception("Horas de execução inválida");
		}else if(minDeExecucao==null || minDeExecucao.trim().equals("") || !verificaNumMaiorQueZero(minDeExecucao)){
			throw new Exception("Minutos de execução inválido");
		}else if(segDeExecucao==null || segDeExecucao.trim().equals("") || !verificaNumMaiorQueZero(segDeExecucao)){
			throw new Exception("Segundos de execução inválido");
		}else if(observacaoAtividade==null){
			throw new Exception("Observação inválida");
		}
	}
	
	private boolean verificaNumMaiorQueZero(String num) {
		try {
			int aux = Integer.parseInt(num);
			if(aux>=0)return true;
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
	public String getNomeAtividade() {
		return nomeAtividade;
	}
	public String getNumeroSeries() {
		return numeroSeries;
	}
	public String getNumeroRepeticoes() {
		return numeroRepeticoes;
	}
	public String getHoraDeExecucao() {
		return horaDeExecucao;
	}
	public String getMinDeExecucao() {
		return minDeExecucao;
	}
	public String getSegDeExecucao() {
		return segDeExecucao;
	}
	public String getObservacaoAtividade() {
		return observacaoAtividade;
	}
	public String getDiaSemana() {
		return diaSemana;
	}
	public int getIdAluno() {
		return idAluno;
	}
	public int getIdAtividade() {
		return this.idAtividade;
	}
	public void setNomeAtividade(String nomeAtividade) {
		this.nomeAtividade = nomeAtividade;
	}
	public void setNumeroSeries(String numeroSeries) {
		this.numeroSeries = numeroSeries;
	}
	public void setNumeroRepeticoes(String numeroRepeticoes) {
		this.numeroRepeticoes = numeroRepeticoes;
	}
	public void setHoraDeExecucao(String horaDeExecucao) {
		this.horaDeExecucao = horaDeExecucao;
	}
	public void setMinDeExecucao(String minDeExecucao) {
		this.minDeExecucao = minDeExecucao;
	}
	public void setSegDeExecucao(String segDeExecucao) {
		this.segDeExecucao = segDeExecucao;
	}
	public void setObservacaoAtividade(String observacaoAtividade) {
		this.observacaoAtividade = observacaoAtividade;
	}
	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	public void setIdAluno(int idAluno) {
		this.idAluno = idAluno;
	}

	
	
	

}
