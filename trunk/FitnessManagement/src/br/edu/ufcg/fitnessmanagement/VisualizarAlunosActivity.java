package br.edu.ufcg.fitnessmanagement;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
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
import br.edu.ufcg.util.ImageAdapter;

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
		final ImageAdapter adapter = new ImageAdapter(this, alunos, getContentResolver());
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
		final Intent historicoAgendamentoActivity = new Intent(getApplicationContext(),HistoricoAgendamentoActivity.class);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.visualizar_alunos, menu);
		return true;
	}

}
