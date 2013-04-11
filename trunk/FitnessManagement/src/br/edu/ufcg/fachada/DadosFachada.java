package br.edu.ufcg.fachada;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import br.edu.ufcg.aluno.Dados;
import br.edu.ufcg.sgbd.DB;

public class DadosFachada {
	
	private static final String TABLE_NAME = "TABLE_DADOS";
	private DB db;
	
	public DadosFachada(DB db) {
		this.db = db;
	}

	public void adicionaDadosDoAluno(Integer id, Dados dados){
		ContentValues values = new ContentValues();
		values.put("id_aluno", id);
		values.put("peso", dados.getPeso());
		values.put("altura", dados.getAltura());
		values.put("braco_e_r", dados.getBracoER());
		values.put("braco_e_c", dados.getBracoEC());
		values.put("antebraco_e", dados.getAntebracoE());
		values.put("coxa_e", dados.getCoxaE());
		values.put("panturrilha_e", dados.getPanturrilhaE());
		values.put("braco_d_r", dados.getBracoDR());
		values.put("braco_d_c", dados.getBracoDC());
		values.put("antebraco_d", dados.getAntebracoD());
		values.put("coxa_d", dados.getCoxaD());
		values.put("panturrilha_d", dados.getPanturrilhaD());
		values.put("data", (new SimpleDateFormat("dd/MM/yyyy")).format(dados.getData()));
		db.insertValues(TABLE_NAME, values);
	}
	
	public List<Dados> getDadosDoAluno(Integer id) {
		List<Dados> dados = new ArrayList<Dados>();
		Cursor cursor = db.getReadableDatabase().query(TABLE_NAME, null, "id_aluno = " + id, null, null, null, null);
		try{
			while(cursor.moveToNext()){
				SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
				Date date = null;
				try {
					date = formater.parse(cursor.getString(13));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Dados dado = new Dados(cursor.getDouble(1), cursor.getDouble(2), cursor.getDouble(3), cursor.getDouble(4), cursor.getDouble(5), cursor.getDouble(6), cursor.getDouble(7), cursor.getDouble(8), cursor.getDouble(9), cursor.getDouble(10), cursor.getDouble(11), cursor.getDouble(12), date);
				dados.add(dado);
			}
		}catch(Exception e){
			e.getStackTrace();
		}finally{
			cursor.close();
		}
		return dados;
	}
}
