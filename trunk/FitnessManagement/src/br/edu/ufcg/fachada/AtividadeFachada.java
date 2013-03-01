package br.edu.ufcg.fachada;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import br.edu.ufcg.exercicio.Atividade;
import br.edu.ufcg.sgbd.DB;

public class AtividadeFachada {

	
	private static final String TABLE_NAME = "TABLE_ATIVIDADE";
	private DB db;
	
	public AtividadeFachada(DB db) {
		this.db = db;
	}

	public void adicionaAtividade(Atividade atividade){
		ContentValues values = new ContentValues();
		String nomeExercicio = atividade.getNomeExercicio();
		String nomeMaquina = atividade.getNomeMaquina();
		String nomeGrupoMuscular = atividade.getGrupoMuscular();
		int numeroSeries = atividade.getSeries();
		int numeroRepeticoes = atividade.getRepeticoes();
		String observacao = atividade.getObservacao();
		int peso = atividade.getPeso();
		
		
		values.put("nomeExercicio", nomeExercicio);
		values.put("nomeMaquina", nomeMaquina);
		values.put("nomeGrupoMuscular", nomeGrupoMuscular);
		values.put("numeroSeries", numeroSeries);
		values.put("numeroRepeticoes", numeroRepeticoes);
		values.put("peso",peso);
		values.put("observacao",observacao);
		
		
		System.out.println("CHEGUEI ATE AQUI DENTRO");
		db.insertValues(TABLE_NAME, values);
		System.out.println("CONSEGUI INSERIR NA TABELA");
		db.close();
	}
	

	
	public List<Atividade> getAtividades(){
		List<Atividade> atividades = new ArrayList<Atividade>();
		Cursor cursor = db.getReadableDatabase().query(TABLE_NAME, null, null, null, null, null, null);
		try{
			if(cursor != null){
				while(cursor.moveToNext()){
					Atividade exercicioComposto = new Atividade(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getInt(5),cursor.getString(6));
					atividades.add(exercicioComposto);
					System.out.println(exercicioComposto.toString());
				}
			}
		}catch(Exception e){
			e.getStackTrace();
		}finally{
			cursor.close();
		}
		//Collections.sort(atividades);
		return atividades;
		
		
	}
	
}
