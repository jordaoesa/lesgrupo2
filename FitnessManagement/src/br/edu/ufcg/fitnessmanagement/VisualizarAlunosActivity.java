package br.edu.ufcg.fitnessmanagement;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import br.edu.ufcg.aluno.Aluno;
import br.edu.ufcg.fachada.AlunoFachada;
import br.edu.ufcg.util.FitnessManagementSingleton;
import br.edu.ufcg.util.ImageAdapterVisualizarAlunos;

public class VisualizarAlunosActivity extends Activity {

	private List<Aluno> alunos;
	private AlunoFachada alunoFachada;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.alunoFachada = FitnessManagementSingleton.getAlunoFachadaInstance();
		this.alunos = alunoFachada.getAlunos();
		showListaAlunosCadastrados();
	}
	
	private void showListaAlunosCadastrados() {
		setTitle("Alunos Cadastrados");
		setContentView(R.layout.activity_visualizar_alunos);
		GridView grade = (GridView) findViewById(R.id.gridViewActivityVisualizar);
		final ImageAdapterVisualizarAlunos adapter = new ImageAdapterVisualizarAlunos(this, alunos, getContentResolver());
		grade.setAdapter(adapter);
		grade.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				selecionaUsuario(position, alunos);
			}

		});
		
		grade.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View v, final int position, long id) {
				final Aluno aluno = alunos.get(position);
				new AlertDialog.Builder(VisualizarAlunosActivity.this)
				.setTitle("Remover o Aluno")
				.setMessage(aluno.getNome())
				.setPositiveButton("Sim", new AlertDialog.OnClickListener(){
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						alunoFachada.removeAluno(aluno.getId());
						adapter.removeItem(position);
						alunos = alunoFachada.getAlunos();
						adapter.notifyDataSetChanged();
						Toast.makeText(getApplicationContext(), "Aluno Removido", Toast.LENGTH_SHORT).show();
					}})
				.setNegativeButton("Não", null)
				.setIcon(R.drawable.alert)
				.show();
				return true;
			}
		});

		Button voltar = (Button) findViewById(R.id.buttonVoltarAcivityVisualizar);
		voltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		Button buttonHistoricoAgendamento = (Button) findViewById(R.id.buttonHistoricoAgendamentoAcivity);
		final Intent historicoAgendamentoActivity = new Intent(getApplicationContext(),HistoricoTabsActivity.class);
		buttonHistoricoAgendamento.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(historicoAgendamentoActivity);
			}
		});

	}

	private void selecionaUsuario(int position, List<Aluno> alunos) {
		finish();
		Intent activityFicha = new Intent(this, PerfilDoAlunoActivity.class);
		activityFicha.putExtra("id_aluno", alunos.get(position).getId());
		startActivity(activityFicha);
	}


	
	private final int SAIR = 1;
	private final int AJUDA = 2;
	
	private final int alunosCadastrados=4;
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
		menuAjuda.add(0, alunosCadastrados, 0, "Alunos Cadastrados");
		
		return r;
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		switch(item.getItemId()){
		
		
		case SAIR: mensagemExibir("Sair", "Saindo"); finish();  break;
		case AJUDA: mensagemExibir("Ajuda", "Menu Ajuda"); break;
		case alunosCadastrados: mensagemExibir("Alunos Cadastrados", "Aqui temos uma lista com todos os seus alunos cadastrados, selecione um aluno especifico e adicione mais detalhes a seu perfil." +
				"Ao clicar em Historico Agendamentos sera mostrada uma lista com atividades pendentes a serem resolvidas, ex: pagamento ou aula."); break;
		
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
