package br.edu.ufcg.fachada;

import java.util.List;

import android.content.ContentValues;
import br.edu.ufcg.aluno.Aluno;
import br.edu.ufcg.sgbd.DB;

public class AlunoFachada {
	
	private DB db;
	
	public AlunoFachada(DB db) {
		this.db = db;
	}
	
	public void adicionarAluno(Aluno aluno) {
		ContentValues values = new ContentValues();
		values.put("nome", aluno.getNome());
		values.put("endereco", aluno.getEndereco());
		values.put("sexo", aluno.getSexo());
		db.insertValues("aluno", values);
	}
	
	public List<Aluno> getAlunos() {
		//TODO
		return null;
	}
	
	public String[] getOnlyNamesOfAlunos(){
		//TODO
		return null;
	}
	
	public Integer[] getOnlyIdsOfAlunos(){
		//TODO
		return null;
	}
	
}
