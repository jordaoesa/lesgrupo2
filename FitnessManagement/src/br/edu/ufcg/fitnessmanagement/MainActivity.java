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
import android.widget.RadioGroup;
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
		inserirUsuarioNoBanco();
	}
	
	private void menuInicial() {
		setContentView(R.layout.activity_fitness_management);
		setTitle("Fitness Management");
		
		Button bCadastrar = (Button) findViewById(R.id.buttonCadastrarAlunosMain);
		Button bGerenciar = (Button) findViewById(R.id.buttonGerenciarAlunosMain);
		Button bCadastrarExercicio = (Button)findViewById(R.id.buttonCadastrarExerciciosMain);
//		Button bAddAluno = (Button) findViewById(R.id.buttonAddAlunoMain);
		
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
		bCadastrarExercicio.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				menuCadastrarExercicio();
			}
		});
//		bAddAluno.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				inserirUsuarioNoBanco();
//			}
//		});
		
	}
	
	private void menuCadastrar() {
		setContentView(R.layout.cadastrar);
		
		Button cadastrar = (Button) findViewById(R.id.buttonCadastrarAluno);
		cadastrar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				String nome = ((EditText) findViewById(R.id.editTextNome)).getText().toString();
				String endereco = ((EditText) findViewById(R.id.editTextEndereco)).getText().toString();
				RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupSexo);
				
				Aluno aluno = null;
				int sexo = radioGroup.getCheckedRadioButtonId();
				if(sexo == R.id.radioButtonSexoMasc){
					aluno = new Aluno(nome, endereco, "M");
				}else if(sexo == R.id.radioButtonSexoFemi){
					aluno = new Aluno(nome, endereco, "F");
				}
				
				alunoFachada.adicionarAluno(aluno);
				Toast.makeText(getApplicationContext(), "Cadastrado com Sucesso", Toast.LENGTH_SHORT).show();
				menuInicial();
			}
		});
		
		Button voltar = (Button) findViewById(R.id.buttonCadastrarVoltar);
		voltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				menuInicial();
			}
		});
		
	}
	
	private void menuCadastrarExercicio() {
		
		setContentView(R.layout.cadastro_exercicio);
		
//		Button botaoCadastrarExercicio = (Button) findViewById(R.id.botaoCadastrarExercicio);
//		
//		final Spinner spinnerNomeExerc = (Spinner)findViewById(R.id.spinnerExercicio);
//		final Spinner spinnerNomeMaquina = (Spinner)findViewById(R.id.spinnerMaquina);
//		final Spinner spinnerGrupoMuscular = (Spinner)findViewById(R.id.spinnerGrupoMuscular);
//		final Spinner spinnerNumSerie = (Spinner)findViewById(R.id.spinnerNumeroSerie);
//		final Spinner spinnerNumRepeticoes = (Spinner)findViewById(R.id.spinnerNumeroRepeticoes);
//		final EditText caixaObservacao = (EditText)findViewById(R.id.caixaObservacaoExercicio);
//		final EditText caixaCadExerPeso = (EditText) findViewById(R.id.caixaCadastroExercicioPeso);
//		
//		botaoCadastrarExercicio.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Exercicio exerc = new Exercicio(spinnerNomeExerc.getSelectedItem().getClass().getName(), 
//						spinnerNomeMaquina.getSelectedItem().getClass().getName(), 
//						GrupoExercicio.BRACO, 
//						Integer.parseInt(spinnerNumSerie.getSelectedItem().getClass().getName()), 
//						Integer.parseInt(spinnerNumRepeticoes.getSelectedItem().getClass().getName()), 
//						Integer.parseInt(caixaCadExerPeso.getText().toString()));
//				
//				
//				
//			}
//		});
//		
		Button botaoCancelarExercicio = (Button) findViewById(R.id.botaoCancelarCadastroExercicio);
		botaoCancelarExercicio.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				menuInicial();
			}
		});
		
	}
	
	private void inserirUsuarioNoBanco() {
		Aluno jordao = new Aluno("aluno", "Campina", "M");
		
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
