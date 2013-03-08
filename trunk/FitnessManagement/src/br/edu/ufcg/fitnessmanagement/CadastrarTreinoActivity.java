package br.edu.ufcg.fitnessmanagement;


import java.util.List;

import br.edu.ufcg.gerenciar.Atividade;
import br.edu.ufcg.util.FitnessManagementSingleton;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class CadastrarTreinoActivity extends Activity {

	private Integer idAluno;
	private String nomeAtividade;
	
	
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
		Button botaoAdicionarAtividade = (Button) findViewById(R.id.botaoAdicionarAtividade);
		Button botaoCadastrarTreino = (Button) findViewById(R.id.botaoCadastrarTreino);
		
		
		//fazer o textView Dinamico
		botaoAdicionarAtividade.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!(nomeAtividade == null || nomeAtividade.equals(""))){
					LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayoutDoScroll);

					TextView text = new TextView(CadastrarTreinoActivity.this);
                    text.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
                    text.setText(nomeAtividade);
                    text.setTextSize(150, 80);
                    layout.addView(text);
				}	
			}
		});
		
		//o aluno ja tem varias atividades.. entao to criando o treino que eh algumas atividades
		
		botaoCadastrarTreino.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			List<Atividade> atividades = FitnessManagementSingleton.getAtividadeFachadaInstance().getAtividades(idAluno);
				for(int i=0;i<atividades.size();i++){
					
					if(atividades.get(i).getNomeExercicio().equals(nomeAtividade)){
					int idAtividade = atividades.get(i).getId();
					FitnessManagementSingleton.getTreinoFachadaInstance().adicionaFicha(idAtividade);
					Toast.makeText(getApplicationContext(), "Treino cadastradoCadastrado", Toast.LENGTH_SHORT).show();
					finish();
					//agora eu tenho TabelaTreino preenchida com (idIncremente,idAtividade) atividade de mesmo nome.
					
					}
					
				}
				
			}
		});
		
		
		
		
		
		
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
		
		capturarNomeAtividade(spinnerAtividades);
		
		
	}
	
	
	private void capturarNomeAtividade(Spinner spinner){
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {	
			@Override
			public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
				//pega nome pela posi��o
				 nomeAtividade = parent.getItemAtPosition(posicao).toString();

			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}
	
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_cadastrar_treino, menu);
		return true;
	}

}
