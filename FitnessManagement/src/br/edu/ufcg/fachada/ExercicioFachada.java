package br.edu.ufcg.fachada;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

import br.edu.ufcg.sgbd.DB;

public class ExercicioFachada {

	private static final String TABLE_NAME = "TABLE_EXERCICIOS";
	private DB db;
	
	public ExercicioFachada(DB db) {
		this.db = db;
	}

	public void adicionaExercicio( String nomeExercicio){
		ContentValues values = new ContentValues();
		values.put("nomeExercicio", nomeExercicio);
		db.insertValues(TABLE_NAME, values);
		db.close();
	}
	
	public List<String> getDadosExercicios() {
		Cursor cursor = db.getReadableDatabase().query(TABLE_NAME, null, null, null, null, null, null);
		List<String> exercicio = new ArrayList<String>();
		try{
			while(cursor.moveToNext()){
				exercicio.add(cursor.getString(1));
			}
		}catch(Exception e){
			e.getStackTrace();
		}finally{
			cursor.close();
		}
		return exercicio;
	}
	
}
