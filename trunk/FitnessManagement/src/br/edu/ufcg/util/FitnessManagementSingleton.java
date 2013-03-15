package br.edu.ufcg.util;

import java.util.Date;

import android.content.Context;
import android.os.Environment;
import br.edu.ufcg.fachada.AlunoFachada;
import br.edu.ufcg.fachada.AtividadeFachada;
import br.edu.ufcg.fachada.DadosFachada;
import br.edu.ufcg.fachada.ExercicioFachada;
import br.edu.ufcg.fachada.FichaFachada;
import br.edu.ufcg.fachada.GrupoMuscularFachada;
import br.edu.ufcg.fachada.ImagemFachada;
import br.edu.ufcg.fachada.MaquinaFachada;
import br.edu.ufcg.fachada.FinancasFachada;
import br.edu.ufcg.fachada.TreinoFachada;
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
	private static ExercicioFachada exercicioFachada;
	private static MaquinaFachada maquinaFachada;
	private static GrupoMuscularFachada grupoMuscularFachada;
	private static AtividadeFachada atividadeFachada;
	private static FichaFachada fichaFachada;
	private static TreinoFachada treinoFachada;
	
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
	
//	public static AtividadeFachada getAtividadeFachadaInstance(){
//		if(atividadeFachada == null){
//			atividadeFachada = new AtividadeFachada(db);
//		}
//		return atividadeFachada;
//	}
	
	
	public static FichaFachada getFichaFachadaInstance(){
		if(fichaFachada == null){
			fichaFachada = new FichaFachada(db);
		}
		return fichaFachada;
	}
	
	public static TreinoFachada getTreinoFachadaInstance(){
		if(treinoFachada == null){
			treinoFachada = new TreinoFachada(db);
		}
		return treinoFachada;
	}

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
