package br.edu.ufcg.fachada;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import br.edu.ufcg.agendamento.Agendamento;
import br.edu.ufcg.agendamento.AgendamentoType;
import br.edu.ufcg.aluno.Aluno;
import br.edu.ufcg.metas.WeightLoss;
import br.edu.ufcg.sgbd.DB;
import br.edu.ufcg.util.FitnessManagementSingleton;

public class MetasFachada {
	private static final String TABLE_NAME = "TABLE_META";
	private DB db;
	
	public MetasFachada(DB db){
		this.db = db;
	}
	public void adicionaMetaWeightLoss(WeightLoss weightLoss){
		ContentValues values = new ContentValues();
		values.put("dias_metas", weightLoss.getDiasPerderPeso());
		values.put("calorias_dia", weightLoss.getCaloriasPerdePeso());
		values.put("id_aluno", weightLoss.getIdALuno());
		values.put("peso_atual", weightLoss.getPesoAtual());
		values.put("peso_perder", weightLoss.getPesoPerder());
		values.put("altura", weightLoss.getAltura());
		values.put("nivel_exercicio", weightLoss.getNivelExercicio());
		db.insertValues(TABLE_NAME, values);
		db.close();
	}
	
	public WeightLoss getMetaWeightLoss(int id_aluno){
		WeightLoss weightLoss = null;
		Cursor cursor = db.getReadableDatabase().query(TABLE_NAME, null,null,null,"id_aluno="+id_aluno, null, null, null);
		try{
			if(cursor != null){
				while(cursor.moveToNext()){
					Aluno aluno = FitnessManagementSingleton.getAlunoFachadaInstance().getAlunoFromId(cursor.getInt(4));
					weightLoss = new WeightLoss(String.valueOf(cursor.getDouble(5)),String.valueOf(cursor.getDouble(6)),aluno.getSexo(),cursor.getString(7), cursor.getString(8),String.valueOf(aluno.getIdade()),aluno.getId());
				}
			}
		}catch(Exception e){
			e.getStackTrace();
		}finally{
			cursor.close();
		}
		return weightLoss;
	}
	public boolean existMetaWeightLoss(int id_aluno){
		Cursor cursor = db.getReadableDatabase().query(TABLE_NAME, null,null,null,"id_aluno="+id_aluno, null, null, null);
		try{
			if(cursor != null){
				while(cursor.moveToNext()){
					return true;
				}
			}
		}catch(Exception e){
			e.getStackTrace();
		}finally{
			cursor.close();
		}
		return false;
	}
	public void delete(int id_aluno){
		db.delete(TABLE_NAME, "id_aluno=" +id_aluno );
	}
}
