package br.edu.ufcg.sgbd;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.edu.ufcg.exception.DataBaseException;

public class DB extends SQLiteOpenHelper {

	private final String TABLE_ATIVIDADE_DIARIA = "CREATE TABLE TABLE_ATIVIDADE_DIARIA(" +
			"id INTEGER PRIMARY KEY AUTOINCREMENT," +
			"nome VARCHAR(255)," +
			"series VARCHAR(255)," +
			"repeticoes VARCHAR(255)," +
			"horas VARCHAR(255)," +
			"minutos VARCHAR(255)," +
			"segundos VARCHAR(255)," +
			"observacoes VARCHAR(255)," +
			"diaSemana VARCHAR(255)," +
			"id_aluno INTEGER," +
			"FOREIGN KEY(id_aluno) REFERENCES TABLE_ALUNO(id)" +

		")";


	private final String TABLE_ALUNO = "CREATE TABLE TABLE_ALUNO(" +
			"id INTEGER PRIMARY KEY AUTOINCREMENT," +
			"idade INTEGER," +
			"nome VARCHAR(255)," +
			"endereco VARCHAR(255)," +
			"sexo VARCHAR(255)," +
			"telefone VARCHAR(255)," +
			"caminho_imagem VARCHAR(255)" +
			")";

	private final String TABLE_DADOS = "CREATE TABLE TABLE_DADOS(" +
			"id_aluno INTEGER," +
			"peso REAL," +
			"altura REAL," +
			"braco_e_r REAL," +
			"braco_e_c REAL," +
			"antebraco_e REAL," +
			"coxa_e REAL," +
			"panturrilha_e REAL," +
			"braco_d_r REAL," +
			"braco_d_c REAL," +
			"antebraco_d REAL," +
			"coxa_d REAL," +
			"panturrilha_d REAL," +
			"data VARCHAR(50)," +
			"PRIMARY KEY(id_aluno, data)," +
			"FOREIGN KEY(id_aluno) REFERENCES TABLE_ALUNO(id)" +
			")";

	private final String TABLE_FINANCAS = "CREATE TABLE TABLE_FINANCAS(" +
			"id INTEGER PRIMARY KEY AUTOINCREMENT," +
			"id_aluno INTEGER," +
			"valor REAL," +
			"FOREIGN KEY(id_aluno) REFERENCES TABLE_ALUNO(id)" +
			")";

	private final String TABLE_THUMBNAIL = "CREATE TABLE TABLE_THUMBNAIL(" +
			"id INTEGER PRIMARY KEY AUTOINCREMENT," +
			"id_aluno INTEGER," +
			"caminho_thumbnail VARCHAR(255)," +
			"FOREIGN KEY(id_aluno) REFERENCES TABLE_ALUNO(id)" +
			")";

	private final String TABLE_IMAGEM = "CREATE TABLE TABLE_IMAGEM(" +
			"id INTEGER PRIMARY KEY AUTOINCREMENT," +
			"id_thumbnail INTEGER," +
			"id_aluno INTEGER," +
			"caminho_imagem VARCHAR(255)," +
			"data VARCHAR(255)," +
			"FOREIGN KEY(id_thumbnail) REFERENCES TABLE_THUMBNAIL(id)," +
			"FOREIGN KEY(id_aluno) REFERENCES TABLE_ALUNO(id)" +
			")";
	
	private final String TABLE_ANAMNESE = "CREATE TABLE TABLE_ANAMNESE(" +
			"id INTEGER PRIMARY KEY AUTOINCREMENT," +
			"id_aluno INTEGER," +
			"refeicoes_diarias INTEGER," +
			"porcent_gordura REAL," +
			"peso REAL," +
			"peso_massa_magra REAL," +
			"atividade VARCHAR(255)," +
			"atividade_gosta VARCHAR(255)," +
			"tempo_parado VARCHAR(255)," +
			"tempo_sono VARCHAR(255)," +
			"nivel_stress VARCHAR(255)," +
			"tempo_fumante VARCHAR(255)," +
			"outros_prob VARCHAR(255)," +
			"quanto_tempo_medic VARCHAR(255)," +
			"qual_medicamento VARCHAR(255)," +
			"qual_cirurgia VARCHAR(255)," +
			"tempo_cirurgia VARCHAR(255)," +
			"frequencia_semanal VARCHAR(255)," +
			"sedentario BOOLEAN," +
			"fuma BOOLEAN," +
			"prob_cardiacos BOOLEAN," +
			"anemia BOOLEAN," +
			"ansiedade BOOLEAN," +
			"prob_resp BOOLEAN," +
			"colesterol BOOLEAN," +
			"depressao BOOLEAN," +
			"insonia BOOLEAN," +
			"diabetes BOOLEAN," +
			"gastrite BOOLEAN," +
			"tireoide BOOLEAN," +
			"varizes BOOLEAN," +
			"tonturas BOOLEAN," +
			"usa_medicamentos BOOLEAN," +
			"fez_cirurgia BOOLEAN," +
			"sente_dores BOOLEAN," +
			"hipertrofia BOOLEAN," +
			"condicionamento BOOLEAN," +
			"diminuir_gordura BOOLEAN," +
			"resistencia BOOLEAN," +
			"enrijecimento BOOLEAN," +
			"postura BOOLEAN," +
			"FOREIGN KEY(id_aluno) REFERENCES TABLE_ALUNO(id)" +
			")";

	private final String TABLE_ATIVIDADE = "CREATE TABLE TABLE_ATIVIDADE(" +
			"id INTEGER PRIMARY KEY AUTOINCREMENT," +
			"id_aluno INTEGER," +
			"numeroSeries INTEGER," +
			"numeroRepeticoes INTEGER," +
			"peso INTEGER," +
			"nomeExercicio VARCHAR(255)," +
			"nomeMaquina VARCHAR(255)," +
			"nomeGrupoMuscular VARCHAR(255)," +
			"observacao VARCHAR(255)," +
			"FOREIGN KEY(id_aluno) REFERENCES TABLE_ALUNO(id)" +
			")";

	private final String TABLE_FICHA = 	"CREATE TABLE TABLE_FICHA(" +
			"id INTEGER PRIMARY KEY AUTOINCREMENT," +
			"id_treino INTEGER," +
			"FOREIGN KEY(id_treino) REFERENCES TABLE_TREINO(id)" +
			")";


	private final String TABLE_TREINO  = 	"CREATE TABLE TABLE_TREINO(" +
			"id INTEGER PRIMARY KEY AUTOINCREMENT," +
			"id_atividade INTEGER," +
			"FOREIGN KEY(id_atividade) REFERENCES TABLE_ATIVIDADE(id)" +
			")";

	public final static String TABLE_AGENDAMENT0 = "CREATE TABLE TABLE_AGENDAMENTO("+
			"id INTEGER PRIMARY KEY AUTOINCREMENT," +
			"id_aluno INTEGER,"+
			"dia_pagamento VARCHAR(255),"+
			"date_millis VARCHAR(255)," +
			"tipo VARCHAR(255),"+
			"FOREIGN KEY(id_aluno) REFERENCES ALUNO(id)"+
			")";

	public final static String TABLE_AGENDAMENTO_KEYS [] = {"id","id_aluno", "dia_pagamento", "tipo"};

	private final String TABLE_META = "CREATE TABLE TABLE_META(" +
			"id INTEGER PRIMARY KEY AUTOINCREMENT," +
			"dias_metas INTEGER," + 
			"calorias_dia DOUBLE," +
			"id_aluno INTEGER," +
			"peso_atual DOUBLE," +
			"peso_perder DOUBLE," +
			"altura DOUBLE,"+ 
			"nivel_exercicio VARCHAR(255)" +
			")";
			
	private final String TABLE_ACOMPANHAMENTO_WEIGHT_LOSS = "CREATE TABLE TABLE_ACOMPANHAMENTO_WEIGHT_LOSS(" +
			"id INTEGER PRIMARY KEY AUTOINCREMENT," +
			"id_aluno INTEGER,"+
			"semana VARCHAR(255),"+
			"total_calorias VARCHAR(255)" +
			")";
			
	/**
	 * Construtor DB
	 * @param context Contexto da Aplicacao
	 */
	public DB(Context context) {
		/* O primeiro argumento eh o contexto da aplicacao
		 * O segundo argumento eh o nome do banco de dados
		 * O terceiro eh um ponteiro para manipulacao de dados
		 * O quarto eh a versao do banco de dados
		 */
		super(context, "FitDB", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_ALUNO);
		db.execSQL(TABLE_DADOS);
		db.execSQL(TABLE_FINANCAS);
		db.execSQL(TABLE_THUMBNAIL);
		db.execSQL(TABLE_IMAGEM);
		db.execSQL(TABLE_ANAMNESE);
		db.execSQL(TABLE_ATIVIDADE);
		db.execSQL(TABLE_FICHA);
		db.execSQL(TABLE_TREINO);
		db.execSQL(TABLE_ATIVIDADE_DIARIA);
		db.execSQL(TABLE_AGENDAMENT0);
		db.execSQL(TABLE_META);
		db.execSQL(TABLE_ACOMPANHAMENTO_WEIGHT_LOSS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		dropTable(db, "TABLE_ALUNO");
		dropTable(db, "TABLE_DADOS");
		dropTable(db, "TABLE_FINANCAS");
		dropTable(db, "TABLE_THUMBNAIL");
		dropTable(db, "TABLE_IMAGEM");
		dropTable(db, "TABLE_ANAMNESE");
		dropTable(db, "TABLE_ATIVIDADE");
		dropTable(db, "TABLE_FICHA");
		dropTable(db, "TABLE_TREINO");
		dropTable(db, "TABLE_ATIVIDADE_DIARIA");
		dropTable(db, "TABLE_AGENDAMENT0");
		dropTable(db, "TABLE_META");
		dropTable(db, "TABLE_ACOMPANHAMENTO_WEIGHT_LOSS");
		onCreate(db);
	}

	private void dropTable(SQLiteDatabase db, String tableName) {
		try{
			db.execSQL("DROP TABLE IF EXISTS " + tableName);
		}catch(Exception e){
			System.out.println(">>> " + e.getMessage());
		}finally{
			db.close();
		}
	}

	public void insertValues(String tableName, ContentValues values) {
		SQLiteDatabase db = this.getWritableDatabase();
		try{
			db.insert(tableName, null, values);
		}catch(Exception e){
			System.out.println(">>> " + e.getMessage());
		}finally{
			db.close();
		}
	}

	public void updateTable(String tableName, ContentValues values, String where){
		SQLiteDatabase db = this.getWritableDatabase();
		try{
			db.update(tableName, values, where, null);
		}catch (Exception e) {
			System.out.println(">>> " + e.getMessage());
		}finally{
			db.close();
		}
	}

	public boolean deleteByField(String tabela,String select[],String where) {

		try{
			SQLiteDatabase db = this.getWritableDatabase();

			try{
				Cursor cursor = db.query(tabela, select,where,null, null, null, null);
				if (cursor == null) throw new DataBaseException("Banco de dados inexistente");
				cursor.moveToFirst();
				for (int j = 0; j < cursor.getCount(); j++) {
					String lineTable = "";
					for (int i = 0; i < select.length; i++) {
						lineTable = lineTable + cursor.getString(i)+"#";
					}
					System.out.println("QUERY: " + lineTable);
					cursor.moveToNext();
				}

			}catch(Exception error){
				throw new DataBaseException(error.getMessage()+"Erro no banco de dados ao realizar uma pesquisa ");
			}
			db.close();
		}catch(Exception error){
			return false;
		}
		return true;
	}


	public void delete(String tableName, String where){
		SQLiteDatabase db = this.getWritableDatabase();
		try {
			db.delete(tableName, where, null);
		} catch (Exception e) {
			System.out.println(">>> " + e.getMessage());
		}finally{
			db.close();
		}
	}

}
