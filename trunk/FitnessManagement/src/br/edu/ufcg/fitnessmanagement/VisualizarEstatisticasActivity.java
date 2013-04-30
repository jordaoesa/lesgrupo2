package br.edu.ufcg.fitnessmanagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.ufcg.aluno.Dados;
import br.edu.ufcg.fachada.DadosFachada;
import br.edu.ufcg.grafico.GraficoDeLinha;
import br.edu.ufcg.util.FitnessManagementSingleton;
import br.edu.ufcg.util.ImageAdapterVisualizarEstatisticas;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;

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
		/*final Button buttonPeso = (Button) findViewById(R.id.buttonEstatisticasPeso);
		final Button buttonAltura = (Button) findViewById(R.id.buttonEstatisticasAltura);
		final Button buttonBracoEC = (Button) findViewById(R.id.buttonEstatisticasBEContraido);
		final Button buttonBracoDC = (Button) findViewById(R.id.buttonEstatisticasBDContraido);
		final Button buttonCoxaE = (Button) findViewById(R.id.buttonEstatisticasCoxaE);
		final Button buttonCoxaD = (Button) findViewById(R.id.buttonEstatisticasCoxaD);
		final Button buttonPanturrilhaE = (Button) findViewById(R.id.buttonEstatisticasPanturrilhaE);
		final Button buttonPanturrilhaD = (Button) findViewById(R.id.buttonEstatisticasPanturrilhaD);*/
		GridView gridViewVisualizarEstatisticas = (GridView) findViewById(R.id.gridViewVisualizarEstatisticas);
		System.out.println(gridViewVisualizarEstatisticas);
		ImageAdapterVisualizarEstatisticas adapter = new ImageAdapterVisualizarEstatisticas(getApplicationContext());
		System.out.println(adapter);
		gridViewVisualizarEstatisticas.setAdapter(adapter);
		
		gridViewVisualizarEstatisticas.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				final List<Double> values = new ArrayList<Double>();
				final List<Date> dates = new ArrayList<Date>();
				
				switch (position) {
				case 0:
					if(dados != null){
						for(Dados d : dados){
							values.add(d.getPeso());
							dates.add(d.getData());
						}
						graficoDeLinhaHandler(values, dates, "Peso");
					}
					break;
				case 1:
					if(dados != null){
						for (Dados d : dados) {
							values.add(d.getAltura());
							dates.add(d.getData());
						}
						graficoDeLinhaHandler(values, dates, "Altura");
					}
					break;
				case 2:
					if(dados != null){
						for(Dados d : dados){
							values.add(d.getBracoEC());
							dates.add(d.getData());
						}
						graficoDeLinhaHandler(values, dates, "Braço Esquerdo Contraído");
					}
					break;
				case 3:
					if(dados != null){
						for(Dados d : dados){
							values.add(d.getBracoDC());
							dates.add(d.getData());
						}
						graficoDeLinhaHandler(values, dates, "Braço Direito Contraído");
					}
					break;
				case 4:
					if(dados != null){
						for(Dados d : dados){
							values.add(d.getCoxaE());
							dates.add(d.getData());
						}
						graficoDeLinhaHandler(values, dates, "Coxa Esquerda");
					}
					break;
				case 5:
					if(dados != null){
						for(Dados d : dados){
							values.add(d.getCoxaD());
							dates.add(d.getData());
						}
						graficoDeLinhaHandler(values, dates, "Coxa Direita");
					}
					break;
				case 6:
					if(dados != null){
						for(Dados d : dados){
							values.add(d.getPanturrilhaE());
							dates.add(d.getData());
						}
						graficoDeLinhaHandler(values, dates, "Panturrilha Esquerda");
					}
					break;
				case 7:
					if(dados != null){
						for(Dados d : dados){
							values.add(d.getPanturrilhaD());
							dates.add(d.getData());
						}
						graficoDeLinhaHandler(values, dates, "Panturrilha Direita");
					}
					break;
				default:
					break;
				}
				
			}
		});
		
		/*final List<Double> values = new ArrayList<Double>();
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
		}*/
		
	}

	private void graficoDeLinhaHandler(List<Double> values, List<Date> dates, String titulo) {
		GraficoDeLinha grafico = new GraficoDeLinha();
		Intent intent = grafico.getIntent(this, values, dates, titulo);
		startActivity(intent);
	}
	
	private final int VOLTAR = 1;
	private final int AJUDA = 2;
	
	private final int avaliacaoFisica=3;

	String ajuda = "Ajuda";
	String voltar = "Voltar";
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		boolean r = super.onCreateOptionsMenu(menu);
		super.onCreateOptionsMenu(menu);
		menu.add(0, VOLTAR, 0, voltar).setIcon(R.drawable.back);
		
		
		SubMenu menuAjuda = menu.addSubMenu(ajuda);
		menuAjuda.setIcon(R.drawable.help);
		menuAjuda.add(0, avaliacaoFisica, 0, "Estatisticas");
		return r;
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		switch(item.getItemId()){
		case VOLTAR:
			mensagemExibir("Voltar", "Voltando",R.drawable.back);
			finish();
			break;
		case AJUDA:
			mensagemExibir("Ajuda", "Avaliacao",R.drawable.help);
			break;
		case avaliacaoFisica:
			mensagemExibir("Avaliacao", "Podemos visualizar Anamnese, Dados estatisticos e fazer uma avaliacao do aluno.",R.drawable.help);
			break;
		}
		return super.onOptionsItemSelected(item);
		
	}
	
	public void mensagemExibir(String titulo, String texto, int icone){
		AlertDialog.Builder mensagem = new AlertDialog.Builder(this);
		mensagem.setTitle(titulo);
		mensagem.setIcon(icone);
		mensagem.setMessage(texto);
		mensagem.setNeutralButton("OK", null);
		mensagem.show();
	}

}
