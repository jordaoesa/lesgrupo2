package br.edu.ufcg.sgbd;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {
	
	private final String TABLE_ALUNO = "CREATE TABLE TABLE_ALUNO(" +
											"id INTEGER PRIMARY KEY AUTOINCREMENT," +
											"nome VARCHAR(255)," +
											"endereco VARCHAR(255)," +
											"sexo VARCHAR(255)," +
											"telefone VARCHAR(255)," +
											"idade INTEGER" +
										")";
	
	private final String TABLE_DADOS = "CREATE TABLE TABLE_DADOS(" +
											"id_aluno INTEGER," +
											"data VARCHAR(11)," +
											"peso REAL," +
											"calorias_gastas REAL," +
											"tamanho_braco REAL," +
											"tamanho_antebraco REAL," +
											"tamanho_perna REAL," +
											"tamanho_panturrilha REAL," +
											"imc REAL," +
											"PRIMARY KEY(id_aluno, data)," +
											"FOREIGN KEY(id_aluno) REFERENCES TABLE_ALUNO(id)" +
										")";
	
	private final String TABLE_FINANCAS = "CREATE TABLE TABLE_FINANCAS(" +
												"id INTEGER PRIMARY KEY AUTOINCREMENT," +
												"id_aluno INTEGER," +
												"valor REAL," +
												"FOREIGN KEY(id_aluno) REFERENCES TABLE_ALUNO(id)" +
											")";
	
	private final String TABLE_EXERCICIOS = "CREATE TABLE TABLE_EXERCICIOS("+"id_exercicio INTEGER,"+"nomeExercicio VARCHAR(255)"+")";
	private final String TABLE_MAQUINAS = "CREATE TABLE TABLE_MAQUINAS("+"id_maquina INTEGER,"+"nomeMaquina VARCHAR(255)"+")";
	private final String TABLE_GRUPO_MUSCULAR = "CREATE TABLE TABLE_GRUPO_MUSCULAR("+"id_grupoMuscular INTEGER,"+"nomeGrupoMuscular VARCHAR(255)"+")";
	/**
	 * O construtor necessita do contexto da aplicacao
	 * @param context
	 */
	public DB(Context context) {
		/* O primeiro argumento eh o contexto da aplicacao
	     * O segundo argumento eh o nome do banco de dados
	     * O terceiro eh um ponteiro para manipulacao de dados, nao precisaremos dele.
	     * O quarto eh a versao do banco de dados
	     */
		super(context, "FitDB", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_ALUNO);
		db.execSQL(TABLE_DADOS);
		db.execSQL(TABLE_FINANCAS);
		db.execSQL(TABLE_EXERCICIOS);
		db.execSQL(TABLE_GRUPO_MUSCULAR);
		db.execSQL(TABLE_MAQUINAS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		dropTable(db, "TABLE_ALUNO");
		dropTable(db, "TABLE_DADOS");
		dropTable(db, "TABLE_FINANCAS");
		dropTable(db, "TABLE_EXERCICIOS");
		dropTable(db, "TABLE_GRUPO_MUSCULAR");
		dropTable(db, "TABLE_MAQUINAS");
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
	
}