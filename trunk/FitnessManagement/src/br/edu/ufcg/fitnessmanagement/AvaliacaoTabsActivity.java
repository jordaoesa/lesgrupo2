package br.edu.ufcg.fitnessmanagement;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class AvaliacaoTabsActivity extends TabActivity {

	private Integer idAluno;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_avaliacao_tabs);
		setTitle("Avaliação");
		
		idAluno = getIntent().getIntExtra("id_aluno", -1);
		
		Button bVoltar = (Button) findViewById(R.id.buttonVoltarAvaliacaoTabs);
		bVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		initTabs();
	}

	private void initTabs() {
		TabHost tabHost = getTabHost();
		
		Intent intentAnamnese = new Intent(getApplicationContext(), AnamneseActivity.class);
		intentAnamnese.putExtra("id_aluno", idAluno);
		TabSpec tabSpecAnamnese = tabHost
				.newTabSpec("Anamnese")
				.setIndicator("Anamese", getResources().getDrawable(R.drawable.ic_launcher))
				.setContent(intentAnamnese);
		
		Intent intentAvaliacaoFisica = new Intent(getApplicationContext(), CadastrarDadosActivity.class);
		intentAvaliacaoFisica.putExtra("id_aluno", idAluno);
		TabSpec tabSpecAvaliacaoFisica = tabHost
				.newTabSpec("Avaliação Física")
				.setIndicator("Avaliação", getResources().getDrawable(R.drawable.ic_launcher))
				.setContent(intentAvaliacaoFisica);
		
		Intent intentVisualizarEstatisticas = new Intent(getApplicationContext(), VisualizarEstatisticasActivity.class);
		intentVisualizarEstatisticas.putExtra("id_aluno", idAluno);
		TabSpec tabSpecVisualizarEstatisticas = tabHost
				.newTabSpec("Estatísticas")
				.setIndicator("Estatísticas", getResources().getDrawable(R.drawable.ic_launcher))
				.setContent(intentVisualizarEstatisticas);
		
		tabHost.addTab(tabSpecAnamnese);
		tabHost.addTab(tabSpecAvaliacaoFisica);
		tabHost.addTab(tabSpecVisualizarEstatisticas);
		tabHost.setCurrentTab(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_avaliacao_tabs, menu);
		return true;
	}

}
