package br.edu.ufcg.fitnessmanagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.ufcg.aluno.Dados;
import br.edu.ufcg.fachada.DadosFachada;
import br.edu.ufcg.grafico.GraficoDeLinha;
import br.edu.ufcg.util.FitnessManagementSingleton;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class VisualizarEstatisticasActivity extends Activity {

	private DadosFachada dadosFachada;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visualizar_estatisticas);
		setTitle("Estatísticas");
		
		dadosFachada = FitnessManagementSingleton.getDadosFachadaInstance();
		mostrarEstatisticas();
	}
	
	private void mostrarEstatisticas() {
		
		final List<Dados> dados = dadosFachada.getDadosDoAluno(getIntent().getIntExtra("id_aluno", -1));
		final Button buttonPeso = (Button) findViewById(R.id.buttonEstatisticasPeso);
		final Button buttonAltura = (Button) findViewById(R.id.buttonEstatisticasAltura);
		final Button buttonBracoEC = (Button) findViewById(R.id.buttonEstatisticasBEContraido);
		final Button buttonBracoDC = (Button) findViewById(R.id.buttonEstatisticasBDContraido);
		final Button buttonCoxaE = (Button) findViewById(R.id.buttonEstatisticasCoxaE);
		final Button buttonCoxaD = (Button) findViewById(R.id.buttonEstatisticasCoxaD);
		final Button buttonPanturrilhaE = (Button) findViewById(R.id.buttonEstatisticasPanturrilhaE);
		final Button buttonPanturrilhaD = (Button) findViewById(R.id.buttonEstatisticasPanturrilhaD);
		
		final List<Double> values = new ArrayList<Double>();
		final List<Date> dates = new ArrayList<Date>();
		if(dados != null){
			
			buttonPeso.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					for(Dados d : dados){
						values.add(d.getPeso());
						dates.add(d.getData());
					}
					graficoDeLinhaHandler(values, dates, "Peso");
				}
			});
			
			buttonAltura.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					for (Dados d : dados) {
						values.add(d.getAltura());
						dates.add(d.getData());
					}
					graficoDeLinhaHandler(values, dates, "Altura");
				}
			});
			
			buttonBracoEC.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					for(Dados d : dados){
						values.add(d.getBracoEC());
						dates.add(d.getData());
					}
					graficoDeLinhaHandler(values, dates, "Braço Esquerdo Contraído");
				}
			});
			
			buttonBracoDC.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					for(Dados d : dados){
						values.add(d.getBracoDC());
						dates.add(d.getData());
					}
					graficoDeLinhaHandler(values, dates, "Braço Direito Contraído");
				}
			});
			
			buttonCoxaE.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					for(Dados d : dados){
						values.add(d.getCoxaE());
						dates.add(d.getData());
					}
					graficoDeLinhaHandler(values, dates, "Coxa Esquerda");
				}
			});
			
			buttonCoxaD.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					for(Dados d : dados){
						values.add(d.getCoxaD());
						dates.add(d.getData());
					}
					graficoDeLinhaHandler(values, dates, "Coxa Direita");
				}
			});
			
			buttonPanturrilhaE.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					for(Dados d : dados){
						values.add(d.getPanturrilhaE());
						dates.add(d.getData());
					}
					graficoDeLinhaHandler(values, dates, "Panturrilha Esquerda");
				}
			});
			
			buttonPanturrilhaD.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					for(Dados d : dados){
						values.add(d.getPanturrilhaD());
						dates.add(d.getData());
					}
					graficoDeLinhaHandler(values, dates, "Panturrilha Direita");
				}
			});
		}
		
	}

	private void graficoDeLinhaHandler(List<Double> values, List<Date> dates, String titulo) {
		GraficoDeLinha grafico = new GraficoDeLinha();
		Intent intent = grafico.getIntent(this, values, dates, titulo);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater()
				.inflate(R.menu.activity_visualizar_estatisticas, menu);
		return true;
	}

}
