package br.edu.ufcg.fitnessmanagement;

import br.edu.ufcg.fachada.GrupoMuscularFachada;
import br.edu.ufcg.gerenciar.Atividade;
import br.edu.ufcg.util.FitnessManagementSingleton;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CadastrarAtividadeActivity extends Activity {
	
//	private Integer idAluno;
//	private String nomeExercicio;
//	private String nomeMaquina;
//	private String nomeGurpoMuscular;
//	private int series;
//	private int repeticoes;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_cadastrar_atividade);
//		
//		idAluno = getIntent().getIntExtra("id_aluno", -1);
//		
//		menuCadastrarExercicio();
//	}
//	
//
//	private void menuCadastrarExercicio() {
//		
//		Button botaoCadastrarExercicio = (Button) findViewById(R.id.botaoCadastrarExercicio);
//		
//		final EditText caixaObservacao = (EditText)findViewById(R.id.caixaObservacaoExercicio);
//		final EditText caixaCadExerPeso = (EditText) findViewById(R.id.caixaCadastroExercicioPeso);
//		
//		preencheSpinnerExercicio();
//		preencheSpinnerMaquina();
//		preencheSpinnerGrupoMuscular();
//		preencheSpinnerRepeticoes();
//		preencheSpinnerSerie();
//		
//		
//		botaoCadastrarExercicio.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Editable etPeso = (Editable) caixaCadExerPeso.getText();
//				if(etPeso == null || etPeso.toString().equals("")){
//					Toast.makeText(getApplicationContext(), "Peso Inválido", Toast.LENGTH_SHORT).show();
//				}else{
//					try{
//						int peso = Integer.parseInt(caixaCadExerPeso.getText().toString());
//						String observacao = caixaObservacao.getText().toString();
//						
//						Atividade atividade = new Atividade(series, repeticoes, peso, nomeExercicio, nomeMaquina, nomeGurpoMuscular,  observacao);
//						FitnessManagementSingleton.getAtividadeFachadaInstance().adicionaAtividade(idAluno, atividade);
//						
//						Toast.makeText(getApplicationContext(), "aluno: " + idAluno + " - exercicio: " + atividade.getNomeExercicio(), Toast.LENGTH_SHORT).show();
//						finish();
//					}catch(NumberFormatException nfe){
//						Toast.makeText(getApplicationContext(), "Peso Inválido", Toast.LENGTH_SHORT).show();
//					}
//				}
//			}
//		});
//		
//		Button botaoCancelarExercicio = (Button) findViewById(R.id.botaoCancelarCadastroExercicio);
//		botaoCancelarExercicio.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				finish();
//			}
//		});
//		
//	}
//
//	private void preencheSpinnerRepeticoes() {
//		Spinner spinnerRepeticoes = (Spinner)findViewById(R.id.spinnerNumeroRepeticoes);
//		Object[] a = {"8","10","12","15","20"};
//		ArrayAdapter<Object> arrayAdapter = new ArrayAdapter<Object>(this, android.R.layout.simple_spinner_item, a );
//		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spinnerRepeticoes.setAdapter(arrayAdapter);			
//		
//		capturarNumeroRepeticoes(spinnerRepeticoes);
//		
//	}
//
//	private void preencheSpinnerSerie() {
//		Spinner spinnerSerie = (Spinner)findViewById(R.id.spinnerNumeroSerie);
//		Object[] a = {"2","3","4","5","6","7"};
//		ArrayAdapter<Object> arrayAdapter = new ArrayAdapter<Object>(this, android.R.layout.simple_spinner_item, a );
//		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spinnerSerie.setAdapter(arrayAdapter);			
//		
//		capturarNumeroSeries(spinnerSerie);
//		
//	}
//
//	private void preencheSpinnerGrupoMuscular() {
//		Spinner spinnerGrupoMuscular = (Spinner)findViewById(R.id.spinnerGrupoMuscular);
//		Object[] a =  FitnessManagementSingleton.getGrupoMuscularFachadaInstance().getDadosGrupoMusculares().toArray();
//		ArrayAdapter<Object> arrayAdapter = new ArrayAdapter<Object>(this, android.R.layout.simple_spinner_item, a );
//		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spinnerGrupoMuscular.setAdapter(arrayAdapter);		
//		
//		capturarNomeGrupoMuscular(spinnerGrupoMuscular);
//	}
//
//
//	private void preencheSpinnerMaquina() {
//		Spinner spinnerNomeMaquina = (Spinner)findViewById(R.id.spinnerMaquina);
//		Object[] a =  FitnessManagementSingleton.getMaquinaFachadaInstance().getDadosMaquinas().toArray();
//		ArrayAdapter<Object> arrayAdapter = new ArrayAdapter<Object>(this, android.R.layout.simple_spinner_item, a );
//		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spinnerNomeMaquina.setAdapter(arrayAdapter);
//		
//		capturarNomeMaquina(spinnerNomeMaquina);
//	}
//
//	private void preencheSpinnerExercicio() {
//		Spinner spinnerNomeExerc = (Spinner)findViewById(R.id.spinnerExercicio);
//		Object[] a =  FitnessManagementSingleton.getExercicioFachadaInstance().getDadosExercicios().toArray();
//		ArrayAdapter<Object> arrayAdapter = new ArrayAdapter<Object>(this, android.R.layout.simple_spinner_item, a );
//		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spinnerNomeExerc.setAdapter(arrayAdapter);
//		
//		capturarNomeExercicio(spinnerNomeExerc);
//	}
//	
//	private void capturarNomeExercicio(Spinner spinner){
//		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {	
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
//				//pega nome pela posi��o
//				 nomeExercicio = parent.getItemAtPosition(posicao).toString();
//			}
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//			}
//		});
//	}
//	private void capturarNomeMaquina(Spinner spinner){
//		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {	
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
//				//pega nome pela posi��o
//				 nomeMaquina = parent.getItemAtPosition(posicao).toString();
//			}
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//			}
//		});
//	}
//	private void capturarNomeGrupoMuscular(Spinner spinner){
//		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {	
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
//				//pega nome pela posi��o
//				 nomeGurpoMuscular = parent.getItemAtPosition(posicao).toString();
//			}
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//			}
//		});
//	}
//	private void capturarNumeroSeries(Spinner spinner){
//		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {	
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
//				//pega nome pela posi��o
//				 series = Integer.parseInt(parent.getItemAtPosition(posicao).toString());
//			}
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//			}
//		});
//	}
//	private void capturarNumeroRepeticoes(Spinner spinner){
//		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {	
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
//				//pega nome pela posi��o
//				repeticoes  = Integer.parseInt(parent.getItemAtPosition(posicao).toString());
//			}
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//			}
//		});
//	}
//
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_cadastrar_exercicio, menu);
//		return true;
//	}

}
