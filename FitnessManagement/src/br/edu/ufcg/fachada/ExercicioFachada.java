package br.edu.ufcg.fachada;


import android.content.ContentValues;
import android.database.Cursor;

import br.edu.ufcg.sgbd.DB;

public class ExercicioFachada {

	private static final String TABLE_NAME = "TABLE_EXERCICIOS";
	private DB db;
	
	public ExercicioFachada(DB db) {
		this.db = db;
	}

	public void adicionaExercicio(Integer id, String nomeExercicio){
		ContentValues values = new ContentValues();
		values.put("id_exercicio", id);
		values.put("nomeExercicio", nomeExercicio);
		db.insertValues(TABLE_NAME, values);
	}
	
	public String getDadosExercicio(Integer id) {
		Cursor cursor = db.getReadableDatabase().query(TABLE_NAME, null, "id_exercicio = " + id, null, null, null, null);
		String s = null;
		try{
			while(cursor.moveToNext()){
				 s ="";
				s = s+ cursor.getInt(0) +  cursor.getInt(1);
				
			}
		}catch(Exception e){
			e.getStackTrace();
		}finally{
			cursor.close();
		}
		return s;
	}
	
}
