package br.edu.ufcg.sgbd;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {
	
	private final String TABLE_ALUNO = "CREATE TABLE ALUNO(" +
										"id INTEGER PRIMARY KEY AUTOINCREMENT," +
										"nome VARCHAR(255)," +
										"sexo VARCHAR(1)," +
										"rua VARCHAR(255)," +
										"numero INTEGER," +
										"cidade VARCHAR(255)" +
										")";
	
	private final String TABLE_DADOS = "CREATE TABLE DADOS(" +
										"id_aluno INTEGER," +
										"data VARCHAR(11)," +
										"peso REAL," +
										"calorias_gastas REAL," +
										"tamanho_braco REAL," +
										"tamanho_antebraco REAL," +
										"tamanho_perna REAL," +
										"tamanho_panturrilha REAL," +
										"imc REAL," +
										"PRIMARY KEY (id_aluno, data)" +
										"FOREIGN KEY (id_aluno) REFERENCES ALUNO(id)" +
										")";
	
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
		super(context, "FitBD", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		dropTable(db, "ALUNO");
		dropTable(db, "DADOS");
		db.execSQL(TABLE_ALUNO);
		db.execSQL(TABLE_DADOS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		dropTable(db, "ALUNO");
		dropTable(db, "DADOS");
		onCreate(db);
	}

	private void dropTable(SQLiteDatabase db, String tableName) {
		db.execSQL("DROP TABLE IF EXISTS " + tableName);
	}
	
	public void insertValues(String tableName, ContentValues values) {
		try{
			SQLiteDatabase db = this.getWritableDatabase();
			db.insert(tableName, null, values);
			db.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
}