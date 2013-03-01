package br.edu.ufcg.fitnessmanagement;


import br.edu.ufcg.util.FitnessManagementSingleton;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class CadastrarTreinoActivity extends Activity {

	private Integer idAluno;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_treino);
		
		idAluno = getIntent().getIntExtra("id_aluno", -1);
		menuCadastrarAtividade();
	}
	
	private void menuCadastrarAtividade() {
		
		preencheSpinnerAtividades();
		
		Button botaoCancelarAtividade = (Button) findViewById(R.id.botaoCancelarCadastroTreinoCadastrarTreino);
		botaoCancelarAtividade.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}
	
	private void preencheSpinnerAtividades() {
		Spinner spinnerAtividades = (Spinner) findViewById(R.id.spinnerDeAtividadesCadastrarTreino);
		Object[] a =  FitnessManagementSingleton.getAtividadeFachadaInstance().getNomesDasAtividades(idAluno).toArray();
		ArrayAdapter<Object> arrayAdapter = new ArrayAdapter<Object>(this, android.R.layout.simple_spinner_item, a );
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerAtividades.setAdapter(arrayAdapter);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_cadastrar_treino, menu);
		return true;
	}

}
