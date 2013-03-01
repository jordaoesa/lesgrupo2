package br.edu.ufcg.fitnessmanagement;

import br.edu.ufcg.util.FitnessManagementSingleton;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CadastrarExercicioActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_exercicio);
		
		menuCadastrarExercicio();
	}
	
	
	
	
	
	
	private void menuCadastrarExercicio() {
		
		Button botaoCadastrarExercicio = (Button) findViewById(R.id.botaoCadastrarExercicio);
		
		
		final Spinner spinnerNomeMaquina = (Spinner)findViewById(R.id.spinnerMaquina);
		final Spinner spinnerGrupoMuscular = (Spinner)findViewById(R.id.spinnerGrupoMuscular);
		final Spinner spinnerNumSerie = (Spinner)findViewById(R.id.spinnerNumeroSerie);
		final Spinner spinnerNumRepeticoes = (Spinner)findViewById(R.id.spinnerNumeroRepeticoes);
		final EditText caixaObservacao = (EditText)findViewById(R.id.caixaObservacaoExercicio);
		final EditText caixaCadExerPeso = (EditText) findViewById(R.id.caixaCadastroExercicioPeso);
		
		preencheSpinnerExercicio();
		
		
		botaoCadastrarExercicio.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Atividade exerc = new Atividade(nomeExercicio, nomeMaquina, grupoExercicio, series, repeticoes, peso);
				
				
				
				
			}
		});
		
		Button botaoCancelarExercicio = (Button) findViewById(R.id.botaoCancelarCadastroExercicio);
		botaoCancelarExercicio.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		
	}

	private void preencheSpinnerExercicio() {
		Spinner spinnerNomeExerc = (Spinner)findViewById(R.id.spinnerExercicio);
		
		Object[] a =  FitnessManagementSingleton.getExercicioFachadaInstance().getDadosExercicios().toArray();
		System.out.println(a[0]);
		
		ArrayAdapter<Object> arrayAdapter = new ArrayAdapter<Object>(this, android.R.layout.simple_spinner_item, a );
		
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerNomeExerc.setAdapter(arrayAdapter);
		
	}






	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_cadastrar_exercicio, menu);
		return true;
	}

}
