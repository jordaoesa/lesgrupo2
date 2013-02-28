package br.edu.ufcg.fachada;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import br.edu.ufcg.aluno.Aluno;
import br.edu.ufcg.sgbd.DB;

public class AlunoFachada {
	
	private final String TABLE_NAME = "TABLE_ALUNO"; 
	private DB db;
	
	public AlunoFachada(DB db) {
		this.db = db;
	}
	
	public void adicionarAluno(Aluno aluno) {
		ContentValues values = new ContentValues();
		values.put("nome", aluno.getNome());
		values.put("endereco", aluno.getEndereco());
		values.put("sexo", aluno.getSexo());
		values.put("telefone", aluno.getTelefone());
		values.put("idade", aluno.getIdade());
		
		db.insertValues(TABLE_NAME, values);
		db.close();
	}
	
	public List<Aluno> getAlunos() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		Cursor cursor = db.getReadableDatabase().query(TABLE_NAME, null, null, null, null, null, null);
		try{
			if(cursor != null){
				while(cursor.moveToNext()){
					Aluno aluno = new Aluno(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5));
					alunos.add(aluno);
				}
			}
		}catch(Exception e){
			e.getStackTrace();
		}finally{
			cursor.close();
		}
		Collections.sort(alunos);
		return alunos;
	}
	
	public String[] getOnlyNamesOfAlunos(){
		List<Aluno> alunos = getAlunos();
		String nomes[] = new String[alunos.size()];
		for(int i=0; i<alunos.size(); i++){
			nomes[i] = alunos.get(i).getNome();
		}
		return nomes;
	}
	
	public Integer[] getOnlyIdsOfAlunos(){
		List<Aluno> alunos = getAlunos();
		Integer ids[] = new Integer[alunos.size()];
		for(int i=0; i<alunos.size(); i++){
			ids[i] = alunos.get(i).getId();
		}
		return ids;
	}
	
	public Integer getIdUltimoAlunoAdicionado(){
		List<Aluno> list = getAlunos();
		Integer id = list.get(list.size()-1).getId();
		return id;
	}

	public Aluno getAlunoFromId(int idAluno) {
		Aluno aluno = null;
		Cursor cursor = db.getReadableDatabase().query(TABLE_NAME, null, "id = " + idAluno, null, null, null, null);
		if(cursor != null){
			cursor.moveToFirst();
			aluno = new Aluno(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5));
		}
		return aluno;
	}
	
}
