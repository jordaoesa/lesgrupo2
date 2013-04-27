package br.edu.ufcg.fitnessmanagement;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
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

		tabHost.clearAllTabs();
		Intent intentAcompanhamentoMeta = new Intent(getApplicationContext(), AcompanhamentoMetasActivity.class);
		intentAcompanhamentoMeta.putExtra("id_aluno", idAluno);
		TabSpec tabSpecAcompanhamento = tabHost
				.newTabSpec("Acompanhamento")
				.setIndicator("Acompanhamento", getResources().getDrawable(R.drawable.treinamento))
				.setContent(intentAcompanhamentoMeta);


		Intent intentVisualizarMetas = new Intent(getApplicationContext(), VisualizaGraficoMetasActivity.class);
		intentVisualizarMetas.putExtra("id_aluno", idAluno);
		TabSpec tabSpecVisualizarGrafico = tabHost
				.newTabSpec("Visualizar")
				.setIndicator("Visualizar", getResources().getDrawable(R.drawable.visualizar))
				.setContent(intentVisualizarMetas);

		tabHost.addTab(tabSpecAcompanhamento);
		tabHost.addTab(tabSpecVisualizarGrafico);
		tabHost.setCurrentTab(0);
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
////		getMenuInflater().inflate(R.menu.activity_avaliacao_tabs, menu);
//		return true;
//	}
	
	
	
	private final int VOLTAR = 1;
	private final int AJUDA = 2;
	
	private final int metas=3;

	String ajuda = "Ajuda";
	String voltar = "Voltar";
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_main, menu);
//		return true;

		
		boolean r = super.onCreateOptionsMenu(menu);
		super.onCreateOptionsMenu(menu);
		//menu.add(0, AJUDA, 0, ajuda).setIcon(R.drawable.alert);
		menu.add(0, VOLTAR, 0, voltar).setIcon(R.drawable.back);
		
		
		SubMenu menuAjuda = menu.addSubMenu(ajuda);
		menuAjuda.setIcon(R.drawable.help);
		menuAjuda.add(0, metas, 0, "Metas");
		return r;
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		switch(item.getItemId()){
		case VOLTAR:
			mensagemExibir("Voltar", "Voltando",R.drawable.back);
			finish();
			break;
		case AJUDA:
			mensagemExibir("Ajuda", "Metas",R.drawable.help);
			break;
		case metas:
			mensagemExibir("Metas", "Podemos criar uma nova Meta para o aluno informando seu peso atual e quanto ele deseja perder para entrar em forma.\n" ,R.drawable.help);
			break;
		}
		return super.onOptionsItemSelected(item);
		
	}
	
	public void mensagemExibir(String titulo, String texto, int icone){
		AlertDialog.Builder mensagem = new AlertDialog.Builder(this);
		mensagem.setTitle(titulo);
		mensagem.setIcon(icone);
		mensagem.setMessage(texto);
		mensagem.setNeutralButton("OK", null);
		mensagem.show();
	}
	

}
