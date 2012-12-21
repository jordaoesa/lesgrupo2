package com.ufcg.disciplinas;

import java.util.LinkedList;
import java.util.List;

public class Disciplina {

	private String nome;
	private int semestre;
	private boolean cursada;
	private boolean cursando;
	private int creditos;
	private List<Disciplina> preRequisitos;
	
	public Disciplina(String nome, int creditos){
		this.nome = nome;
		this.creditos = creditos;
		this.cursada = false;
		this.cursando = false;
		preRequisitos = new LinkedList<Disciplina>();
	}
	
	public void addPreRequisito(String requisito, int creditos){
		preRequisitos.add(new Disciplina(requisito, creditos));
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
		if(this.semestre == 1){
			setCursando(true);
		}
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

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

}
