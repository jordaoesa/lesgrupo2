package br.edu.ufcg.fachada;

import android.content.ContentValues;
import android.database.Cursor;
import br.edu.ufcg.sgbd.DB;

public class MaquinaFachada {

	private static final String TABLE_NAME = "TABLE_MAQUINAS";
	private DB db;
	
	public MaquinaFachada(DB db) {
		this.db = db;
	}

	public void adicionaMaquina( String nomeMaquina){
		ContentValues values = new ContentValues();
		values.put("nomeMaquina", nomeMaquina);
		db.insertValues(TABLE_NAME, values);
	}
	
	public String getDadosMaquinas() {
		Cursor cursor = db.getReadableDatabase().query(TABLE_NAME, null, null, null, null, null, null);
		String s ="";
		try{
			while(cursor.moveToNext()){
				
				s = s+ cursor.getInt(0) +  cursor.getString(1);
				
			}
		}catch(Exception e){
			e.getStackTrace();
		}finally{
			cursor.close();
		}
		return s;
	}
	
}