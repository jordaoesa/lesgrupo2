package br.edu.ufcg.fitnessmanagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import br.edu.ufcg.aluno.Aluno;
import br.edu.ufcg.aluno.Dados;
import br.edu.ufcg.exercicio.Exercicio;
import br.edu.ufcg.exercicio.GrupoExercicio;
import br.edu.ufcg.grafico.GraficoDeLinha;
import br.edu.ufcg.sgbd.SGDB;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		menuInicial();
		inserirUsuariosNoBanco();
	}
	
	private void inserirUsuariosNoBanco() {
		Aluno jordao = new Aluno("jordao", "Campina", "M");
		
		Dados dados1Jordao = new Dados(75.0, 100.0, 31.0, 55.0, 20.0, new Date("01/01/2013"));
		Dados dados2Jordao = new Dados(76.0, 200.0, 32.0, 57.0, 21.0, new Date("02/01/2013"));
		Dados dados3Jordao = new Dados(77.0, 50.0, 33.0, 60.0, 21.0, new Date("03/01/2013"));

		Dados dados4Jordao = new Dados(78.0, 100.0, 34.0, 51.0, 20.0, new Date("04/01/2013"));
		Dados dados5Jordao = new Dados(79.0, 200.0, 35.0, 52.0, 21.0, new Date("05/01/2013"));
		Dados dados6Jordao = new Dados(82.0, 50.0, 36.0, 63.0, 21.0, new Date("06/01/2013"));
		
		Dados dados7Jordao = new Dados(85.0, 100.0, 37.0, 54.0, 20.0, new Date("07/01/2013"));
		Dados dados8Jordao = new Dados(89.0, 200.0, 38.0, 55.0, 21.0, new Date("08/01/2013"));
		Dados dados9Jordao = new Dados(97.0, 50.0, 39.0, 66.0, 21.0, new Date("09/01/2013"));
		
		SGDB.addAluno(jordao);
		
		SGDB.addDados(jordao.getId(), dados1Jordao);
		SGDB.addDados(jordao.getId(), dados2Jordao);
		SGDB.addDados(jordao.getId(), dados3Jordao);
		SGDB.addDados(jordao.getId(), dados4Jordao);
		SGDB.addDados(jordao.getId(), dados5Jordao);
		SGDB.addDados(jordao.getId(), dados6Jordao);
		SGDB.addDados(jordao.getId(), dados7Jordao);
		SGDB.addDados(jordao.getId(), dados8Jordao);
		SGDB.addDados(jordao.getId(), dados9Jordao);
		
	}

	private void graficoDeLinhaHandler(List<Double> values, List<Date> dates, String titulo) {
		GraficoDeLinha grafico = new GraficoDeLinha();
		Intent intent = grafico.getIntent(this, values, dates, titulo);
		startActivity(intent);
	}
	
	private void menuInicial() {
		setContentView(R.layout.activity_fitness_management);
		
		Button bCadastrar = (Button) findViewById(R.id.buttonCadastrarAlunos);
		Button bDados = (Button) findViewById(R.id.buttonCadastrarDados);
		Button bVisualizar = (Button) findViewById(R.id.buttonVisualizarEstatisticas);
		
		bCadastrar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				menuCadastrar();
			}
		});
		
		bDados.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				menuVisualizarAlunos(R.layout.atualizar_dados);
			}
		});
		
		bVisualizar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				menuVisualizarAlunos(R.layout.estatisticas);
			}
		});
	}
	
	private void menuCadastrar() {
		setContentView(R.layout.cadastrar);
		
		Button cadastrar = (Button) findViewById(R.id.buttonCadastrarAluno);
		cadastrar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				String nome = ((EditText) findViewById(R.id.editTextNome)).getText().toString();
				String endereco = ((EditText) findViewById(R.id.editTextEndereco)).getText().toString();
				RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupSexo);
				
				Aluno aluno = null;
				int sexo = radioGroup.getCheckedRadioButtonId();
				if(sexo == R.id.radioButtonSexoMasc){
					aluno = new Aluno(nome, endereco, "M");
				}else if(sexo == R.id.radioButtonSexoFemi){
					aluno = new Aluno(nome, endereco, "F");
				}
				
				SGDB.addAluno(aluno);
				Toast.makeText(getApplicationContext(), "Cadastrado com Sucesso", Toast.LENGTH_SHORT).show();
				menuInicial();
			}
		});
		
		Button voltar = (Button) findViewById(R.id.buttonCadastrarVoltar);
		voltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				menuInicial();
			}
		});
		
	}
	
	private void menuCadastrarExercicio() {
		
		setContentView(R.layout.cadastro_exercicio);
		
		Button botaoCadastrarExercicio = (Button) findViewById(R.id.botaoCadastrarExercicio);
		
		final Spinner spinnerNomeExerc = (Spinner)findViewById(R.id.spinnerExercicio);
		final Spinner spinnerNomeMaquina = (Spinner)findViewById(R.id.spinnerMaquina);
		final Spinner spinnerGrupoMuscular = (Spinner)findViewById(R.id.spinnerGrupoMuscular);
		final Spinner spinnerNumSerie = (Spinner)findViewById(R.id.spinnerNumeroSerie);
		final Spinner spinnerNumRepeticoes = (Spinner)findViewById(R.id.spinnerNumeroRepeticoes);
		final EditText caixaObservacao = (EditText)findViewById(R.id.caixaObservacaoExercicio);
		final EditText caixaCadExerPeso = (EditText) findViewById(R.id.caixaCadastroExercicioPeso);
		final Exercicio exerc;
		
		botaoCadastrarExercicio.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				exerc = new Exercicio(spinnerNomeExerc.getSelectedItem().getClass().getName(), 
//						spinnerNomeMaquina.getSelectedItem().getClass().getName(), 
//						GrupoExercicio.BRACO, 
//						Integer.parseInt(spinnerNumSerie.getSelectedItem().getClass().getName()), 
//						Integer.parseInt(spinnerNumRepeticoes.getSelectedItem().getClass().getName()), 
//						Integer.parseInt(caixaCadExerPeso.getText()));
				
				
				
			}
		});
		
		Button voltar = (Button) findViewById(R.id.buttonCadastrarVoltar);
		voltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				menuInicial();
			}
		});
		
	}
	
	private void menuVisualizarAlunos(final int layout) {
		setContentView(R.layout.visualizar_alunos);
		ListView listView = (ListView) findViewById(R.id.listViewVisualizarAlunos);
		final String alunos[] = SGDB.getOnlyNamesOfAlunos();
		final Integer ids[] = SGDB.getOnlyIdsOfAlunos();
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_list, R.id.textViewItem, alunos);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(), adapter.getItem(position).toString(), Toast.LENGTH_SHORT).show();
				if(layout == R.layout.estatisticas){
					mostrarEstatisticas(layout, ids[position]);
				}else if(layout == R.layout.atualizar_dados){
					atualizarDados(layout, ids[position]);
				}
				
			}

			private void mostrarEstatisticas(final int layout, final int id) {
				setContentView(layout);
				
				final List<Dados> dados = SGDB.getDadosFromUser(id);
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
						menuVisualizarAlunos(layout);
					}
				});
				
			}

			private void atualizarDados(final int layout, final int id) {
				setContentView(layout);
				
				Button atualizar = (Button) findViewById(R.id.buttonAtualizarDados);
				atualizar.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						Double peso = Double.parseDouble(((EditText)findViewById(R.id.editTextPeso)).getText().toString());
						Double calorias = Double.parseDouble(((EditText)findViewById(R.id.editTextCalorias)).getText().toString());
						Double braco = Double.parseDouble(((EditText)findViewById(R.id.editTextBraco)).getText().toString());
						Double perna = Double.parseDouble(((EditText)findViewById(R.id.editTextPerna)).getText().toString());
						Double imc = Double.parseDouble(((EditText)findViewById(R.id.editTextIMC)).getText().toString());
						String data = ((EditText)findViewById(R.id.editTextData)).getText().toString();
						
						java.text.DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
						Date date = null;
						try {
							date = formater.parse(data);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						System.out.println(date.toString());
						
						Dados dados = new Dados(peso, calorias, braco, perna, imc, date);
						SGDB.addDados(id, dados);
						Toast.makeText(getApplicationContext(), "Sucess", Toast.LENGTH_SHORT).show();
						menuVisualizarAlunos(layout);
					}
				});
				
				Button voltar = (Button) findViewById(R.id.buttonAtualizarVoltar);
				voltar.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						menuInicial();
					}
				});
			}
			
		});
		
		Button voltar = (Button) findViewById(R.id.buttonVoltarVisualizacao);
		voltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				menuInicial();
			}
		});
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
