package br.edu.ufcg.fachada;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import br.edu.ufcg.sgbd.DB;

public class FichaFachada {

	private static final String TABLE_NAME = "TABLE_FACHADA";
	private DB db;
	
	public FichaFachada(DB db) {
		this.db = db;
	}

	public void adicionaFicha( int idTreino){
		ContentValues values = new ContentValues();
		values.put("id_treino", idTreino);
		db.insertValues(TABLE_NAME, values);
		db.close();
	}
	
//	public List<String> getDadosExercicios() {
//		Cursor cursor = db.getReadableDatabase().query(TABLE_NAME, null, null, null, null, null, null);
//		List<String> exercicio = new ArrayList<String>();
//		try{
//			while(cursor.moveToNext()){
//				exercicio.add(cursor.getString(1));
//			}
//		}catch(Exception e){
//			e.getStackTrace();
//		}finally{
//			cursor.close();
//		}
//		return exercicio;
//	}
	
}
