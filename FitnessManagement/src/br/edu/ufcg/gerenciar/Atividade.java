package br.edu.ufcg.gerenciar;

import java.util.ArrayList;

import br.edu.ufcg.exercicio.Exercicio;

public class Atividade {

	private String nomeAtividade;
	private ArrayList<Exercicio> exercicios = new ArrayList<Exercicio>();
	private String id;
	
	public Atividade(String nomeAtividade){
		this.nomeAtividade = nomeAtividade;
		for(Exercicio e:exercicios){
			id+=e.getID();
		}
	}
	public Atividade(String nomeAtividade,ArrayList<Exercicio> exercicios){
		this.exercicios = exercicios;
		this.nomeAtividade = nomeAtividade;
		for(Exercicio e:exercicios){
			id+=e.getID();
		}
	}
	
	
	public void adicionarExercicio(Exercicio exercicio){
		if(exercicio!= null){
			exercicios.add(exercicio);
		}
	}
	
	public boolean removerExercicio(String id){
		boolean removido = false;
		for(Exercicio exercicio:exercicios){
			if(exercicio.getID().equals(id)){
				return exercicios.remove(exercicio);
			}
		}
		return removido;
	}
	
	public ArrayList<Exercicio> getAtividade(){
		return this.exercicios;
	}
	
	public Object getID() {
		return id;
	}
	
	public String toString(){
		String retorno = "Nome Atividade: "+nomeAtividade+"\nLista de Exercícios:\n\n";
		for(Exercicio exer:exercicios){
			retorno = retorno +exer.toString()+"\n\n";
		}
		return retorno;
	}
	

	
}
