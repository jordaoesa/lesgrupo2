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
				.setIndicator("Avaliação", getResources().getDrawable(R.drawable.avaliacao))
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


//	
//	private final int SAIR = 1;
//	private final int AJUDA = 2;
//	
//	private final int avaliacao=3;
//	String ajuda = "Ajuda";
//	String sair = "Sair";
//	
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
////		// Inflate the menu; this adds items to the action bar if it is present.
////		getMenuInflater().inflate(R.menu.activity_main, menu);
////		return true;
//
//		
//		boolean r = super.onCreateOptionsMenu(menu);
//		super.onCreateOptionsMenu(menu);
//		//menu.add(0, AJUDA, 0, ajuda).setIcon(R.drawable.alert);
//		menu.add(0, SAIR, 0, sair).setIcon(R.drawable.alert);
//		
//		
//		SubMenu menuAjuda = menu.addSubMenu(ajuda);
//		menuAjuda.add(0, avaliacao, 0, "Avaliacao");
//		
//		return r;
//		
//	}
//	
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item){
//		
//		switch(item.getItemId()){
//		
//		
//		case SAIR: mensagemExibir("Sair", "Saindo"); finish();  break;
//		case AJUDA: mensagemExibir("Ajuda", "Avaliacao"); break;
//		case avaliacao: mensagemExibir("Avaliacao", "Podemos visualizar Anamnese, Dados estatisticos e fazer uma avaliacao do aluno."); break;
//		}
//		
//		return super.onOptionsItemSelected(item);
//	}
//	
//	public void mensagemExibir(String titulo, String texto){
//		AlertDialog.Builder mensagem = new AlertDialog.Builder(this);
//		mensagem.setTitle(titulo);
//		mensagem.setMessage(texto);
//		mensagem.setNeutralButton("OK", null);
//		mensagem.show();
//	}


}
