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
		final Button buttonCalorias = (Button) findViewById(R.id.buttonEstatisticasCalorias);
		final Button buttonBraco = (Button) findViewById(R.id.buttonEstatisticasBraco);
		final Button buttonPerna = (Button) findViewById(R.id.buttonEstatisticasPerna);
		final Button buttonIMC = (Button) findViewById(R.id.buttonEstatisticasIMC);
		
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
			buttonCalorias.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					for(Dados d : dados){
						values.add(d.getCalorias());
						dates.add(d.getData());
					}
					graficoDeLinhaHandler(values, dates, "Calorias");
				}
			});
			buttonBraco.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					for(Dados d : dados){
						values.add(d.getTamanhoBraco());
						dates.add(d.getData());
					}
					graficoDeLinhaHandler(values, dates, "Tamanho Braço");
				}
			});
			buttonPerna.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					for(Dados d : dados){
						values.add(d.getTamanhoPerna());
						dates.add(d.getData());
					}
					graficoDeLinhaHandler(values, dates, "Tamanho Perna");
				}
			});
			buttonIMC.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					for(Dados d : dados){
						values.add(d.getImc());
						dates.add(d.getData());
					}
					graficoDeLinhaHandler(values, dates, "IMC");
				}
			});
		}
		
		
		Button voltar = (Button) findViewById(R.id.buttonEstatisticasVoltar);
		voltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		
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
