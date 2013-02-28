package br.edu.ufcg.fitnessmanagement;

import br.edu.ufcg.aluno.Aluno;
import br.edu.ufcg.fachada.AlunoFachada;
import br.edu.ufcg.util.FitnessManagementSingleton;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CadastrarAlunoActivity extends Activity {

	private AlunoFachada alunoFachada;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_aluno);
		setTitle("Cadastrar Novo Aluno");
		
		alunoFachada = FitnessManagementSingleton.getAlunoFachadaInstance();
		menuCadastrar();
	}
	
	private void menuCadastrar() {
		
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
					aluno = new Aluno(nome, endereco, "Masculino", telefone, idade);
				}else if(sexo == R.id.radioButtonSexoFemMenuCadastrar){
					aluno = new Aluno(nome, endereco, "Feminino", telefone, idade);
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
