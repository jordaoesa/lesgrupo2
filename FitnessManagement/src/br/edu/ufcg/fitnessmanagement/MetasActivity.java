package br.edu.ufcg.fitnessmanagement;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import br.edu.ufcg.aluno.Aluno;
import br.edu.ufcg.fachada.MetasFachada;
import br.edu.ufcg.grafico.GraficoDeLinha;
import br.edu.ufcg.metas.WeightLoss;
import br.edu.ufcg.util.FitnessManagementSingleton;
import br.edu.ufcg.util.Message;
import br.edu.ufcg.util.Utils;

public class MetasActivity extends Activity {

	private int itemSpinnerSelecionado = 0;
	private int valorIdadeMetas;
	private int valorAlturaMetas;
	private double valorPesoPerdaMetas;
	private Aluno aluno;
	private MetasFachada metaFachada;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		aluno = FitnessManagementSingleton.getAlunoFachadaInstance()
				.getAlunoFromId(getIntent().getIntExtra("id_aluno", -1));
		metaFachada = FitnessManagementSingleton.getMetaFachadaInstance();
		menu_meta();
	}

	private void menu_meta() {
		setContentView(R.layout.opcoes_tela_metas);

		final Button botaoVoltarMenuMetas = (Button) findViewById(R.id.buttonVoltarMenuMetas);
		botaoVoltarMenuMetas.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				finish();
			}
		});

		final Button botaoNovaMeta = (Button) findViewById(R.id.buttonNovaMeta);
		botaoNovaMeta.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				menuNovaMeta();
			}
		});

		final Button botaoAcompanhamentoMeta = (Button) findViewById(R.id.buttonAcompanhamentoMeta);
		botaoAcompanhamentoMeta.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				WeightLoss weightLoss = FitnessManagementSingleton
						.getMetaFachadaInstance().getMetaWeightLoss(
								aluno.getId());
				if (weightLoss == null) {
					Builder dialog = Utils.showMessage(MetasActivity.this,
							Message.ALERT, "Atenção",
							"Nenhuma meta cadastrada.Deseja criar?");
					dialog.setPositiveButton("Sim",
							new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int which) {
							menuNovaMeta();
						}
					});
					dialog.setNegativeButton("Não", null);
					dialog.show();
				} else {
					Intent intentAcompanhamentoMeta = new Intent(
							getApplicationContext(),
							AcompanhamentoMetasActivity.class);
					intentAcompanhamentoMeta.putExtra("id_aluno", aluno.getId());
					startActivity(intentAcompanhamentoMeta);
				}
			}
		});
//		final Button botaoVisualizaMeta = (Button) findViewById(R.id.buttonAcompanhamentoMetaVisualizar);
//		botaoVisualizaMeta.setOnClickListener(new OnClickListener() {
//			public void onClick(View arg0) {
//				MetasFachada metaFachada = FitnessManagementSingleton.getMetaFachadaInstance();
//				if(metaFachada.getAcompanhamentoWeightLoss(aluno.getId()).size() > 0){
//					GraficoDeLinha grafico = new GraficoDeLinha();
//					Intent intent = grafico.getGraficoMetas(MetasActivity.this, metaFachada.getMetaWeightLoss(aluno.getId()), aluno.getId());
//					intent.setAction(Intent.ACTION_MAIN);
//					intent.addCategory(Intent.CATEGORY_HOME);
//					startActivity(intent);
//				}else{
//					Builder dialog = Utils.showMessage(MetasActivity.this,
//							Message.ALERT, "Atenção",
//							"Nenhuma acompanhamento feito.Deseja fazer?");
//					dialog.setPositiveButton("Sim",
//							new DialogInterface.OnClickListener() {
//						public void onClick(DialogInterface dialog,
//								int which) {
//							Intent intentAcompanhamentoMeta = new Intent(
//									getApplicationContext(),
//									AcompanhamentoMetasActivity.class);
//							intentAcompanhamentoMeta.putExtra("id_aluno", aluno.getId());
//							startActivity(intentAcompanhamentoMeta);	
//						}
//					});
//					dialog.setNegativeButton("Não", null);
//					dialog.show();
//				}
//
//			}
//		});
	}

	private void menuNovaMeta() {
		setContentView(R.layout.activity_metas);
		final EditText alturaMetas = (EditText) findViewById(R.id.editTextAlturaMeta);
		final EditText pesoMetas = (EditText) findViewById(R.id.editTextPesoPerderMeta);
		final EditText pesoAtual = (EditText) findViewById(R.id.editTextPesoAtualMeta);

		// adiciona os elementos no spinner
		final List<String> nivelExercicioFisico = new ArrayList<String>();
		nivelExercicioFisico.add("Não pratica exercício");
		nivelExercicioFisico.add("1-3 exercícios por semana");
		nivelExercicioFisico.add("3-5 exercícios por semana");
		nivelExercicioFisico.add("Exercita todos os dias");
		nivelExercicioFisico.add("Profissional");
		ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, nivelExercicioFisico);
		arrayAdapter1
		.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		final Spinner spinnerPraticaMetas = (Spinner) findViewById(R.id.spinnerPraticaMeta);
		spinnerPraticaMetas.setAdapter(arrayAdapter1);
		// pega o elemento selecionado do spinner

		spinnerPraticaMetas
		.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long id) {
				itemSpinnerSelecionado = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		// Aqui eu so preciso agora criar o Objeto metas não sei como tu fez ai,
		// mas ja tem
		// todos os dados coletados ai em cima.
		// o que foi escolhido no spinner ta no atributo da classe
		// "itemSpinnerSelecionado" ele
		// é o índice da lista de opções.
		// agora é o botão mostrar Meta que vai aparecer uma caixa de dialogo
		// com as informações
		// que so precisa chamar algum getMeta do objeto que tu cria e mostrar,
		// so precisa jogar ele dentro do dialogo ai no try;

		final Button botaoMostrarMetas = (Button) findViewById(R.id.buttonCalcularMeta);
		botaoMostrarMetas.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					try {
						final WeightLoss metaWeightLoss = new WeightLoss(
								pesoAtual.getText().toString(), pesoMetas
								.getText().toString(), aluno.getSexo(),
								alturaMetas.getText().toString(),
								nivelExercicioFisico
								.get(itemSpinnerSelecionado), String
								.valueOf(aluno.getIdade()), aluno
								.getId());
						Builder dialog = Utils.showMessage(MetasActivity.this,
								Message.CONFIRM, "Meta estimada",
								metaWeightLoss.toString() + "\n"
										+ "deseja salvar?");
						dialog.setPositiveButton("Sim",
								new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								if (metaFachada
										.existMetaWeightLoss(aluno
												.getId())) {
									Builder dialogSalvar = Utils
											.showMessage(
													MetasActivity.this,
													Message.CONFIRM,
													"Atenção",
													"Já existe uma meta cadastrada."
															+ "\n"
															+ "Deseja salvar por cima?");
									dialogSalvar
									.setPositiveButton(
											"Sim",
											new DialogInterface.OnClickListener() {
												public void onClick(
														DialogInterface dialog,
														int which) {
													metaFachada
													.delete(aluno
															.getId(),
															"TABLE_META");
													metaFachada
													.adicionaMetaWeightLoss(metaWeightLoss);
												}
											});
									dialogSalvar.setNegativeButton(
											"Não", null);
									dialogSalvar.show();
								} else {
									metaFachada
									.adicionaMetaWeightLoss(metaWeightLoss);
								}
							}
						});
						dialog.setNegativeButton("Não",
								new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
							}
						});
						dialog.show();
					} catch (Exception e) {
						Builder dialog = Utils.showMessage(MetasActivity.this,
								Message.ERROR, "Erro", e.getMessage());
						dialog.show();
					}

					// cria o objeto aqui passando os parametros
					// e joga um possível "getMeta()" numa caixinha de dialogo
				} catch (Exception e) {
					// aqui em baixo tu muda o texto pra alguma exceção se teu
					// objeto lançar.
					Toast.makeText(getApplicationContext(), e.getMessage(),
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		final Button botaoVoltarMetas = (Button) findViewById(R.id.buttonVoltarActivityMetas);
		botaoVoltarMetas.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				menu_meta();
			}
		});

	}

	private final int VOLTAR = 1;
	private final int AJUDA = 2;

	private final int metas = 3;

	String ajuda = "Ajuda";
	String voltar = "Voltar";

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// // Inflate the menu; this adds items to the action bar if it is
		// present.
		// getMenuInflater().inflate(R.menu.activity_main, menu);
		// return true;

		boolean r = super.onCreateOptionsMenu(menu);
		super.onCreateOptionsMenu(menu);
		// menu.add(0, AJUDA, 0, ajuda).setIcon(R.drawable.alert);
		menu.add(0, VOLTAR, 0, voltar).setIcon(R.drawable.back);

		SubMenu menuAjuda = menu.addSubMenu(ajuda);
		menuAjuda.setIcon(R.drawable.help);
		menuAjuda.add(0, metas, 0, "Metas");
		return r;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case VOLTAR:
			mensagemExibir("Voltar", "Voltando", R.drawable.back);
			finish();
			break;
		case AJUDA:
			mensagemExibir("Ajuda", "Metas", R.drawable.help);
			break;
		case metas:
			mensagemExibir(
					"Metas",
					"Podemos criar uma nova Meta para o aluno informando seu peso atual e quanto ele deseja perder para entrar em forma.\n",
					R.drawable.help);
			break;
		}
		return super.onOptionsItemSelected(item);

	}

	public void mensagemExibir(String titulo, String texto, int icone) {
		AlertDialog.Builder mensagem = new AlertDialog.Builder(this);
		mensagem.setTitle(titulo);
		mensagem.setIcon(icone);
		mensagem.setMessage(texto);
		mensagem.setNeutralButton("OK", null);
		mensagem.show();
	}

}