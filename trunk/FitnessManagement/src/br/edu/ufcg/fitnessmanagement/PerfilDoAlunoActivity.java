package br.edu.ufcg.fitnessmanagement;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;
import br.edu.ufcg.aluno.Aluno;
import br.edu.ufcg.fachada.AlunoFachada;
import br.edu.ufcg.util.FitnessManagementSingleton;

public class PerfilDoAlunoActivity extends Activity {

	private File file;
	private Uri uri;
	private AlunoFachada alunoFachada;
	private Aluno aluno;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		alunoFachada = FitnessManagementSingleton.getAlunoFachadaInstance();
		getAluno();
		//file = new File(Environment.getExternalStorageDirectory() + "/.FitnessManagement/Fotos/Perfil");
		file = new File(FitnessManagementSingleton.getCaminhoFotoPerfil());
		if(!file.exists()){
			file.mkdirs();
		}
		//file = new File(FitnessManagementSingleton.getFotoPerfil());
		file = new File(file, FitnessManagementSingleton.getNomeFotoPerfil());
		//file = new File(file, "br.ufcg.edu.perfil_" + (new Date()).getDate() + ".jpg");
		System.out.println(file.getAbsolutePath());
		uri = Uri.fromFile(file);
		menuDeOpcoes();
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == 2013 && resultCode == Activity.RESULT_OK){
			//System.out.println(">>> " + data);
			//Toast.makeText(getApplicationContext(), data.toString(), Toast.LENGTH_SHORT).show();
			Bitmap bm = (Bitmap) data.getExtras().get("data");
			
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(file);
				bm.compress(Bitmap.CompressFormat.PNG, 100, fos);
				fos.flush();
				fos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				(new File(aluno.getCaminhoImagem())).delete(); // DELETAR IMAGEM CASO SEJA UMA NOVA FOTO
				aluno.setCaminhoImagem(file.getAbsolutePath());
				alunoFachada.updateCaminhoFoto(aluno.getId(), file.getAbsolutePath());
			} catch (Exception e) {
				System.out.println(">>> " + e.getMessage());
			}
			
			QuickContactBadge qcbFoto = (QuickContactBadge) findViewById(R.id.quickContactBadgePerfilAluno);
			qcbFoto.setImageBitmap(bm);
		}
		super.onActivityResult(requestCode, resultCode, data);
		
		finish();
		
		Intent activityFicha = new Intent(this, PerfilDoAlunoActivity.class);
		activityFicha.putExtra("id_aluno", aluno.getId());
		startActivity(activityFicha);
	}
	
	@Override
	public void onBackPressed() {
		Intent activityVisualizar = new Intent(getApplicationContext(), VisualizarAlunosActivity.class);
		startActivity(activityVisualizar);
		super.onBackPressed();
	}
	
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		if(requestCode == 0 && resultCode == Activity.RESULT_OK){
//			QuickContactBadge qcbFoto = (QuickContactBadge) findViewById(R.id.quickContactBadgePerfilAluno);
//			try {
//				Bitmap bm = comprimeESalvaImagem();
//				qcbFoto.setImageBitmap(bm);			
//				//qcbFoto.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), uri));
//			}catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		super.onActivityResult(requestCode, resultCode, data);
//	}
//
//	private Bitmap comprimeESalvaImagem() throws IOException {
//		
//		BitmapFactory.Options bmOpt = new BitmapFactory.Options();
//		bmOpt.inJustDecodeBounds = true;
//		BitmapFactory.decodeFile(file.getAbsolutePath(), bmOpt);
//		int scale = Math.min((bmOpt.outWidth/100), (bmOpt.outHeight/100));
//		
//		bmOpt.inJustDecodeBounds = false;
//		bmOpt.inSampleSize = scale;
//		bmOpt.inPurgeable = true;
//		
//		Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), bmOpt);
//		FileOutputStream fos = new FileOutputStream(file);
//		bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
//		
//		fos.flush();
//		fos.close();
//		
//		return bitmap;
//	}

	private void getAluno() {
		int idAluno = getIntent().getIntExtra("id_aluno", -1);
		aluno = alunoFachada.getAlunoFromId(idAluno);
	}

	private void menuDeOpcoes() {
		setContentView(R.layout.activity_perfil_do_aluno);
		setTitle("Perfil do Aluno");
		
		QuickContactBadge qcbFoto = (QuickContactBadge) findViewById(R.id.quickContactBadgePerfilAluno);
		TextView tvNome = (TextView) findViewById(R.id.textViewNomePerfilAluno);
		TextView tvTelefone = (TextView) findViewById(R.id.textViewTelefonePerfilAluno);
		TextView tvIdade = (TextView) findViewById(R.id.textViewIdadePerfilAluno);
		TextView tvSexo = (TextView) findViewById(R.id.textViewSexoPerfilAluno);
		
		tvNome.setText(aluno.getNome());
		tvTelefone.setText(aluno.getTelefone().toString());
		tvIdade.setText(aluno.getIdade().toString());
		tvSexo.setText(aluno.getSexo());
		try {
			qcbFoto.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(new File(aluno.getCaminhoImagem()))));
		} catch (Exception e) {
			System.out.println(">>> " + e.getMessage());
		}
		
		Button bAvaliacaoDoAluno = (Button) findViewById(R.id.buttonAvaliacaoDoAlunoPerfilAluno);
		Button bGerenciarFinancas = (Button) findViewById(R.id.buttonGerenciarFinancasPerfilAluno);
		Button bAgendarAcompanhamento = (Button) findViewById(R.id.buttonAgendarAcompanhamentoPerfilAluno);
		Button bAcompanhamentoVisual = (Button) findViewById(R.id.buttonAcompanhamentoVisualPerfilAluno);
		Button bTreinoDoAluno = (Button) findViewById(R.id.buttonTreinoDoAluno);
		Button bMeta = (Button)findViewById(R.id.buttonPerfilMeta);
		
		bMeta.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentAvaliarTabs = new Intent(getApplicationContext(), MetasActivity.class);
				intentAvaliarTabs.putExtra("id_aluno", aluno.getId());
				startActivity(intentAvaliarTabs);
				
			}
		});
		
		bAvaliacaoDoAluno.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//Intent intentAvaliar = new Intent(getApplicationContext(), AvaliacaoDoAlunoActivity.class);
				//intentAvaliar.putExtra("id_aluno", aluno.getId());
				//startActivity(intentAvaliar);
				Intent intentAvaliarTabs = new Intent(getApplicationContext(), AvaliacaoTabsActivity.class);
				intentAvaliarTabs.putExtra("id_aluno", aluno.getId());
				startActivity(intentAvaliarTabs);
			}
		});
		
		bGerenciarFinancas.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intentFinancas = new Intent(getApplicationContext(), GerenciarFinancasActivity.class);
				intentFinancas.putExtra("id_aluno", aluno.getId());
				startActivity(intentFinancas);
			}
		});
		bAgendarAcompanhamento.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentAgendar = new Intent(getApplicationContext(), AgendarAcompanhamentoActivity.class);
				intentAgendar.putExtra("id_aluno", aluno.getId());
				startActivity(intentAgendar);
			}
		});
		qcbFoto.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
//				Toast.makeText(getApplicationContext(), "iniciando camera", Toast.LENGTH_SHORT).show();
//				capturarImagem();
				capturar();
				return false;
			}
		});
		bTreinoDoAluno.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final Intent intent = new Intent(getApplicationContext(), GenciarTreinoSemanal.class);
				intent.putExtra("id_aluno", aluno.getId());
				startActivity(intent);
				
			}
		});
//		qcbFoto.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				Toast.makeText(getApplicationContext(), "iniciando camera", Toast.LENGTH_SHORT).show();
//				try {
//					aluno.setCaminhoImagem(file.getAbsolutePath());
//					alunoFachada.updateCaminhoFoto(aluno.getId(), file.getAbsolutePath());
//				} catch (Exception e) {
//					System.out.println(">>> " + e.getMessage());
//				}
//				Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//				startActivityForResult(in, 2013);
//			}
//		});
		
		bAcompanhamentoVisual.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentAcompanhamento = new Intent(getApplicationContext(), AcompanhamentoVisualActivity.class);
				intentAcompanhamento.putExtra("id_aluno", aluno.getId());
				startActivity(intentAcompanhamento);
			}
		});
		
		Button voltar = (Button) findViewById(R.id.buttonVoltarPerfilAluno);
		voltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
				Intent activityVisualizar = new Intent(getApplicationContext(), VisualizarAlunosActivity.class);
				startActivity(activityVisualizar);
			}
		});
		
	}
	
	private void capturar() {
		Toast.makeText(getApplicationContext(), "iniciando camera", Toast.LENGTH_SHORT).show();
		Vibrator vibrador = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		vibrador.vibrate(500);
		
		Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(in, 2013);
	}
	
//	private void capturarImagem(){
//		try {
//			aluno.setCaminhoImagem(file.getAbsolutePath());
//			alunoFachada.updateCaminhoFoto(aluno.getId(), file.getAbsolutePath());
//		} catch (Exception e) {
//			System.out.println(">>> " + e.getMessage());
//		}
//		Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//		in.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//		startActivityForResult(in, 0);
//	}
	
	

	private final int SAIR = 1;
	private final int AJUDA = 2;
	
	private final int fichaDoAluno=3;
	String ajuda = "Ajuda";
	String sair = "Sair";
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_main, menu);
//		return true;

		
		boolean r = super.onCreateOptionsMenu(menu);
		super.onCreateOptionsMenu(menu);
		//menu.add(0, AJUDA, 0, ajuda).setIcon(R.drawable.alert);
		menu.add(0, SAIR, 0, sair).setIcon(R.drawable.alert);
		
		
		SubMenu menuAjuda = menu.addSubMenu(ajuda);
		menuAjuda.add(0, fichaDoAluno, 0, "Ficha do Aluno");
		
		return r;
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		switch(item.getItemId()){
		
		
		case SAIR: mensagemExibir("Sair", "Saindo"); finish();  break;
		case AJUDA: mensagemExibir("Ajuda", "Menu Ajuda"); break;
		case fichaDoAluno: mensagemExibir("Ficha do Aluno", "Em Avaliação do Aluno podemos visualizar algumas informacoes sobre o aluno.\n" +
				"Em Agendar Acompanhamento, agendamos horarios com o aluno.\n" +
				"Em Acompanhamento visual, visualizamos fotos do aluno.\n" +
				"Em Gerenciar financas, podemos quitar ou adicionar dividas do aluno.\n" +
				"Em Treino do Aluno, cadastramos atividades fisicas a serem executadas pelo aluno."); break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public void mensagemExibir(String titulo, String texto){
		AlertDialog.Builder mensagem = new AlertDialog.Builder(this);
		mensagem.setTitle(titulo);
		mensagem.setMessage(texto);
		mensagem.setNeutralButton("OK", null);
		mensagem.show();
	}


}
