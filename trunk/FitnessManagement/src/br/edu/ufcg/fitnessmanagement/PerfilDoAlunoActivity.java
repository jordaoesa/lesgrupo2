package br.edu.ufcg.fitnessmanagement;


import br.edu.ufcg.aluno.Aluno;
import br.edu.ufcg.fachada.AlunoFachada;
import br.edu.ufcg.fachada.DadosFachada;
import br.edu.ufcg.util.FitnessManagementSingleton;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class PerfilDoAlunoActivity extends Activity {

	private AlunoFachada alunoFachada;
	private Aluno aluno;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		alunoFachada = FitnessManagementSingleton.getAlunoFachadaInstance();
		getAluno();
		menuDeOpcoes();
		
	}

	private void getAluno() {
		int idAluno = getIntent().getIntExtra("id_aluno", -1);
		aluno = alunoFachada.getAlunoFromId(idAluno);
	}

	private void menuDeOpcoes() {
		setContentView(R.layout.activity_perfil_do_aluno);
		setTitle("Ficha do Aluno");
		
		TextView tvNome = (TextView) findViewById(R.id.textViewNomePerfilAluno);
		TextView tvTelefone = (TextView) findViewById(R.id.textViewTelefonePerfilAluno);
		TextView tvIdade = (TextView) findViewById(R.id.textViewIdadePerfilAluno);
		TextView tvSexo = (TextView) findViewById(R.id.textViewSexoPerfilAluno);
		
		tvNome.setText(aluno.getNome());
		tvTelefone.setText(aluno.getTelefone().toString());
		tvIdade.setText(aluno.getIdade().toString());
		tvSexo.setText(aluno.getSexo());
		
		Button bCadastrarDados = (Button) findViewById(R.id.buttonCadastrarDadosPerfilAluno);
		Button bCadastrarExercicio = (Button) findViewById(R.id.buttonCadastrarExercicioPerfilAluno);
		Button bCadastrarAtividade = (Button)findViewById(R.id.buttonCadastrarAtividadePerfilAluno);
		Button bCadastrarFicha = (Button)findViewById(R.id.buttonCriarFichaPerfilAluno);
		Button bVisualizarEstatisticas = (Button)findViewById(R.id.buttonVisualizarEstatisticasPerfilAluno);
		Button bGerenciarFinancas = (Button) findViewById(R.id.buttonGerenciarFinancasPerfilAluno);
		Button bAgendarAcompanhamento = (Button)findViewById(R.id.buttonAgendarAcompanhamentoPerfilAluno);//TODO
		
		bCadastrarDados.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentDados = new Intent(getApplicationContext(), CadastrarDadosActivity.class);
				intentDados.putExtra("id_aluno", aluno.getId());
				startActivity(intentDados);
			}
		});
		bCadastrarExercicio.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intentExercicio = new Intent(getApplicationContext(), CadastrarExercicioActivity.class);
				startActivity(intentExercicio);
			}
		});
		bCadastrarAtividade.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentAtividade = new Intent(getApplicationContext(), CadastrarAtividadeActivity.class);
				startActivity(intentAtividade);
			}
		});
		bCadastrarFicha.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentFicha = new Intent(getApplicationContext(), CriarFichaActivity.class);
				startActivity(intentFicha);
			}
		});
		bVisualizarEstatisticas.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentEstatisticas = new Intent(getApplicationContext(), VisualizarEstatisticasActivity.class);
				intentEstatisticas.putExtra("id_aluno", aluno.getId());
				startActivity(intentEstatisticas);
			}
		});
		bGerenciarFinancas.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intentFinancas = new Intent(getApplicationContext(), GerenciarFinancasActivity.class);
				intentFinancas.putExtra("id_aluno", aluno.getId());
				startActivity(intentFinancas);
			}
		});
		
		Button voltar = (Button) findViewById(R.id.buttonVoltarPerfilAluno);
		voltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_ficha_do_aluno, menu);
		return true;
	}

}
