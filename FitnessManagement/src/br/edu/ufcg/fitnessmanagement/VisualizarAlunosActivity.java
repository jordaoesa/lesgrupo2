package br.edu.ufcg.fitnessmanagement;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import br.edu.ufcg.aluno.Aluno;
import br.edu.ufcg.util.FitnessManagementSingleton;
import br.edu.ufcg.util.ImageAdapter;

public class VisualizarAlunosActivity extends Activity {

	private List<Aluno> alunos;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.alunos = FitnessManagementSingleton.getAlunoFachadaInstance().getAlunos();
		showListaAlunosCadastrados();
	}
	
	private void showListaAlunosCadastrados() {
		setTitle("Alunos Cadastrados");
		setContentView(R.layout.activity_visualizar_alunos);
		GridView grade = (GridView) findViewById(R.id.gridViewActivityVisualizar);
		grade.setAdapter(new ImageAdapter(this, alunos, getContentResolver()));
		grade.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				selecionaUsuario(position, alunos);
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
