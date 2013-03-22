package br.edu.ufcg.fitnessmanagement;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import br.edu.ufcg.fachada.AtividadeDiariaFachada;
import br.edu.ufcg.gerenciar.Atividade;
import br.edu.ufcg.util.FitnessManagementSingleton;

public class GerenciadorTreinoDiarioActivity extends Activity {

	private AtividadeDiariaFachada fachadaAtividadeDiaria ;
	private ArrayList<String>listaAtividades;
	private ArrayAdapter<String> arrayAdapterAtividades;

    private ListView listViewAtividadesDiarias;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gerenciador_treino_diario);
		fachadaAtividadeDiaria = new AtividadeDiariaFachada(FitnessManagementSingleton.getDBInstance());
		menuCriarAtividadeDia();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_gerenciador_treino_diario,
				menu);
		return true;
	}
	
	private void menuCriarAtividadeDia(){
		prencherListView();
		final EditText nomeAtividade = (EditText)findViewById(R.id.editTextNomeDaAtividade);
		final EditText numeroSeries = (EditText)findViewById(R.id.editTextNumeroSeries);
		final EditText numeroRepeticoes = (EditText)findViewById(R.id.editTextNumeroRepeticoes);
		final EditText horaExecucao = (EditText)findViewById(R.id.editTextHoraTempoExecucao);
		final EditText minExecucao = (EditText)findViewById(R.id.editTextMinutoTempoExecucao);
		final EditText segExecucao = (EditText)findViewById(R.id.editTextSegundoTempoExecucao);
		final EditText observacaoAtividade = (EditText)findViewById(R.id.editTextObservacaoAtividade);
		final Button botaoCadastrarAtividadeDiaria = (Button)findViewById(R.id.buttonCadastrarAtividadeDiaria);
		botaoCadastrarAtividadeDiaria.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try{
					Atividade atividade = new Atividade(nomeAtividade.getText().toString(), numeroSeries.getText().toString(), 
							numeroRepeticoes.getText().toString(),horaExecucao.getText().toString(), 
							minExecucao.getText().toString(), segExecucao.getText().toString(), 
							observacaoAtividade.getText().toString(),getIntent().getStringExtra("diaSemana"),
							getIntent().getIntExtra("id_aluno", -1));
					Toast.makeText(getApplicationContext(),"Atividade Cadastrada", Toast.LENGTH_SHORT).show();
					fachadaAtividadeDiaria.adicionarAtividadeDiaria(atividade);
					prencherListView();
							
				}catch(Exception e){
					Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
		final List<Atividade> auxx = fachadaAtividadeDiaria.getAtividadesDiaria(getIntent().getIntExtra("id_aluno", -1),getIntent().getStringExtra("diaSemana"));
		listViewAtividadesDiarias.setOnItemClickListener(new OnItemClickListener() {
		      public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
		        String selectedFromList =(String) (listViewAtividadesDiarias.getItemAtPosition(myItemInt));
		        System.out.println(auxx.get(myItemInt).getAtividadeFull());
		      }                 
		});
	}
	
	private void prencherListView(){
		listaAtividades = new ArrayList<String>();
		listViewAtividadesDiarias = (ListView)findViewById(R.id.listViewAtividadesDiaria);
		List<Atividade> aux = fachadaAtividadeDiaria.getAtividadesDiaria(getIntent().getIntExtra("id_aluno", -1),getIntent().getStringExtra("diaSemana"));
		for (Atividade a: aux) {
			System.out.println(a.getAtividadeResume());
			listaAtividades.add(a.getAtividadeResume());
		}
		
		arrayAdapterAtividades = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listaAtividades);
		listViewAtividadesDiarias.setAdapter(arrayAdapterAtividades);
		
	}
	
	

}
