package br.edu.ufcg.fachada;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import br.edu.ufcg.agendamento.Agendamento;
import br.edu.ufcg.agendamento.AgendamentoType;
import br.edu.ufcg.gerenciar.Atividade;
import br.edu.ufcg.sgbd.DB;

public class AgendamentoFachada {

	private static final String TABLE_NAME = "TABLE_AGENDAMENTO";
	private DB db;
	
	public AgendamentoFachada(DB db){
		this.db = db;
	}
	public void adicionaAgendamento( Agendamento agendamento){
		ContentValues values = new ContentValues();
		values.put("id_aluno", agendamento.getIdAluno());
		values.put("dia_pagamento", agendamento.getDiaPagamento());
		values.put("date_millis", agendamento.getDateInMillis());
		values.put("tipo", agendamento.getType().value());
		db.insertValues(TABLE_NAME, values);
		db.close();
	}
	public List<Agendamento> getAgendamentoPorAluno(int id_aluno) {
		List<Agendamento> atividades = new ArrayList<Agendamento>();
		Cursor cursor = db.getReadableDatabase().query(TABLE_NAME, null,"id_aluno="+id_aluno, null, null, null, null);

		try{
			if(cursor != null){
				while(cursor.moveToNext()){
					Agendamento atividade;
					if(cursor.getString(4).equals("Treino")){
						atividade = new Agendamento(cursor.getInt(1),cursor.getString(2),AgendamentoType.TREINO, cursor.getString(3));
					}else{
						atividade = new Agendamento(cursor.getInt(1),cursor.getString(2),AgendamentoType.PAGAMENTO,cursor.getString(3));
					}
					atividades.add(atividade);
				}
			}
		}catch(Exception e){
			e.getStackTrace();
		}finally{
			cursor.close();
		}
		return atividades;
	}
	
	public void removeAgendamento( Agendamento agendamento ){
		String where = "id_aluno="+agendamento.getIdAluno() + " and dia_pagamento" + agendamento.getDiaPagamento();
		String select [] = DB.TABLE_AGENDAMENTO_KEYS;
		db.deleteByField(TABLE_NAME, select, where);
	}
}
