package br.edu.ufcg.gerenciar;

import java.util.ArrayList;

import br.edu.ufcg.exercicio.Atividade;

public class Treino {

	private String nomeAtividade;
	private ArrayList<Atividade> exercicios = new ArrayList<Atividade>();
	private String id;
	
	public Treino(String nomeAtividade){
		this.nomeAtividade = nomeAtividade;
		for(Atividade e:exercicios){
			id+=e.getID();
		}
	}
	public Treino(String nomeAtividade,ArrayList<Atividade> exercicios){
		this.exercicios = exercicios;
		this.nomeAtividade = nomeAtividade;
		for(Atividade e:exercicios){
			id+=e.getID();
		}
	}
	
	
	public void adicionarExercicio(Atividade exercicio){
		if(exercicio!= null){
			exercicios.add(exercicio);
		}
	}
	
	public boolean removerExercicio(String id){
		boolean removido = false;
		for(Atividade exercicio:exercicios){
			if(exercicio.getID().equals(id)){
				return exercicios.remove(exercicio);
			}
		}
		return removido;
	}
	
	public ArrayList<Atividade> getAtividade(){
		return this.exercicios;
	}
	
	public Object getID() {
		return id;
	}
	
	public String toString(){
		String retorno = "Nome Atividade: "+nomeAtividade+"\nLista de Exercícios:\n\n";
		for(Atividade exer:exercicios){
			retorno = retorno +exer.toString()+"\n\n";
		}
		return retorno;
	}
	

	
}
