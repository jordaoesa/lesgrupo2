package br.edu.ufcg.fachada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import br.edu.ufcg.fachada.ImagemFachada.Imagem;
import br.edu.ufcg.sgbd.DB;

public class ImagemFachada {
	
	private final String TABLE_THUMBNAIL = "TABLE_THUMBNAIL";
	private final String TABLE_IMAGEM = "TABLE_IMAGEM";
	private DB db;
	
	public ImagemFachada(DB db) {
		this.db = db;
	}
	
	public void adicionarThumbnail(Thumbnail thumbnail){
		ContentValues values = new ContentValues();
		values.put("id_aluno", thumbnail.getIdAluno());
		values.put("caminho_thumbnail", thumbnail.getCaminhoThumbnail());
		
		db.insertValues(TABLE_THUMBNAIL, values);
		db.close();
	}
	
	public void adicionarImagem(Imagem imagem){
		ContentValues values = new ContentValues();
		values.put("id_aluno", imagem.getIdAluno());
		values.put("id_thumbnail", imagem.getIdThumbnail());
		values.put("caminho_imagem", imagem.getCaminhoImagem());
		
		db.insertValues(TABLE_IMAGEM, values);
		db.close();
	}
	
	public String getCaminhoImagem(Integer idThumbnail){
		String caminho = null;
		Cursor cursor = db.getReadableDatabase().query(TABLE_IMAGEM, new String[]{"caminho_imagem"}, "id_thumbnail = " + idThumbnail, null, null, null, null);
		if(cursor != null){
			cursor.moveToFirst();
			caminho = cursor.getString(0);
		}
		return caminho;
	}

	public HashMap<Integer, String> getCaminhosImagens(Integer idAluno) {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		Cursor cursor = db.getReadableDatabase().query(TABLE_IMAGEM, null, "id_aluno = " + idAluno, null, null, null, null);
		if(cursor != null){
			while(cursor.moveToNext()){
				map.put(cursor.getInt(1), cursor.getString(3));
			}
		}
		return map;
	}
	
	public List<Thumbnail> getAllThumbnails(Integer idAluno){
		List<Thumbnail> list = new ArrayList<ImagemFachada.Thumbnail>();
		Cursor cursor = db.getReadableDatabase().query(TABLE_THUMBNAIL, null, "id_aluno = " + idAluno, null, null, null, "id");
		if(cursor != null){
			while(cursor.moveToNext()){
				Thumbnail t = new Thumbnail(cursor.getInt(0), cursor.getInt(1), cursor.getString(2));
				list.add(t);
			}
		}
		return list;
	}
	
	public List<Imagem> getAllImages(Integer idAluno){
		List<Imagem> imagens = new ArrayList<ImagemFachada.Imagem>();
		Cursor cursor = db.getReadableDatabase().query(TABLE_IMAGEM, null, "id_aluno = " + idAluno, null, null, null, "id");
		if(cursor != null){
			while(cursor.moveToNext()){
				Imagem im = new Imagem(cursor.getInt(0), cursor.getInt(2), cursor.getInt(1), cursor.getString(3));
				imagens.add(im);
			}
		}
		return imagens;
	}
	
	public Thumbnail getUltimoThumbnailAdicionado(Integer idAluno) {
		List<Thumbnail> list = getAllThumbnails(idAluno);
		return list.get(list.size()-1);
	}
	
	public class Thumbnail {
		private Integer id;
		private Integer idAluno;
		private String caminhoThumbnail;
		
		public Thumbnail(Integer id, Integer idAluno, String caminhoThumbnail) {
			this.id = id;
			this.idAluno = idAluno;
			this.caminhoThumbnail = caminhoThumbnail;
		}
		
		public Thumbnail(Integer idAluno, String caminhoThumbnail) {
			this.idAluno = idAluno;
			this.caminhoThumbnail = caminhoThumbnail;
		}
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getIdAluno() {
			return idAluno;
		}
		public void setIdAluno(Integer idAluno) {
			this.idAluno = idAluno;
		}
		public String getCaminhoThumbnail() {
			return caminhoThumbnail;
		}
		public void setCaminhoThumbnail(String caminhoThumbnail) {
			this.caminhoThumbnail = caminhoThumbnail;
		}
	}
	
	public class Imagem {
		private Integer id;
		private Integer idAluno;
		private Integer idThumbnail;
		private String caminhoImagem;
		
		public Imagem(Integer id, Integer idAluno, Integer idThumbnail, String caminhoImagem) {
			this.id = id;
			this.idAluno = idAluno;
			this.idThumbnail = idThumbnail;
			this.caminhoImagem = caminhoImagem;
		}
		
		public Imagem(Integer idAluno, Integer idThumbnail, String caminhoImagem) {
			this.idAluno = idAluno;
			this.idThumbnail = idThumbnail;
			this.caminhoImagem = caminhoImagem;
		}
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getIdAluno() {
			return idAluno;
		}
		public void setIdAluno(Integer idAluno) {
			this.idAluno = idAluno;
		}
		public Integer getIdThumbnail() {
			return idThumbnail;
		}
		public void setIdThumbnail(Integer idThumbnail) {
			this.idThumbnail = idThumbnail;
		}
		public String getCaminhoImagem() {
			return caminhoImagem;
		}
		public void setCaminhoImagem(String caminhoImagem) {
			this.caminhoImagem = caminhoImagem;
		}
	}

}
