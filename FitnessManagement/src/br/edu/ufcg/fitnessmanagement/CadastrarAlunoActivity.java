package br.edu.ufcg.fitnessmanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import br.edu.ufcg.aluno.Aluno;
import br.edu.ufcg.fachada.AlunoFachada;
import br.edu.ufcg.util.FitnessManagementSingleton;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings.System;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CadastrarAlunoActivity extends Activity {

	private File file;
	private Uri uri;
	private AlunoFachada alunoFachada;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_aluno);
		setTitle("Cadastrar Novo Aluno");
		
		alunoFachada = FitnessManagementSingleton.getAlunoFachadaInstance();
		menuCadastrar();
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
				
				String nome = ((EditText) findViewById(R.id.editTextNomeMenuCadastrar)).getText().toString();
				String endereco = ((EditText) findViewById(R.id.editTextEnderecoMenuCadastrar)).getText().toString();
				String telefone = ((EditText) findViewById(R.id.editTextTelefoneMenuCadastrar)).getText().toString();
				Integer idade = Integer.parseInt(((EditText) findViewById(R.id.editTextIdadeMenuCadastrar)).getText().toString());
				final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupSexoMenuCadastrar);
				Aluno aluno = null;
				
				
				int sexo = radioGroup.getCheckedRadioButtonId();
				if(sexo == R.id.radioButtonSexoMasMenuCadastrar){
					aluno = new Aluno(nome, idade, endereco, "Masculino", telefone);
				}else if(sexo == R.id.radioButtonSexoFemMenuCadastrar){
					aluno = new Aluno(nome, idade, endereco, "Feminino", telefone);
				}
				
				alunoFachada.adicionarAluno(aluno);
				Toast.makeText(getApplicationContext(), "Cadastrado com Sucesso", Toast.LENGTH_SHORT).show();
				finish();
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_cadastrar_aluno, menu);
		return true;
	}

}
