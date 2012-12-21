package com.ufcg.disciplinas;

import java.util.LinkedList;
import java.util.List;

public class Disciplina {

	private String nome;
	private int semestre;
	private boolean cursada;
	private boolean cursando;
	private List<Disciplina> preRequisitos;
	
	public Disciplina(String nome){
		this.nome = nome;
		this.cursada = false;
		this.cursando = false;
		preRequisitos = new LinkedList<Disciplina>();
	}
	
	public void addPreRequisito(String requisito){
		preRequisitos.add(new Disciplina(requisito));
	}
	public String getNome(){
		return this.nome;
	}
	public List<Disciplina> getPreRequisitos(){
		return this.preRequisitos;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Disciplina))
			return false;
		Disciplina other = (Disciplina) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public boolean isCursada() {
		return cursada;
	}

	public void setCursada(boolean cursada) {
		this.cursada = cursada;
	}

	public boolean isCursando() {
		return cursando;
	}

	public void setCursando(boolean cursando) {
		this.cursando = cursando;
	}

}
