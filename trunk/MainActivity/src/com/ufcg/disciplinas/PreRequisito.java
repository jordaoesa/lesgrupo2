package com.ufcg.disciplinas;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PreRequisito {

	private Map<String, List<String>> requisitos;

	private static PreRequisito instance;

	private PreRequisito(){
		requisitos = new HashMap<String, List<String>>();
	}

	public static PreRequisito getInstance(){
		if(instance == null){
			instance = new PreRequisito();
		}
		return instance;
	}

	public void add(String disciplina, String requisito){
		List<String> disciplinasPreRequisitos = new LinkedList<String>();
		if(requisitos.containsKey(disciplina)){
			disciplinasPreRequisitos = requisitos.get(disciplina);
			disciplinasPreRequisitos.add(requisito);
			requisitos.put(disciplina, disciplinasPreRequisitos);
		}else{
			disciplinasPreRequisitos.add(requisito);
			requisitos.put(disciplina, disciplinasPreRequisitos);
		}
	}
	
	public Map<String, List<String>> getMap(){
		return this.requisitos;
	}

	private void addRequisito(Disciplina disciplina) {
		List<Disciplina> requisitos = new LinkedList<Disciplina>();
	}

}
