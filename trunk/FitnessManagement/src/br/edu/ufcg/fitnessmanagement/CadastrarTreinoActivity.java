package br.edu.ufcg.fitnessmanagement;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CadastrarTreinoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_treino);
		
		menuCadastrarAtividade();
	}
	
	private void menuCadastrarAtividade() {
		
		Button botaoCancelarAtividade = (Button) findViewById(R.id.botaoCancelarCadastroTreinoCadastrarTreino);
		botaoCancelarAtividade.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_cadastrar_treino, menu);
		return true;
	}

}
