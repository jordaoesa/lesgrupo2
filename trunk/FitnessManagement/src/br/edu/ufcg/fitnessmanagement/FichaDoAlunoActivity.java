package br.edu.ufcg.fitnessmanagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import android.widget.EditText;
import android.widget.Toast;

public class FichaDoAlunoActivity extends Activity {

	private DadosFachada dadosFachada;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		dadosFachada = FitnessManagementSingleton.getDadosFachadaInstance();
		menuDeOpcoes();
		
	}

	private void menuDeOpcoes() {
		setContentView(R.layout.activity_ficha_do_aluno);
		setTitle("Ficha do Aluno");
		
		Button bCadastrarDados = (Button) findViewById(R.id.buttonCadastrarDadosFichaAluno);
		Button bCadastrarAtividade = (Button)findViewById(R.id.buttonCadastrarAtividadeFichaAluno);
		Button bCadastrarFicha = (Button)findViewById(R.id.buttonCriarFichaFichaAluno);
		Button bVisualizarEstatisticas = (Button)findViewById(R.id.buttonVisualizarEstatisticasFichaAluno);//TODO
		Button bAgendarAcompanhamento = (Button)findViewById(R.id.buttonAgendarAcompanhamentoFichaAluno);//TODO
		
		bCadastrarDados.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//atualizarDados(); //TODO
			}
		});
		
		bCadastrarAtividade.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				menuCadastrarAtividade();
			}
		});
		bCadastrarFicha.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				menuCadastrarFicha();
			}
		});
		bVisualizarEstatisticas.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//mostrarEstatisticas(); //TODO
			}
		});
		
		Button voltar = (Button) findViewById(R.id.buttonVoltarFichaAluno);
		voltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}
	
	private void menuCadastrarAtividade() {
		
		setContentView(R.layout.cadastro_atividade);
		Button botaoCancelarAtividade = (Button) findViewById(R.id.botaoCancelarCadastroAtividade);
		botaoCancelarAtividade.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				menuDeOpcoes();
			}
		});
	}
	private void menuCadastrarFicha() {
		
		setContentView(R.layout.ficha_aluno);
		Button botaoCancelarFicha = (Button) findViewById(R.id.buttonCancelarCadastroFicha);
		botaoCancelarFicha.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				menuDeOpcoes();
			}
		});
	}
	private void mostrarEstatisticas(final int id) {
		setContentView(R.layout.estatisticas);
		setTitle("Estatísticas");
		
		final List<Dados> dados = dadosFachada.getDadosDoAluno(id);
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
				menuDeOpcoes();
			}
		});
		
	}
	private void atualizarDados(final int id) {
		setContentView(R.layout.atualizar_dados);
		setTitle("Atualizar Dados");
		
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
				
				dadosFachada.adicionaDadosDoAluno(id, dados);
				Toast.makeText(getApplicationContext(), "Sucess", Toast.LENGTH_SHORT).show();
				menuDeOpcoes();
			}
		});
		
		Button voltar = (Button) findViewById(R.id.buttonAtualizarVoltar);
		voltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				menuDeOpcoes();
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
		getMenuInflater().inflate(R.menu.activity_ficha_do_aluno, menu);
		return true;
	}

}
