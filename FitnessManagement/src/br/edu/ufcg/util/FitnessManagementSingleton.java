package br.edu.ufcg.util;

import android.content.Context;
import br.edu.ufcg.fachada.AlunoFachada;
import br.edu.ufcg.fachada.DadosFachada;
import br.edu.ufcg.fachada.ExercicioFachada;
import br.edu.ufcg.fachada.GrupoMuscularFachada;
import br.edu.ufcg.fachada.MaquinaFachada;
import br.edu.ufcg.fachada.FinancasFachada;
import br.edu.ufcg.sgbd.DB;

public class FitnessManagementSingleton {

	private static Context context;
	private static DB db;
	private static AlunoFachada alunoFachada;
	private static DadosFachada dadosFachada;
	private static FinancasFachada financasFachada;
	
	//alspkdkasd
	private static ExercicioFachada exercicioFachada;
	private static MaquinaFachada maquinaFachada;
	private static GrupoMuscularFachada grupoMuscularFachada;
	
	public static void setContext(Context context){
		FitnessManagementSingleton.context = context;
	}
	
	public static DB getDBInstance(){
		if(db == null){
			db = new DB(context);
		}
		return db;
	}
	
	public static AlunoFachada getAlunoFachadaInstance(){
		if(alunoFachada == null){
			alunoFachada = new AlunoFachada(db);
		}
		return alunoFachada;
	}
	
	public static DadosFachada getDadosFachadaInstance(){
		if(dadosFachada == null){
			dadosFachada = new DadosFachada(db);
		}
		return dadosFachada;
	}
	
	//apsikaosk
	public static ExercicioFachada getExercicioFachadaInstance(){
		if(exercicioFachada == null){
			exercicioFachada = new ExercicioFachada(db);
		}
		return exercicioFachada;
	}
	public static MaquinaFachada getMaquinaFachadaInstance(){
		if(maquinaFachada == null){
			maquinaFachada = new MaquinaFachada(db);
		}
		return maquinaFachada;
	}
	public static GrupoMuscularFachada getGrupoMuscularFachadaInstance(){
		if(grupoMuscularFachada == null){
			grupoMuscularFachada = new GrupoMuscularFachada(db);
		}
		return grupoMuscularFachada;
	}
	
	public static FinancasFachada getFinancasFachadaInstance(){
		if(financasFachada == null){
			financasFachada = new FinancasFachada(db);
		}
		return financasFachada;
	}
	
}
