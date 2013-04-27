package br.edu.ufcg.fitnessmanagement;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
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
				.setIndicator("Anamnese", getResources().getDrawable(R.drawable.medical))
				.setContent(intentAnamnese);
		
		Intent intentAvaliacaoFisica = new Intent(getApplicationContext(), AvaliacaoFisicaActivity.class);
		intentAvaliacaoFisica.putExtra("id_aluno", idAluno);
		TabSpec tabSpecAvaliacaoFisica = tabHost
				.newTabSpec("Avaliação Física")
				.setIndicator("Avaliação Física", getResources().getDrawable(R.drawable.avaliacao))
				.setContent(intentAvaliacaoFisica);
		
		Intent intentVisualizarEstatisticas = new Intent(getApplicationContext(), VisualizarEstatisticasActivity.class);
		intentVisualizarEstatisticas.putExtra("id_aluno", idAluno);
		TabSpec tabSpecVisualizarEstatisticas = tabHost
				.newTabSpec("Estatísticas")
				.setIndicator("Estatísticas", getResources().getDrawable(R.drawable.estatistica))
				.setContent(intentVisualizarEstatisticas);
		
		tabHost.addTab(tabSpecAnamnese);
		tabHost.addTab(tabSpecAvaliacaoFisica);
		tabHost.addTab(tabSpecVisualizarEstatisticas);
		tabHost.setCurrentTab(0);
	}

		
}
