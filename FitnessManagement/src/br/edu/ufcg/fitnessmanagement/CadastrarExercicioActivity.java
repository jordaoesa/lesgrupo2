package br.edu.ufcg.fitnessmanagement;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CadastrarExercicioActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_exercicio);
		
		menuCadastrarExercicio();
	}
	
	private void menuCadastrarExercicio() {
		
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
				finish();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_cadastrar_exercicio, menu);
		return true;
	}

}
