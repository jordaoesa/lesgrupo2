package br.edu.ufcg.util;

import android.content.Context;
import br.edu.ufcg.fachada.AlunoFachada;
import br.edu.ufcg.fachada.DadosFachada;
import br.edu.ufcg.fachada.FinancasFachada;
import br.edu.ufcg.sgbd.DB;

public class FitnessManagementSingleton {

	private static Context context;
	private static DB db;
	private static AlunoFachada alunoFachada;
	private static DadosFachada dadosFachada;
	private static FinancasFachada financasFachada;
	
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
	
	public static FinancasFachada getFinancasFachadaInstance(){
		if(financasFachada == null){
			financasFachada = new FinancasFachada(db);
		}
		return financasFachada;
	}
	
}
