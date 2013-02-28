package br.edu.ufcg.fitnessmanagement;

import java.util.List;

import br.edu.ufcg.aluno.Aluno;
import br.edu.ufcg.fachada.AlunoFachada;
import br.edu.ufcg.util.FitnessManagementSingleton;
import br.edu.ufcg.util.ImageAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class VisualizarAlunosActivity extends Activity {
	
	private AlunoFachada alunoFachada;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		alunoFachada = FitnessManagementSingleton.getAlunoFachadaInstance();
		
		setContentView(R.layout.activity_visualizar_alunos);
		setTitle("Alunos Cadastrados");
		
		carregaAlunos();
		
	}

	private void carregaAlunos() {
		
		final List<Aluno> alunos = alunoFachada.getAlunos();
		GridView grade = (GridView) findViewById(R.id.gridViewActivityVisualizar);
		grade.setAdapter(new ImageAdapter(this, alunos));
		
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
	}
	
	private void selecionaUsuario(int position, List<Aluno> alunos) {
		//Toast.makeText(getApplicationContext(), alunos.get(position).getNome(), Toast.LENGTH_SHORT).show();
		Intent activityFicha = new Intent(this, PerfilDoAlunoActivity.class);
		activityFicha.putExtra("id_aluno", alunos.get(position).getId());
		startActivity(activityFicha);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.visualizar_alunos, menu);
		return true;
	}

}
