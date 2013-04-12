package br.edu.ufcg.fitnessmanagement;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import br.edu.ufcg.fitnessmanagement.R;

public class AcompanhamentoMetasTabsActivity extends TabActivity {

	private Integer idAluno;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acompanhamento_metas_tabs);
		setTitle("Meta Perda de Peso");
		
		idAluno = getIntent().getIntExtra("id_aluno", -1);
		
		initTabs();
	}

	private void initTabs() {
		TabHost tabHost = getTabHost();
		
		Intent intentAcompanhamentoMeta = new Intent(getApplicationContext(), AcompanhamentoMetasActivity.class);
		intentAcompanhamentoMeta.putExtra("id_aluno", idAluno);
		TabSpec tabSpecTreino = tabHost
				.newTabSpec("Acompanhamento")
				.setIndicator("Acompanhamento", getResources().getDrawable(R.drawable.treinamento))
				.setContent(intentAcompanhamentoMeta);
		
		
		Intent intentVisualizarMetas = new Intent(getApplicationContext(), VisualizaGraficoMetasActivity.class);
		intentVisualizarMetas.putExtra("id_aluno", idAluno);
		TabSpec tabSpecPagamentos = tabHost
				.newTabSpec("Visualizar")
				.setIndicator("Visualizar", getResources().getDrawable(R.drawable.visualizar))
				.setContent(intentVisualizarMetas);
		
		tabHost.addTab(tabSpecTreino);
		tabHost.addTab(tabSpecPagamentos);
		tabHost.setCurrentTab(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_avaliacao_tabs, menu);
		return true;
	}

}
