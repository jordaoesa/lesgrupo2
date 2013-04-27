package br.edu.ufcg.fitnessmanagement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
import android.view.MenuItem;
import android.view.SubMenu;
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
	private String peso = "Sem Informação";
	private String altura = "Sem Informação";
	private String bracoEC = "Sem Informação";
	private String bracoDC = "Sem Informação";
	private String coxaE = "Sem Informação";
	private String coxaD = "Sem Informação";
	private String panturrilhaE = "Sem Informação";
	private String panturrilhaD = "Sem Informação";
	
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
				long photoId = imageAdapterGallery.getItemId(indiceFoto);
				if(photoId != -1) getInformacoesAluno((imagens.get((int)photoId)).getDataFormatada());
				try {
					ImagemFachada.Imagem im = imagens.get((int)imageAdapterGallery.getItemId(indiceFoto));
					new AlertDialog.Builder(AcompanhamentoVisualActivity.this)
					.setTitle("Informações do Aluno")
					.setMessage("Data: " + im.getDataFormatada() +
							"\nPeso: " + peso + " kg" +
							"\nAltura: " + altura + " cm" +
							"\nBraço Esquerdo: " + bracoEC + " cm" +
							"\nBraço Direito: " + bracoDC + " cm" +
							"\nCoxa Esquerda: " + coxaE + " cm" +
							"\nCoxa Direita: " + coxaD + " cm" +
							"\nPanturrilha Esquerda: " + panturrilhaE + " cm" +
							"\nPanturrilha Direita: " + panturrilhaD + " cm")
					.setNeutralButton("OK", null)
					.show();
				} catch (Exception e) {
					System.out.println(">>> " + e.getMessage());
				}
				return true;
			}

			private void getInformacoesAluno(String data) {
				List<Dados> dados = dadosFachada.getDadosDoAluno(idAluno);
				for(Dados dado : dados){
					String date = (new SimpleDateFormat("dd/MM/yyyy")).format(dado.getData());
					if(date.equals(data)){
						peso = dado.getPeso().toString();
						altura = dado.getAltura().toString();
						bracoEC = dado.getBracoEC().toString();
						bracoDC = dado.getBracoDC().toString();
						coxaE = dado.getCoxaE().toString();
						coxaD = dado.getCoxaD().toString();
						panturrilhaE = dado.getPanturrilhaE().toString();
						panturrilhaD = dado.getPanturrilhaD().toString();
						break;
					}
				}
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
	

	
	private final int VOLTAR = 1;
	private final int AJUDA = 2;
	
	private final int acompanhamentoVisual=3;
	String ajuda = "Ajuda";
	String voltar = "Voltar";
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_main, menu);
//		return true;

		
		boolean r = super.onCreateOptionsMenu(menu);
		super.onCreateOptionsMenu(menu);
		//menu.add(0, AJUDA, 0, ajuda).setIcon(R.drawable.alert);
		menu.add(0, VOLTAR, 0, voltar).setIcon(R.drawable.back);
		
		
		SubMenu menuAjuda = menu.addSubMenu(ajuda);
		menuAjuda.setIcon(R.drawable.help);
		menuAjuda.add(0, acompanhamentoVisual, 0, "Acompanhamento Visual");
		
		return r;
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		switch(item.getItemId()){
		
		
		case VOLTAR: mensagemExibir("Voltar", "Voltando",R.drawable.back); finish();  break;
		case AJUDA: mensagemExibir("Ajuda", "Acompanhamento Visual",R.drawable.help); break;
		case acompanhamentoVisual: mensagemExibir("Acompanhamento Visual", "Tire fotos do seu aluno e tenha um acompanhamento visual da evolucao do seu aluno.\n" +
				"Para tirar uma nova foto pressione a foto mais recente.\n" +
				"Pressionando uma foto voce sera capaz de visualizar algumas informacoes estaticas e a data em que a foto foi tirada.",R.drawable.help); break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public void mensagemExibir(String titulo, String texto,int icon){
		AlertDialog.Builder mensagem = new AlertDialog.Builder(this);
		mensagem.setTitle(titulo);
		mensagem.setMessage(texto);
		mensagem.setIcon(icon);
		mensagem.setNeutralButton("OK", null);
		mensagem.show();
	}

}
