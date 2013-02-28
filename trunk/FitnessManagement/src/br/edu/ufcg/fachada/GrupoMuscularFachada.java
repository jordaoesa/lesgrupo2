package br.edu.ufcg.fachada;

import android.content.ContentValues;
import android.database.Cursor;
import br.edu.ufcg.sgbd.DB;

public class GrupoMuscularFachada {

	private static final String TABLE_NAME = "TABLE_GRUPO_MUSCULAR";
	private DB db;
	
	public GrupoMuscularFachada(DB db) {
		this.db = db;
	}

	public void adicionaGrupoMuscular( String nomeGrupoMuscular){
		ContentValues values = new ContentValues();
		values.put("nomeGrupoMuscular", nomeGrupoMuscular);
		db.insertValues(TABLE_NAME, values);
		db.close();
	}
	
	public String getDadosGrupoMusculares() {
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
