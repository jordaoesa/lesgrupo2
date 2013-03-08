package br.edu.ufcg.gerenciar;

import java.util.ArrayList;

public class Ficha {
	
	private String nomeFicha;
	private ArrayList<Treino> atividades;
	private String id;
	
	public Ficha(String nomeFicha){
		this.nomeFicha = nomeFicha;
	}
	public Ficha(String nomeFicha,ArrayList<Treino> atividades){
		this.atividades = atividades;
		this.nomeFicha = nomeFicha;
	}
	public void adicionarAtividade(Treino atividade){
		if(atividade!= null){
			atividades.add(atividade);
		}
	}
	
	public boolean removerAtividade(String id){
		boolean removido = false;
		for(Treino a:atividades){
			if(a.getId().equals(id)){
				return atividades.remove(a);
			}
		}
		return removido;
	}
	
	public ArrayList<Treino> getFicha(){
		return this.atividades;
	}
	
	public String getID(){
		return id;
	}
	
	public String toString(){
		String retorno = "Nome Ficha: "+nomeFicha+"\nLista de Atividades:\n\n";
		for(Treino a:atividades){
			retorno = retorno +a.toString()+"\n\n";
		}
		return retorno;
	}
	
	

}
