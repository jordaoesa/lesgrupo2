package br.edu.ufcg.fachada;

import java.util.List;

import br.edu.ufcg.aluno.Dados;
import br.edu.ufcg.sgbd.DB;

public class DadosFachada {
	
	private DB db;
	
	public DadosFachada(DB db) {
		this.db = db;
	}

	public void adicionaDadosDoAluno(Integer id, Dados dados){
		//TODO
	}
	
	public List<Dados> getDadosDoAluno(Integer id) {
		//TODO
		return null;
	}
}
