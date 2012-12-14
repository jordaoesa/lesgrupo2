package com.ufcg.disciplinas;

import java.util.LinkedList;
import java.util.List;

public class Disciplina {

	private String nome;
	private List<Disciplina> preRequisitos;
	
	public Disciplina(String nome){
		this.nome = nome;
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

}
