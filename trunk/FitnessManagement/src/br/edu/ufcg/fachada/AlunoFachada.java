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
		values.put("sexo", aluno.getSexo());
		
		db.insertValues(TABLE_NAME, values);
		db.close();
	}
	
	public List<Aluno> getAlunos() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		Cursor cursor = db.getReadableDatabase().query(TABLE_NAME, null, null, null, null, null, null);
		try{
			if(cursor != null){
				while(cursor.moveToNext()){
					Aluno aluno = new Aluno(cursor.getInt(0), cursor.getString(1), null, cursor.getString(2));
					alunos.add(aluno);
				}
			}
		}catch(Exception e){
			e.getStackTrace();
		}finally{
			cursor.close();
		}
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
//		System.out.println("ALUNOS>>>: " + alunos);//TODO
		Integer ids[] = new Integer[alunos.size()];
		for(int i=0; i<alunos.size(); i++){
			ids[i] = alunos.get(i).getId();
		}
		return ids;
	}
	
	public Integer getIdUltimoAlunoAdicionado(){
		List<Aluno> list = getAlunos();
//		for(Aluno a : list) System.out.print("antes>> "+a.getId() + " ");
		Comparator<Aluno> c = new Comparator<Aluno>() {
			@Override
			public int compare(Aluno lhs, Aluno rhs) {
				if(lhs.getId() < rhs.getId()) return -1;
				else if(lhs.getId() > rhs.getId()) return 1;
				return 0;
			}
		};
		Collections.sort(list, c);
//		for(Aluno a : list) System.out.print("depois>> "+a.getId() + " ");
		
		Integer id = list.get(list.size()-1).getId();
		return id;
	}
	
}
