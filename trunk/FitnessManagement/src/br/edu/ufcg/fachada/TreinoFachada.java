package br.edu.ufcg.fachada;

import android.content.ContentValues;
import br.edu.ufcg.sgbd.DB;

public class TreinoFachada {


		private static final String TABLE_NAME = "TABLE_TREINO";
		private DB db;
		
		public TreinoFachada(DB db) {
			this.db = db;
		}

		public void adicionaFicha( int idAtividade){
			ContentValues values = new ContentValues();
			values.put("id_atividade", idAtividade);
			db.insertValues(TABLE_NAME, values);
			
			System.out.println("adicionei a atividade tal...");
			db.close();
		}
		
//		public List<String> getDadosExercicios() {
//			Cursor cursor = db.getReadableDatabase().query(TABLE_NAME, null, null, null, null, null, null);
//			List<String> exercicio = new ArrayList<String>();
//			try{
//				while(cursor.moveToNext()){
//					exercicio.add(cursor.getString(1));
//				}
//			}catch(Exception e){
//				e.getStackTrace();
//			}finally{
//				cursor.close();
//			}
//			return exercicio;
//		}
	
	
}
