package br.edu.ufcg.fitnessmanagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.ufcg.aluno.Aluno;
import br.edu.ufcg.aluno.Dados;
import br.edu.ufcg.fachada.AlunoFachada;
import br.edu.ufcg.fachada.DadosFachada;
import br.edu.ufcg.grafico.GraficoDeLinha;
import br.edu.ufcg.sgbd.DB;
import br.edu.ufcg.util.FitnessManagementSingleton;
import br.edu.ufcg.util.ImageAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class VisualizarAlunosActivity extends Activity {
	
	private DB db;
	private AlunoFachada alunoFachada;
	private DadosFachada dadosFachada;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		db = FitnessManagementSingleton.getDBInstance();
		alunoFachada = FitnessManagementSingleton.getAlunoFachadaInstance();
		dadosFachada = FitnessManagementSingleton.getDadosFachadaInstance();
		
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
		Toast.makeText(getApplicationContext(), "selecionado: " + alunos.get(position).getNome(), Toast.LENGTH_SHORT).show();
		//TODO nova activity com funcoes para modificar aluno
		mostrarEstatisticas(R.layout.estatisticas, position);
		
	}
	
	private void graficoDeLinhaHandler(List<Double> values, List<Date> dates, String titulo) {
		GraficoDeLinha grafico = new GraficoDeLinha();
		Intent intent = grafico.getIntent(this, values, dates, titulo);
		startActivity(intent);
	}
	
	private void mostrarEstatisticas(final int layout, final int id) {
//		System.out.println("id>>>>>> " + id);
		setContentView(layout);
		
		final List<Dados> dados = dadosFachada.getDadosDoAluno(id);
//		System.out.println("dados: " + dados);//TODO
//		final List<Dados> dados = SGDB.getDadosFromUser(id);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.visualizar_alunos, menu);
		return true;
	}

}
