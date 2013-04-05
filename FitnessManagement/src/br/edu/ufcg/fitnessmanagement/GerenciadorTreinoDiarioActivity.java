package br.edu.ufcg.fitnessmanagement;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
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
	private List<Atividade> treinoDoDiaDoBD;
    private ListView listViewAtividadesDiarias;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gerenciador_treino_diario);
		fachadaAtividadeDiaria = new AtividadeDiariaFachada(FitnessManagementSingleton.getDBInstance());
		menuCriarAtividadeDia();
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
		//prencherListView();
		//final List<Atividade> treinoDoDiaDoBD = fachadaAtividadeDiaria.getAtividadesDiaria(getIntent().getIntExtra("id_aluno", -1),getIntent().getStringExtra("diaSemana"));
		listViewAtividadesDiarias.setOnItemClickListener(new OnItemClickListener() {
		      public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
		    	  AlertDialog.Builder adb=new AlertDialog.Builder(GerenciadorTreinoDiarioActivity.this);
		          adb.setTitle("Delete?");
		          adb.setMessage(treinoDoDiaDoBD.get(myItemInt).getAtividadeFull());
		          final int positionToRemove = myItemInt;
		          adb.setNegativeButton("Cancelar", null);
		          adb.setPositiveButton("Deletar", new AlertDialog.OnClickListener() {
		              public void onClick(DialogInterface dialog, int which) {
		            	  arrayAdapterAtividades.remove(treinoDoDiaDoBD.get(positionToRemove).getAtividadeResume());
		            	  fachadaAtividadeDiaria.deleteAtividadeDiaria(treinoDoDiaDoBD.get(positionToRemove));
		            	  arrayAdapterAtividades.notifyDataSetChanged();
		            	  treinoDoDiaDoBD = fachadaAtividadeDiaria.getAtividadesDiaria(getIntent().getIntExtra("id_aluno", -1),getIntent().getStringExtra("diaSemana"));
		              }});
		          adb.show(); 
		        System.out.println(treinoDoDiaDoBD.get(myItemInt).getAtividadeFull());
		      }                 
		});
		prencherListView();
	}
	
	private void prencherListView(){
		listaAtividades = new ArrayList<String>();
		listViewAtividadesDiarias = (ListView)findViewById(R.id.listViewAtividadesDiaria);
		treinoDoDiaDoBD = fachadaAtividadeDiaria.getAtividadesDiaria(getIntent().getIntExtra("id_aluno", -1),getIntent().getStringExtra("diaSemana"));
		for (Atividade a: treinoDoDiaDoBD) {
			System.out.println(a.getAtividadeResume());
			listaAtividades.add(a.getAtividadeResume());
		}
		
		arrayAdapterAtividades = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listaAtividades);
		listViewAtividadesDiarias.setAdapter(arrayAdapterAtividades);
		
	}
	
	
	

	
	private final int SAIR = 1;
	private final int AJUDA = 2;
	
	private final int gerenciarTreinoDiario=3;
	String ajuda = "Ajuda";
	String sair = "Sair";
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_main, menu);
//		return true;

		
		boolean r = super.onCreateOptionsMenu(menu);
		super.onCreateOptionsMenu(menu);
		//menu.add(0, AJUDA, 0, ajuda).setIcon(R.drawable.alert);
		menu.add(0, SAIR, 0, sair).setIcon(R.drawable.alert);
		
		
		SubMenu menuAjuda = menu.addSubMenu(ajuda);
		menuAjuda.add(0, gerenciarTreinoDiario, 0, "Gerenciar Treino Diario");
		
		return r;
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		switch(item.getItemId()){
		
		
		case SAIR: mensagemExibir("Sair", "Saindo"); finish();  break;
		case AJUDA: mensagemExibir("Ajuda", "Gerenciar Treino Diario"); break;
		case gerenciarTreinoDiario: mensagemExibir("Gerenciar Treino Diario", "Informe um nome para a Atividade, o numero de serie e repeticoes e o tempo de execucao da atividade, caso nao tenha tempo preencha os campos com 0.\n" +
				"Adicione observacoes sobre a atividade e clique em cadastrar atividade.\n" +
				"As atividades ja cadastradas sao visualizadas logo abaixo e podem ser deletadas, basta pressionar e escolher a opcao deletar."); break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public void mensagemExibir(String titulo, String texto){
		AlertDialog.Builder mensagem = new AlertDialog.Builder(this);
		mensagem.setTitle(titulo);
		mensagem.setMessage(texto);
		mensagem.setNeutralButton("OK", null);
		mensagem.show();
	}


}
