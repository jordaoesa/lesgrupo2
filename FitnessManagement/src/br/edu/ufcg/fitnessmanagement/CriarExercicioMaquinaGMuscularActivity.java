package br.edu.ufcg.fitnessmanagement;

import br.edu.ufcg.fachada.ExercicioFachada;
import br.edu.ufcg.fachada.GrupoMuscularFachada;
import br.edu.ufcg.fachada.MaquinaFachada;
import br.edu.ufcg.util.FitnessManagementSingleton;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CriarExercicioMaquinaGMuscularActivity extends Activity {
	
	private ExercicioFachada exercicioFachada;
	private MaquinaFachada maquinaFachada;
	private GrupoMuscularFachada grupoMuscularFachada;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_criar_exercicio_maquina_gmuscular_bd);
		
		
		exercicioFachada = FitnessManagementSingleton.getExercicioFachadaInstance();
		maquinaFachada = new FitnessManagementSingleton().getMaquinaFachadaInstance();
		grupoMuscularFachada = new FitnessManagementSingleton().getGrupoMuscularFachadaInstance();
//		
		
		menuCriarExercicioMaquinaGMuscular();
	}
	
	
	
	
	private void menuCriarExercicioMaquinaGMuscular() {
		
		Button botaoCriarExercicio = (Button) findViewById(R.id.botaoCriarExercicio);
		Button botaoCriarMaquina = (Button)findViewById(R.id.botaoCriarMaquina);
		Button botaoCriarGMuscular =(Button)findViewById(R.id.botaoCriarGrupoMuscular);
//		
		final TextView textViewExercicio = (TextView) findViewById(R.id.nomeExercicio);
		final TextView textViewMaquina = (TextView) findViewById(R.id.nomeMaquina);
		final TextView textViewGMuscular = (TextView) findViewById(R.id.nomeGrupoMuscular);
//		
		
		botaoCriarExercicio.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String nomeExercicio = textViewExercicio.getText().toString();
				
				exercicioFachada.adicionaExercicio(nomeExercicio);
				
			}
		});
		
		//adicionar maquina
		botaoCriarMaquina.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String nomeMaquina = textViewMaquina.getText().toString();
				maquinaFachada.adicionaMaquina(nomeMaquina);
			}
		});
		
		//criar grupo muscular
		botaoCriarGMuscular.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String nomeGrupoMuscular = textViewGMuscular.getText().toString();
				grupoMuscularFachada.adicionaGrupoMuscular(nomeGrupoMuscular);
			}
		});
		
		
		
	}
	
	
	
	
	
	
	

	
	
}
