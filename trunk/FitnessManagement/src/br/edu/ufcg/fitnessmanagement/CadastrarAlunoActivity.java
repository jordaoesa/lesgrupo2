package br.edu.ufcg.fitnessmanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import br.edu.ufcg.aluno.Aluno;
import br.edu.ufcg.fachada.AlunoFachada;
import br.edu.ufcg.util.FitnessManagementSingleton;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.PatternMatcher;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CadastrarAlunoActivity extends Activity {

	private File file;
	private Uri uri;
	private ImageView foto;
	private ImageButton botao;
	private AlunoFachada alunoFachada;
	private Bitmap bm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_aluno);
		setTitle("Cadastrar Novo Aluno");
		
		alunoFachada = FitnessManagementSingleton.getAlunoFachadaInstance();
		foto();
		menuCadastrar();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == 2013 && resultCode == Activity.RESULT_OK){
			//System.out.println(">>> " + data);
			//Toast.makeText(getApplicationContext(), data.toString(), Toast.LENGTH_SHORT).show();
			bm = (Bitmap) data.getExtras().get("data");
			
			foto = (ImageView) findViewById(R.id.imageViewFotoCadastro);
			foto.setImageBitmap(bm);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	private void foto() {
		botao = (ImageButton) findViewById(R.id.imageButtonCapturarFotoCadastro);
		//file = new File(Environment.getExternalStorageDirectory() + "/.FitnessManagement/Fotos/Perfil/br.ufcg.edu.perfil_" + (new Date()).getDate() + ".jpg");
		file = new File(FitnessManagementSingleton.getCAMINHO_FOTO_PERFIL());
		if(!file.exists()){
			file.mkdirs();
		}
		file = new File(file, FitnessManagementSingleton.getNomeFotoPerfil());
		uri = Uri.fromFile(file);
		
		botao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "iniciando camera", Toast.LENGTH_SHORT).show();
				Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(in, 2013);
			}
		});
		
		
	}

//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		if(requestCode == 0 && resultCode == Activity.RESULT_OK){
//			ImageView ivCapturar = (ImageView) findViewById(R.id.imageViewCapturarFotoCadastrar);
//			try {
//				ivCapturar.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), uri));
//			}catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		super.onActivityResult(requestCode, resultCode, data);
//	}
	
	private void menuCadastrar() {
//		file = new File(Environment.getRootDirectory(), ".FitnessManagement/Pictures/Perfil/"++".jpg");
//		uri = Uri.fromFile(file);
//		final ImageView ivCapturar = (ImageView) findViewById(R.id.imageViewCapturarFotoCadastrar);
//		ivCapturar.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//				in.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//				startActivityForResult(in, 0);
//			}
//		});
		
		Button cadastrar = (Button) findViewById(R.id.buttonCadastrarMenuCadastrar);
		cadastrar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				FileOutputStream fos;
				try {
					fos = new FileOutputStream(file);
					bm.compress(Bitmap.CompressFormat.PNG, 100, fos);
					fos.flush();
					fos.close();
				} catch (Exception e) {
					System.out.println(">>> " + e.getMessage());
				}
				
				Editable nome = ((EditText) findViewById(R.id.editTextNomeMenuCadastrar)).getText();
				Editable endereco = ((EditText) findViewById(R.id.editTextEnderecoMenuCadastrar)).getText();
				Editable telefone = ((EditText) findViewById(R.id.editTextTelefoneMenuCadastrar)).getText();
				Editable idade = ((EditText) findViewById(R.id.editTextIdadeMenuCadastrar)).getText();
				final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupSexoMenuCadastrar);
				Aluno aluno = null;
				
				if(verificaDadosEntrada(nome, endereco, telefone, idade)){
					int sexo = radioGroup.getCheckedRadioButtonId();
					if(sexo == R.id.radioButtonSexoMasMenuCadastrar){
						aluno = new Aluno(nome.toString(), Integer.parseInt(idade.toString()), endereco.toString(), "Masculino", telefone.toString());
					}else if(sexo == R.id.radioButtonSexoFemMenuCadastrar){
						aluno = new Aluno(nome.toString(), Integer.parseInt(idade.toString()), endereco.toString(), "Feminino", telefone.toString());
					}
					
					aluno.setCaminhoImagem(file.getAbsolutePath());//TODO MODIFICAR O CONSTRUTOR DE ALUNO PARA RECEBER O CAMINHO
					alunoFachada.adicionarAluno(aluno);
					Toast.makeText(getApplicationContext(), "Cadastrado com Sucesso", Toast.LENGTH_SHORT).show();
					finish();
				}
			}

		});
		
		Button voltar = (Button) findViewById(R.id.buttonVoltarMenuCadastrar);
		voltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		
	}
	
	private boolean verificaDadosEntrada(Editable nome, Editable endereco, Editable telefone, Editable idade) {
		if(nome == null || nome.toString().equals("")){
			Toast.makeText(getApplicationContext(), "Nome Inválido", Toast.LENGTH_SHORT).show();
			return false;
		}else if(endereco == null || endereco.toString().equals("")){
			Toast.makeText(getApplicationContext(), "Endereço Inválido", Toast.LENGTH_SHORT).show();
			return false;
		}else if(telefone == null || telefone.toString().equals("") || !telefone.toString().matches("\\([0-9]{2}\\)\\s[2-9]{1}[0-9]{3}\\-[0-9]{4}")){
			Toast.makeText(getApplicationContext(), "Telefone Inválido", Toast.LENGTH_SHORT).show();
			return false;
		}else if(idade == null || idade.toString().equals("")){
			Toast.makeText(getApplicationContext(), "Idade Inválida", Toast.LENGTH_SHORT).show();
		}else{
			try{
				Integer.parseInt(idade.toString());
			}catch(NumberFormatException nfe){
				Toast.makeText(getApplicationContext(), "Idade Inválida", Toast.LENGTH_SHORT).show();
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_cadastrar_aluno, menu);
		return true;
	}

}
