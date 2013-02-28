package br.edu.ufcg.fitnessmanagement;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;
import br.edu.ufcg.aluno.Aluno;
import br.edu.ufcg.aluno.Dados;
import br.edu.ufcg.fachada.AlunoFachada;
import br.edu.ufcg.fachada.DadosFachada;
import br.edu.ufcg.sgbd.DB;
import br.edu.ufcg.util.FitnessManagementSingleton;

public class MainActivity extends Activity {
	
	private DB db;
	private AlunoFachada alunoFachada;
	private DadosFachada dadosFachada;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		FitnessManagementSingleton.setContext(this);
		db = FitnessManagementSingleton.getDBInstance();
		alunoFachada = FitnessManagementSingleton.getAlunoFachadaInstance();
		dadosFachada = FitnessManagementSingleton.getDadosFachadaInstance();
		
		menuInicial();
	}
	
	private void menuInicial() {
		setContentView(R.layout.activity_fitness_management);
		setTitle("Fitness Management");
		
		Button bCadastrar = (Button) findViewById(R.id.buttonCadastrarAlunosMain);
		Button bGerenciar = (Button) findViewById(R.id.buttonGerenciarAlunosMain);
		Button bAddAluno = (Button) findViewById(R.id.buttonAddAlunoMain);
		
		final Intent intentGerenciar = new Intent(this, VisualizarAlunosActivity.class);
		
		bCadastrar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				menuCadastrar();
			}
		});
		bGerenciar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(intentGerenciar);
			}
		});
		bAddAluno.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				inserirUsuarioNoBanco();
			}
		});
		
	}
	
	private void menuCadastrar() {
		setContentView(R.layout.cadastrar);
		
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
				menuInicial();
			}
		});
		
		Button voltar = (Button) findViewById(R.id.buttonVoltarMenuCadastrar);
		voltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				menuInicial();
			}
		});
		
	}
	
	private void inserirUsuarioNoBanco() {
		Aluno jordao = new Aluno("aluno", "Campina", "Masculino", "(83) 9999-9999", 20);
		
		Dados dados1Jordao = new Dados(75.0, 100.0, 31.0, 55.0, 20.0, new Date("01/01/2013"));
		Dados dados2Jordao = new Dados(76.0, 200.0, 32.0, 57.0, 21.0, new Date("02/01/2013"));
		Dados dados3Jordao = new Dados(77.0, 50.0, 33.0, 60.0, 21.0, new Date("03/01/2013"));

		Dados dados4Jordao = new Dados(78.0, 100.0, 34.0, 51.0, 20.0, new Date("04/01/2013"));
		Dados dados5Jordao = new Dados(79.0, 200.0, 35.0, 52.0, 21.0, new Date("05/01/2013"));
		Dados dados6Jordao = new Dados(82.0, 50.0, 36.0, 63.0, 21.0, new Date("06/01/2013"));
		
		Dados dados7Jordao = new Dados(85.0, 100.0, 37.0, 54.0, 20.0, new Date("07/01/2013"));
		Dados dados8Jordao = new Dados(89.0, 200.0, 38.0, 55.0, 21.0, new Date("08/01/2013"));
		Dados dados9Jordao = new Dados(97.0, 50.0, 39.0, 66.0, 21.0, new Date("09/01/2013"));
		
		alunoFachada.adicionarAluno(jordao);
		int id = alunoFachada.getIdUltimoAlunoAdicionado();
		
		dadosFachada.adicionaDadosDoAluno(id, dados1Jordao);
		dadosFachada.adicionaDadosDoAluno(id, dados2Jordao);
		dadosFachada.adicionaDadosDoAluno(id, dados3Jordao);
		dadosFachada.adicionaDadosDoAluno(id, dados4Jordao);
		dadosFachada.adicionaDadosDoAluno(id, dados5Jordao);
		dadosFachada.adicionaDadosDoAluno(id, dados6Jordao);
		dadosFachada.adicionaDadosDoAluno(id, dados7Jordao);
		dadosFachada.adicionaDadosDoAluno(id, dados8Jordao);
		dadosFachada.adicionaDadosDoAluno(id, dados9Jordao);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
