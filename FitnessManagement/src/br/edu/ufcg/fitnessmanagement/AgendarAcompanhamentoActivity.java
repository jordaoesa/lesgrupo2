package br.edu.ufcg.fitnessmanagement;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AgendarAcompanhamentoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agendar_acompanhamento);
		setTitle("Agendar Acompanhamento");
		
		menuAgendamento();
	}


	private void menuAgendamento() {
		Button bVoltar = (Button) findViewById(R.id.buttonVoltarAgendarAcompanhamento);
		Button bAgendar = (Button) findViewById(R.id.buttonAgendarAgendarAcompanhamento);
		
		
		bAgendar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//ADICIONAR DADOS DE VALOR AS DIVIDAS DO ALUNO //TODO
			}
		});
		bVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_agendar_acompanhamento, menu);
		return true;
	}

}
