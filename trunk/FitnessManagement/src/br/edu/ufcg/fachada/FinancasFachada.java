package br.edu.ufcg.fachada;

import android.content.ContentValues;
import android.database.Cursor;
import br.edu.ufcg.sgbd.DB;

public class FinancasFachada {

	private final String TABLE_NAME = "TABLE_FINANCAS"; 
	private DB db;
	
	public FinancasFachada(DB db) {
		this.db = db;
	}
	
	public void addDivida(Integer idAluno, Double valor) {
		Double dividaTotal = getDividaTotal(idAluno);
		ContentValues values = new ContentValues();
		values.put("id_aluno", idAluno);
		
		try{
			db.insertValues(TABLE_NAME, values);
		}catch(Exception e){
			System.out.println(">>> " + e.getMessage());
		}finally{
			values.put("valor", (dividaTotal+valor));
			db.updateTable(TABLE_NAME, values, "id_aluno = " + idAluno);
			db.close();
		}
		
	}

	public void quitaDivida(Integer idAluno, Double valor) {
		Double dividaTotal = getDividaTotal(idAluno);
		
		ContentValues values = new ContentValues();
		values.put("valor", (dividaTotal-valor));
		db.updateTable(TABLE_NAME, values, "id_aluno = " + idAluno);
		db.close();
	}

	public Double getDividaTotal(Integer idAluno) {
		Cursor cursor = db.getReadableDatabase().query(TABLE_NAME, new String[]{"valor"}, "id_aluno = " + idAluno, null, null, null, null);
		Double valor = null;
		if(cursor != null){
			cursor.moveToFirst();
			try{
				valor = cursor.getDouble(0);
			}catch(Exception e){
				valor = 0D;
				System.out.println(">>>> " + e.getMessage());
			}
		}
		return valor;
	}
	
	

}
