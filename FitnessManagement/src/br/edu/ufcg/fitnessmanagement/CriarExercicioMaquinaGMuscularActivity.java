package br.edu.ufcg.fitnessmanagement;

import br.edu.ufcg.fachada.ExercicioFachada;
import br.edu.ufcg.fachada.GrupoMuscularFachada;
import br.edu.ufcg.fachada.MaquinaFachada;
import br.edu.ufcg.util.FitnessManagementSingleton;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class CriarExercicioMaquinaGMuscularActivity extends Activity {
	
	private ExercicioFachada exercicioFachada;
	private MaquinaFachada maquinaFachada;
	private GrupoMuscularFachada grupoMuscularFachada;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_criar_exercicio_maquina_gmuscular_bd);
		setTitle("Cadastrar Exercícios");
		
		exercicioFachada = FitnessManagementSingleton.getExercicioFachadaInstance();
		maquinaFachada =  FitnessManagementSingleton.getMaquinaFachadaInstance();
		grupoMuscularFachada =  FitnessManagementSingleton.getGrupoMuscularFachadaInstance();
		
		menuCriarExercicioMaquinaGMuscular();
	}

	private void menuCriarExercicioMaquinaGMuscular() {
		
		Button botaoCadastrar = (Button) findViewById(R.id.buttonCadastrarExercicioMaquinaMusculo);
		Button botaoVoltar = (Button) findViewById(R.id.buttonVoltarCriarExercicioMaquinaMusculo);
		
		final TextView textViewExercicio = (TextView) findViewById(R.id.nomeExercicio);
		final TextView textViewMaquina = (TextView) findViewById(R.id.nomeMaquina);
		final TextView textViewGMuscular = (TextView) findViewById(R.id.nomeGrupoMuscular);

		botaoCadastrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Editable nomeExercicio = (Editable) textViewExercicio.getText();
				Editable nomeMaquina = (Editable) textViewMaquina.getText();
				Editable nomeGrupoMuscular = (Editable) textViewGMuscular.getText();
				
				if(nomeExercicio == null || nomeExercicio.toString().equals("")){
					Toast.makeText(getApplicationContext(), "Exercício Inválido", Toast.LENGTH_SHORT).show();
				}else if(nomeMaquina == null || nomeMaquina.toString().equals("")){
					Toast.makeText(getApplicationContext(), "Máquina Inválida", Toast.LENGTH_SHORT).show();
				}else if(nomeGrupoMuscular == null || nomeGrupoMuscular.toString().equals("")){
					Toast.makeText(getApplicationContext(), "Grupo Muscular Inválido", Toast.LENGTH_SHORT).show();
				}else{
					exercicioFachada.adicionaExercicio(nomeExercicio.toString());
					maquinaFachada.adicionaMaquina(nomeMaquina.toString());
					grupoMuscularFachada.adicionaGrupoMuscular(nomeGrupoMuscular.toString());
					
					textViewExercicio.setText("");
					textViewMaquina.setText("");
					textViewGMuscular.setText("");
					
					Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
		botaoVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
	}
	
}
