package br.edu.ufcg.fachada;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import br.edu.ufcg.aluno.Aluno;
import br.edu.ufcg.gerenciar.Atividade;
import br.edu.ufcg.sgbd.DB;

public class AtividadeDiariaFachada {

	private final String TABLE_NAME = "TABLE_ATIVIDADE_DIARIA"; 
	private DB db;
	
	public AtividadeDiariaFachada(DB db) {
		this.db = db;
	}
	
	public void adicionarAtividadeDiaria(Atividade atividade) {
		ContentValues values = new ContentValues();
		values.put("nome", atividade.getNomeAtividade());
		values.put("series", atividade.getNumeroSeries());
		values.put("repeticoes", atividade.getNumeroRepeticoes());
		values.put("horas", atividade.getHoraDeExecucao());
		values.put("minutos", atividade.getMinDeExecucao());
		values.put("segundos", atividade.getSegDeExecucao());
		values.put("observacoes", atividade.getObservacaoAtividade());
		values.put("diaSemana", atividade.getDiaSemana());
		values.put("id_aluno", atividade.getIdAluno());
		db.insertValues(TABLE_NAME, values);
		db.close();
	}
	
	public List<Atividade> getAtividadesDiaria(int id_aluno, String diaSemana) {
		List<Atividade> atividades = new ArrayList<Atividade>();
		Cursor cursor = db.getReadableDatabase().query(TABLE_NAME, null,"id_aluno="+id_aluno, null, null, null, null);

		try{
			if(cursor != null){
				while(cursor.moveToNext()){
					Atividade atividade = new Atividade(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getInt(9));
					if(cursor.getString(8).equals(diaSemana)){
						atividades.add(atividade);
					}
				}
			}
		}catch(Exception e){
			e.getStackTrace();
		}finally{
			cursor.close();
		}
		System.out.println(atividades);
		return atividades;
	}
	
	
	
	
}
