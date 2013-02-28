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
		values.put("data", (new SimpleDateFormat("dd/MM/yyyy")).format(dados.getData()));
		values.put("peso", dados.getPeso());
		values.put("calorias_gastas", dados.getCalorias());
		values.put("tamanho_braco", dados.getTamanhoBraco());
		values.put("tamanho_perna", dados.getTamanhoPerna());
		values.put("imc", dados.getImc());
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
					date = formater.parse(cursor.getString(1));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Dados dado = new Dados(cursor.getDouble(2), cursor.getDouble(3), cursor.getDouble(4), cursor.getDouble(6), cursor.getDouble(8), date);
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
