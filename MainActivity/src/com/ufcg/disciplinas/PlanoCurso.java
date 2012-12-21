package com.ufcg.disciplinas;

import java.util.List;

import java.util.LinkedList;

public class PlanoCurso {

	private List<Disciplina> disciplinasObrigatorias;
	private static  PlanoCurso instance;
	private PlanoCurso(){
		this.disciplinasObrigatorias = new LinkedList<Disciplina>();
	}
	public static PlanoCurso getInstance(){
		if(instance == null){
			instance = new PlanoCurso();
		}
		return instance;
	}
	
	public List<Disciplina> getObrigatorias(){
		return this.disciplinasObrigatorias;
	}
//	public void addObrigatorias(String nomeDisciplina){
//		this.disciplinasObrigatorias.add(new Disciplina(nomeDisciplina));
//	}
	public void addObrigatorias(Disciplina disciplina){
		this.disciplinasObrigatorias.add(disciplina);
	}
	public void addObrigatorias(String nomeDisciplina, int semestre, int creditos){
		Disciplina disciplina = new Disciplina(nomeDisciplina, creditos);
		disciplina.setSemestre(semestre);
		this.disciplinasObrigatorias.add(disciplina);
	}

	public Disciplina getObrigatorias(String nomeDisciplina){
		for (Disciplina disciplina : disciplinasObrigatorias) {
			if(nomeDisciplina.equals(disciplina.getNome())){
				return disciplina;
			}
		}
		return null;
	}
	
	public void setDisciplinaCursada(String nomeDisciplina, boolean cursada){
		getObrigatorias(nomeDisciplina).setCursada(cursada);
	}
	public void setSemestreDisciplina(String nomeDisciplina, String novoSemestre){
		getObrigatorias(nomeDisciplina).setSemestre(Integer.parseInt(novoSemestre));
	}
	
	
}
