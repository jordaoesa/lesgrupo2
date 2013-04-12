package br.edu.ufcg.util;

import java.util.Date;

import android.content.Context;
import android.os.Environment;
import br.edu.ufcg.fachada.AgendamentoFachada;
import br.edu.ufcg.fachada.AlunoFachada;
import br.edu.ufcg.fachada.AnamneseFachada;
import br.edu.ufcg.fachada.DadosFachada;
import br.edu.ufcg.fachada.ImagemFachada;
import br.edu.ufcg.fachada.FinancasFachada;
import br.edu.ufcg.fachada.MetasFachada;
import br.edu.ufcg.sgbd.DB;

public class FitnessManagementSingleton {

	private static final String CAMINHO = Environment.getExternalStorageDirectory() + "/.FitnessManagement";
	private static final String CAMINHO_THUMBNAIL_ACOMPANHAMENTO = CAMINHO + "/Fotos/Thumbnails";
	private static final String CAMINHO_IMAGEM_ACOMPANHAMENTO = CAMINHO + "/Fotos/Imagens";
	private static String CAMINHO_FOTO_PERFIL = CAMINHO + "/Fotos/Perfil";
	private static Context context;
	private static DB db;
	private static AlunoFachada alunoFachada;
	private static DadosFachada dadosFachada;
	private static FinancasFachada financasFachada;
	private static ImagemFachada imagemFachada;
	private static AnamneseFachada anamneseFachada;
	private static MetasFachada metasFachada;
	private static AgendamentoFachada agendamentoFachada;
	
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
	
	public static AnamneseFachada getAnamneseFachadaInstance() {
		if(anamneseFachada == null){
			anamneseFachada = new AnamneseFachada(db);
		}
		return anamneseFachada;
	}
	
	public static AgendamentoFachada getAgendamentoFachadaInstance(){
		if(agendamentoFachada == null){
			agendamentoFachada = new AgendamentoFachada(db);
		}
		return agendamentoFachada;
	}
	
	public static MetasFachada getMetaFachadaInstance(){
		if(metasFachada == null){
			metasFachada = new MetasFachada(db);
		}
		return metasFachada;
	}
//	public static AtividadeFachada getAtividadeFachadaInstance(){
//		if(atividadeFachada == null){
//			atividadeFachada = new AtividadeFachada(db);
//		}
//		return atividadeFachada;
//	}
	
	
	public static ImagemFachada getImagemFachadaInstance() {
		if(imagemFachada == null){
			imagemFachada = new ImagemFachada(db);
		}
		return imagemFachada;
	}
	
	public static String getCaminhoFotoPerfil() {
		return CAMINHO_FOTO_PERFIL;
	}
	
	public static String getCaminhoThumbnailAcompanhamento(){
		return CAMINHO_THUMBNAIL_ACOMPANHAMENTO;
	}
	
	public static String getCaminhoImagemAcompanhamento() {
		return CAMINHO_IMAGEM_ACOMPANHAMENTO;
	}
	
	public static String getNomeFotoPerfil(){
		return "/br.ufcg.edu.perfil_" + (new Date()).toString().replace(" ", "-").replace(":", "-") + ".jpg";
	}
	
	public static String getNomeThumbnailAcompanhamento(){
		return "/br.ufcg.edu.thumbnail_" + (new Date()).toString().replace(" ", "-").replace(":", "-") + ".jpg";
	}

	public static String getNomeImagemAcompanhamento(){
		return "/br.ufcg.edu.imagem_" + (new Date()).toString().replace(" ", "-").replace(":", "-") + ".jpg";
	}

}
