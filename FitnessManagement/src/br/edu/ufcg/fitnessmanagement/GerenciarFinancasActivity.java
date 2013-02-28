package br.edu.ufcg.fitnessmanagement;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class GerenciarFinancasActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gerenciar_financas);
		setTitle("Gerencia de Financas");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_gerenciar_financas, menu);
		return true;
	}

}
