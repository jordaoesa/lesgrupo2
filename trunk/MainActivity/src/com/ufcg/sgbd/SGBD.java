package com.ufcg.sgbd;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ufcg.exception.DataBaseException;

public class SGBD extends SQLiteOpenHelper{

	private static String NAME_DATA_BASE = "PROJETO_PILOTO";
	private static int VERSION = 16;


	public SGBD(Context context) {
		super(context, NAME_DATA_BASE, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createTable_DISCIPLINAS(db);
		createTable_REQUISITOS(db);
	}	


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		dropTable_DISCIPLINAS(db);
		dropTable_REQUISITOS(db);
		onCreate(db);

	}
	private void createTable_DISCIPLINAS(SQLiteDatabase db){
		db.execSQL(com.ufcg.sgbd.ScriptSQL.disciplinas);
	}
	private void createTable_REQUISITOS(SQLiteDatabase db) {
		db.execSQL(com.ufcg.sgbd.ScriptSQL.requisitos);
	}
	private void dropTable_REQUISITOS(SQLiteDatabase db) {
		db.execSQL("DROP TABLE IF EXISTS requisitos");
	}
	public void dropTable_DISCIPLINAS(SQLiteDatabase db){
		db.execSQL("DROP TABLE IF EXISTS disciplinas");
	}
	private void dropTableSystem(SQLiteDatabase db){
		db.execSQL("DROP TABLE IF EXISTS disciplinas;");
	}
	public long insertValues(String tabela, ContentValues values) throws DataBaseException{
		long resultado = -1;
		try{
			SQLiteDatabase banco = this.getWritableDatabase();
			resultado = banco.insert(tabela,null, values);		
			banco.close();			

		}catch(Exception erro){
			throw new DataBaseException("Erro de Sintax de SQL em alguma tabela");
		}
		return resultado;
	}

	public int update(String tabela, ContentValues values, String idTabela, int registro) {
		SQLiteDatabase db = this.getWritableDatabase();

		// updating row
		return db.update(tabela, values, idTabela + " = ?",null);
	}


	public boolean delete(String tabela) {

		try{
			SQLiteDatabase db = this.getWritableDatabase();
			db.delete(tabela,null,null);
			db.close();
		}catch(Exception error){
			return false;
		}
		return true;
	}





	/**
	 * Realiza pesquisa de uma tabela conforme o campo
	 * @param campo
	 * @param valor
	 * @param tabela
	 * @param dadosTabela
	 * @return
	 * @throws DataBaseException
	 */
	public String[] query(String SELECT[], String WHERE, String tabela) throws DataBaseException{
		String valores [] = new String[SELECT.length + 1];
		Cursor cursor = null;
		SQLiteDatabase db = this.getReadableDatabase();

		try{

			cursor = db.query(tabela, SELECT, WHERE,null, null, null, null);
			if (cursor != null)
				cursor.moveToFirst();
			else throw new DataBaseException("Banco de dados inexistente");

			if(cursor.getCount() == 0)
				throw new DataBaseException("Nao há cadastro salvo com estas informacoes");


			//armazena cada campo "cursor.getString(i)" da tabela no vetor de string, ou seja retorna apenas o primeiro indice encontrado
			for(int i = 0 ; i < SELECT.length ; i++){
				if(cursor.getString(i).equals(null)) throw new DataBaseException("Dado Inconsistente");
				valores[i] = cursor.getString(i);
			}

		}catch(Exception error){
			throw new DataBaseException(error.getMessage()+"Erro no banco de dados ao realizar uma pesquisa ");
		}


		return valores;

	}

	/**
	 * Realiza pesquisa de uma tabela conforme o campo
	 * @param campo
	 * @param valor
	 * @param tabela
	 * @param dadosTabela
	 * @return
	 * @throws DataBaseException
	 */
	public List<String> queryREFATORED(String SELECT[], String tabela) throws DataBaseException{
		List<String> valoresQueryRetorno = new ArrayList<String>();	

		Cursor cursor = null;
		SQLiteDatabase db = this.getReadableDatabase();

		try{

			cursor = db.query(tabela, SELECT, null,null, null, null, null);

			if (cursor != null)
				cursor.moveToFirst();
			else throw new DataBaseException("Banco de dados inexistente");

			if(cursor.getCount() == 0)
				return valoresQueryRetorno;

			int numeroDeRegistrosEncontrados = cursor.getCount(); // retorna o numero de registros encontrados com a consulta correspondente

			for(int j = 0; j < numeroDeRegistrosEncontrados ; j++){

				String lineTable = ""; //salva a informacao de uma tabela por linha com o separador  '#'

				//armazena cada campo "cursor.getString(i)" da tabela no vetor de string, ou seja retorna apenas o primeiro indice encontrado
				for(int i = 0 ; i < SELECT.length ; i++){
					if(cursor.getString(i).equals(null)) throw new DataBaseException("Dado Inconsistente");
					lineTable = lineTable + cursor.getString(i)+"#";
				}

				valoresQueryRetorno.add(lineTable);
				cursor.moveToNext();
			}

		}catch(Exception error){
			throw new DataBaseException(error.getMessage()+"Erro no banco de dados ao realizar uma pesquisa ");
		}


		return valoresQueryRetorno;

	}

	public List<String> query2(String SELECT[], String WHERE, String tabela, String [] dadosTabela) throws DataBaseException{
		List<String> valoresQueryRetorno = new ArrayList<String>();		

		Cursor cursor = null;
		SQLiteDatabase db = this.getReadableDatabase();

		try{

			cursor = db.query(tabela, SELECT, WHERE,null, null, null, null);


			if (cursor != null)
				cursor.moveToFirst();
			else throw new DataBaseException("Banco de dados inexistente");

			int tamanho = 0;
			int count = cursor.getCount();

			for(int j = 0 ; j < count ; j++){

				if(cursor.getCount() == 0)
					throw new DataBaseException("Nao há cadastro salvo com estas informacoes");

				String linha = "";
				for(int i = 0 ; i < dadosTabela.length ; i++){
					if(cursor.getString(i).equals(null)) throw new DataBaseException("Dado Inconsistente");
					linha = linha+" "+cursor.getString(i);
				}
				valoresQueryRetorno.add(linha);
				cursor.moveToNext();
				tamanho++;

			};


		}catch(Exception error){
			throw new DataBaseException(error.getMessage()+"Erro no banco de dados ao realizar uma pesquisa ");

		}

		return valoresQueryRetorno;

	}


	public List<String>  queryAllbyField(String nome, String select [], String where, String table) throws DataBaseException{
		List<String>linha;


		if(nome == null || nome.equals(" ") || nome.equals(""))
			throw new DataBaseException("Valores de pesquisa inválidos");

		linha = queryREFATORED(select, table);


		return linha;
	}

	public int getCountRegistros(String tabela, String dadosTabela[])throws DataBaseException{
		SQLiteDatabase db = this.getDataBaseRead();

		try{
			Cursor cursor = db.query(tabela, dadosTabela, null,null,null,null,null);
			return cursor.getCount();
		}catch(Exception error){
			throw new DataBaseException("Erro ao retornar quantidade de registros");
		}

	}

	public String getCampo(String tabela, String dadosTabela[]) throws DataBaseException{
		SQLiteDatabase db = this.getDataBaseRead();

		try{
			Cursor cursor = db.query(tabela, dadosTabela, null,null,null,null,null);
			return cursor.getString(3);
		}catch(Exception error){			
			throw new DataBaseException("Erro ao retornar quantidade de registros");
		}
	}

	public SQLiteDatabase getDataBaseWrite(){
		return this.getWritableDatabase();
	}

	public SQLiteDatabase getDataBaseRead(){
		return this.getReadableDatabase();
	}


}