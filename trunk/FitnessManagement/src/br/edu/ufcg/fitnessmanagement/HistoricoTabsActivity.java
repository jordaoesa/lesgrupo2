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

public class HistoricoTabsActivity extends TabActivity {

	private Integer idAluno;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_historico_tabs);
		setTitle("Avaliação");
		
		idAluno = getIntent().getIntExtra("id_aluno", -1);
		
		Button bVoltar = (Button) findViewById(R.id.buttonVoltarHistoricoTabs);
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
		
		Intent intentTreino = new Intent(getApplicationContext(), HistoricoAgendamentoActivity.class);
		intentTreino.putExtra("tipo", "Treino");
		TabSpec tabSpecTreino = tabHost
				.newTabSpec("Treinos Agendados")
				.setIndicator("Treinos Agendados", getResources().getDrawable(R.drawable.treinamento))
				.setContent(intentTreino);
		
		Intent intentPagamentos = new Intent(getApplicationContext(), HistoricoAgendamentoActivity.class);
		intentPagamentos.putExtra("tipo", "Pagamento");
		TabSpec tabSpecPagamentos = tabHost
				.newTabSpec("Pagamentos Pendentes")
				.setIndicator("Pagamentos Pendentes", getResources().getDrawable(R.drawable.cash))
				.setContent(intentPagamentos);
		
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
