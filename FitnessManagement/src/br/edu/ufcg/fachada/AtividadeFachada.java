package br.edu.ufcg.fachada;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import br.edu.ufcg.gerenciar.Atividade;
import br.edu.ufcg.sgbd.DB;

public class AtividadeFachada {

//	
//	private static final String TABLE_NAME = "TABLE_ATIVIDADE";
//	private DB db;
//	
//	public AtividadeFachada(DB db) {
//		this.db = db;
//	}
//
//	public void adicionaAtividade(Integer idAluno, Atividade atividade){
//		ContentValues values = new ContentValues();
//		values.put("id_aluno", idAluno);
//		values.put("nomeExercicio", atividade.getNomeExercicio());
//		values.put("nomeMaquina", atividade.getNomeMaquina());
//		values.put("nomeGrupoMuscular", atividade.getGrupoMuscular());
//		values.put("numeroSeries", atividade.getSeries());
//		values.put("numeroRepeticoes", atividade.getRepeticoes());
//		values.put("peso", atividade.getPeso());
//		values.put("observacao", atividade.getObservacao());
//		
//		db.insertValues(TABLE_NAME, values);
//		db.close();
//	}
//	
//	public List<Atividade> getAtividades(Integer idAluno){
//		List<Atividade> atividades = new ArrayList<Atividade>();
//		Cursor cursor = db.getReadableDatabase().query(TABLE_NAME, null, "id_aluno = " + idAluno, null, null, null, null);
//		try{
//			if(cursor != null){
//				while(cursor.moveToNext()){
//					Atividade atividade = new Atividade(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5),cursor.getString(6), cursor.getString(7), cursor.getString(8));
//					atividades.add(atividade);
//				}
//			}
//		}catch(Exception e){
//			e.getStackTrace();
//		}finally{
//			cursor.close();
//		}
//		//Collections.sort(atividades);
//		return atividades;
//	}
//	
//	public List<String> getNomesDasAtividades(Integer idAluno){
//		List<Atividade> atividades = getAtividades(idAluno);
//		List<String> nomes = new ArrayList<String>();
//		for(Atividade atividade : atividades){
//			nomes.add(atividade.getNomeExercicio());
//		}
//		return nomes;
//	}
	
}
