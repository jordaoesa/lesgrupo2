package br.edu.ufcg.fachada;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import br.edu.ufcg.aluno.Aluno;
import br.edu.ufcg.metas.AcompanhamentoWeightLoss;
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
		Aluno aluno = FitnessManagementSingleton.getAlunoFachadaInstance().getAlunoFromId(id_aluno);
		Cursor cursor = db.getReadableDatabase().query(TABLE_NAME, null,"id_aluno="+id_aluno, null, null,null,null);
		try{
			if(cursor != null){
				while(cursor.moveToNext()){
					System.out.println("get meta weight");
					weightLoss = new WeightLoss(String.valueOf(cursor.getDouble(4)),
							String.valueOf(cursor.getDouble(5)),
							aluno.getSexo(),
							cursor.getString(6), 
							cursor.getString(7),
							String.valueOf(aluno.getIdade()),
							aluno.getId());
					System.out.println("get meta weight 2 " + weightLoss);
				}
			}
		}catch(Exception e){
			System.out.println(">>> " + e.getMessage());
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
	
	public void salvarAcompanhamentoWeightLoss(int id_aluno,String semana, String totalCalorias){
		if(existAcompanhamentoWeightLoss(id_aluno, semana)){
			db.delete("TABLE_ACOMPANHAMENTO_WEIGHT_LOSS", "id_aluno=" +id_aluno +"semana=" + semana);
			insertAcompanhamento(id_aluno, semana, totalCalorias);
		}else{
			insertAcompanhamento(id_aluno, semana, totalCalorias);
		}
	}
	private void insertAcompanhamento(int id_aluno, String semana,
			String totalCalorias) {
		ContentValues values = new ContentValues();
		values.put("id_aluno", id_aluno);
		values.put("semana", semana);
		values.put("total_calorias", totalCalorias);
		db.insertValues("TABLE_ACOMPANHAMENTO_WEIGHT_LOSS", values);
		db.close();
	}
	private boolean existAcompanhamentoWeightLoss(int id_aluno, String semana){
		Cursor cursor = db.getReadableDatabase().query("TABLE_ACOMPANHAMENTO_WEIGHT_LOSS", null,"id_aluno="+id_aluno, null, null, null,null);
		try{
			if(cursor != null){
				while(cursor.moveToNext()){
					if(cursor.getString(3).equals(semana)){
						return true;
					}
				}
			}
		}catch(Exception e){
			e.getStackTrace();
		}finally{
			cursor.close();
		}
		return false;
	}
	public List<AcompanhamentoWeightLoss> getAcompanhamentoWeightLoss(int id_aluno){
		List<AcompanhamentoWeightLoss> acompanhamentos = new ArrayList<AcompanhamentoWeightLoss>();
		Cursor cursor = db.getReadableDatabase().query("TABLE_ACOMPANHAMENTO_WEIGHT_LOSS", null,"id_aluno="+id_aluno, null, null, null,null);
		try{
			if(cursor != null){
				while(cursor.moveToNext()){
					acompanhamentos.add(new AcompanhamentoWeightLoss(id_aluno,cursor.getString(3),cursor.getString(4)));
				}
			}
		}catch(Exception e){
			e.getStackTrace();
		}finally{
			cursor.close();
		}
		return acompanhamentos;
	}
	public void delete(int id_aluno){
		db.delete(TABLE_NAME, "id_aluno=" +id_aluno );
	}
}
