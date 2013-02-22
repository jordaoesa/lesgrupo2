package br.edu.ufcg.gerenciar;

import java.util.ArrayList;

import br.edu.ufcg.exercicio.Exercicio;

public class Ficha {
	
	private String nomeFicha;
	private ArrayList<Atividade> atividades = new ArrayList<Atividade>();
	private String id;
	
	public Ficha(){
		
	}
	public Ficha(ArrayList<Atividade> atividades){
		this.atividades = atividades;
	}
	public void adicionarAtividade(Atividade atividade){
		if(atividade!= null){
			atividades.add(atividade);
		}
	}
	
	public boolean removerAtividade(String id){
		boolean removido = false;
		for(Atividade a:atividades){
			if(a.getID().equals(id)){
				return atividades.remove(a);
			}
		}
		return removido;
	}
	
	public ArrayList<Atividade> getFicha(){
		return this.atividades;
	}
	
	public String getID(){
		return id;
	}
	
	public String toString(){
		String retorno = "Nome Ficha: "+nomeFicha+"\nLista de Atividades:\n\n";
		for(Atividade a:atividades){
			retorno = retorno +a.toString()+"\n\n";
		}
		return retorno;
	}
	
	

}
