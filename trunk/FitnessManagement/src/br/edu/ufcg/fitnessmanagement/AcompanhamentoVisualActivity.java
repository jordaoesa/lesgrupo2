package br.edu.ufcg.fitnessmanagement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import br.edu.ufcg.aluno.Dados;
import br.edu.ufcg.fachada.DadosFachada;
import br.edu.ufcg.fachada.ImagemFachada;
import br.edu.ufcg.fachada.ImagemFachada.Imagem;
import br.edu.ufcg.fachada.ImagemFachada.Thumbnail;
import br.edu.ufcg.util.FitnessManagementSingleton;
import br.edu.ufcg.util.ImageAdapterGallery;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class AcompanhamentoVisualActivity extends Activity {

	private DadosFachada dadosFachada;
	private ImagemFachada imagemFachada;
	private Integer idAluno;
	private Integer indiceFoto = 0;
	private File imagem;
	private File thumbnail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acompanhamento_visual);
		
		idAluno = getIntent().getIntExtra("id_aluno", -1);
		dadosFachada = FitnessManagementSingleton.getDadosFachadaInstance();
		imagemFachada = FitnessManagementSingleton.getImagemFachadaInstance();
		
		criaCaminhosImagens();
		menuAcompanhamentoVisual();
	}
	
	private void criaCaminhosImagens() {
		imagem = new File(FitnessManagementSingleton.getCaminhoImagemAcompanhamento());
		thumbnail = new File(FitnessManagementSingleton.getCaminhoThumbnailAcompanhamento());
		if(!imagem.exists()){
			imagem.mkdirs();
		}
		if(!thumbnail.exists()){
			thumbnail.mkdirs();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == 2013 && resultCode == Activity.RESULT_OK){
			File destino = new File(thumbnail, FitnessManagementSingleton.getNomeThumbnailAcompanhamento());
			try {
				File origem = imagem.getAbsoluteFile();
				comprimeESalvaImagem(origem, destino);
				
				Thumbnail tn = imagemFachada.new Thumbnail(idAluno, destino.getAbsolutePath());
				imagemFachada.adicionarThumbnail(tn);
				
				Imagem im = imagemFachada.new Imagem(idAluno, imagemFachada.getUltimoThumbnailAdicionado(idAluno).getId(), imagem.getAbsolutePath(), new Date());
				System.out.println("--- salvando " + imagem.getAbsolutePath());
				imagemFachada.adicionarImagem(im);
				
			} catch (IOException e) {
				System.out.println(">>> " + e.getMessage());
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
		
		finish();
		
		Intent intentAcompanhamento = new Intent(getApplicationContext(), AcompanhamentoVisualActivity.class);
		intentAcompanhamento.putExtra("id_aluno", idAluno);
		startActivity(intentAcompanhamento);
		
	}
	
	private Bitmap comprimeESalvaImagem(File origem, File destino) throws IOException {

		BitmapFactory.Options bmOpt = new BitmapFactory.Options();
		bmOpt.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(origem.getAbsolutePath(), bmOpt);
		int scale = Math.min((bmOpt.outWidth / 100), (bmOpt.outHeight / 100));

		bmOpt.inJustDecodeBounds = false;
		bmOpt.inSampleSize = scale;
		bmOpt.inPurgeable = true;

		Bitmap bitmap = BitmapFactory.decodeFile(origem.getAbsolutePath(), bmOpt);
		FileOutputStream fos = new FileOutputStream(destino);
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);

		fos.flush();
		fos.close();

		return bitmap;
	}

	private void menuAcompanhamentoVisual() {
		//final List<String> caminhosThumbnails = imagemFachada.getCaminhosThumbnails(idAluno);
		final HashMap<Integer, ImagemFachada.Imagem> imagens = imagemFachada.getImagens(idAluno);
		final List<Thumbnail> thumbnails = imagemFachada.getAllThumbnails(idAluno);
		//final List<Imagem> imagens = imagemFachada.getAllImages(idAluno);
		System.out.println("--- " + thumbnails);
		System.out.println("--- " + imagens);
		
		Button bVoltar = (Button) findViewById(R.id.buttonVoltarAcompanhamentoVisual);
		Gallery galeria = (Gallery) findViewById(R.id.galleryAcompanhamentoVisual);
		final ImageView imagemEspandida = (ImageView) findViewById(R.id.imageViewAcompanhamentoVisual);
		
		final ImageAdapterGallery imageAdapterGallery = new ImageAdapterGallery(getContentResolver(), getApplicationContext(), thumbnails);
		galeria.setAdapter(imageAdapterGallery);
		
		try {
			Uri uri = Uri.fromFile(new File((imagens.get(thumbnails.get(0).getId())).getCaminhoImagem())); //imagemFachada.getCaminhoImagem((int) imageAdapterGallery.getItemId(position))
			imagemEspandida.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), uri));
		} catch (Exception e) {
			System.out.println(">>> " + e.getMessage());
			imagemEspandida.setImageResource(android.R.drawable.ic_menu_gallery);
		}
		
		galeria.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int position, long id) {
				
				indiceFoto = position;
				Toast.makeText(getApplicationContext(), "imagem " + position, Toast.LENGTH_SHORT).show();
				if(position == (thumbnails.size()-1)){
					
					Toast.makeText(getApplicationContext(), "iniciando camera", Toast.LENGTH_SHORT).show();
					Vibrator vibrador = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
					vibrador.vibrate(500);
					
					Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					
					imagem = new File(imagem, FitnessManagementSingleton.getNomeImagemAcompanhamento());
					
					in.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagem));
					startActivityForResult(in, 2013);
				}else{
					try {
						Uri uri = Uri.fromFile(new File((imagens.get((int)imageAdapterGallery.getItemId(position))).getCaminhoImagem())); //imagemFachada.getCaminhoImagem((int) imageAdapterGallery.getItemId(position))
						imagemEspandida.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), uri));
					} catch (Exception e) {
						System.out.println(">>> " + e.getMessage());
						imagemEspandida.setImageResource(R.drawable.ic_launcher);
					}
				}
			}
		});
		
		imagemEspandida.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View arg0) {
				
				String peso = "Sem Informação";
				String braco = "Sem Informação";
				String perna = "Sem Informação";
				getInformacoesAluno((imagens.get((int)imageAdapterGallery.getItemId(indiceFoto))).getData(), peso, braco, perna);
				
				
				try {
					
					ImagemFachada.Imagem im = null;
					im = imagens.get((int)imageAdapterGallery.getItemId(indiceFoto));
					new AlertDialog.Builder(AcompanhamentoVisualActivity.this)
					.setTitle("Informações Foto " + indiceFoto)
					.setMessage("Data\t: " + (imagens.get((int)imageAdapterGallery.getItemId(indiceFoto))).getDataFormatada() +
							"\nPeso\t: " + peso + 
							"\nBraço\t: " + braco +
							"\nPerna\t: " + perna +
							"")
							.setNeutralButton("OK", null)
							.show();
				} catch (Exception e) {
					System.out.println(">>> " + e.getMessage());
				}
				return true;
			}
		});
//		imagemEspandida.setOnLongClickListener(new OnLongClickListener() {
//			@Override
//			public boolean onLongClick(View arg0) {
//				Toast.makeText(getApplicationContext(), "iniciando camera", Toast.LENGTH_SHORT).show();
//				Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//				
//				imagem = new File(imagem, FitnessManagementSingleton.getNomeImagemAcompanhamento());
//				
//				in.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagem));
//				startActivityForResult(in, 2013);
//				return false;
//			}
//		});
		
		
		bVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}
	
	private void getInformacoesAluno(Date data, String peso, String braco, String perna){
		List<Dados> dados = dadosFachada.getDadosDoAluno(idAluno);
		for(Dados dado : dados){
			if(dado.getData().equals(data)){
				peso = dado.getPeso().toString();
				braco = dado.getTamanhoBraco().toString();
				perna = dado.getTamanhoPerna().toString();
				break;
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_acompanhamento_visual, menu);
		return true;
	}

}
