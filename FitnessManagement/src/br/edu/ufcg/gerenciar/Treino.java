package br.edu.ufcg.gerenciar;

import java.util.ArrayList;
import java.util.List;


public class Treino {

	private Integer id;
	private Integer idAluno;
	private String nomeTreino;
	private List<Atividade> atividades;
	
	public Treino(){
		this.atividades = new ArrayList<Atividade>();
	}
	
	public Treino(String nomeTreino){
		this.atividades = new ArrayList<Atividade>();
		this.nomeTreino = nomeTreino;
	}
	
	public Treino(String nomeTreino, ArrayList<Atividade> atividades){
		this.atividades = atividades;
		this.nomeTreino = nomeTreino;
	}
	
	public void adicionarAtividade(Atividade atividade){
		if(atividade != null){
			atividades.add(atividade);
		}
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdAluno() {
		return idAluno;
	}
	public void setIdAluno(Integer idAluno) {
		this.idAluno = idAluno;
	}
	public String getNomeTreino() {
		return nomeTreino;
	}
	public void setNomeTreino(String nomeTreino) {
		this.nomeTreino = nomeTreino;
	}
	public List<Atividade> getAtividades() {
		return atividades;
	}
	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}
	
	
}
