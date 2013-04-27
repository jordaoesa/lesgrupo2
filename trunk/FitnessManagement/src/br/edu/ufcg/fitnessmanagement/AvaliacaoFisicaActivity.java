package br.edu.ufcg.fitnessmanagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.ufcg.aluno.Aluno;
import br.edu.ufcg.aluno.Dados;
import br.edu.ufcg.fachada.AlunoFachada;
import br.edu.ufcg.fachada.DadosFachada;
import br.edu.ufcg.util.FitnessManagementSingleton;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AvaliacaoFisicaActivity extends Activity {

	private DadosFachada dadosFachada;
	private Integer idAluno;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_avaliacao_fisica);
		setTitle("Atualizar Dados");
		
		dadosFachada = FitnessManagementSingleton.getDadosFachadaInstance();
		idAluno = getIntent().getIntExtra("id_aluno", -1);
		atualizarDados();
	}
	
	private void atualizarDados() {
		Button inserirDados = (Button) findViewById(R.id.buttonAtualizarDados);
		/*final EditText data = (EditText)findViewById(R.id.editTextData);
		data.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(before < count){
					String date = data.getText().toString();
					if(date.length() == 2){
						data.setText(date + "/");
					}else if(date.length() == 3 && date.indexOf("/") != 2){
						data.setText(date.substring(0, 2) + "/" + date.substring(2));
					}else if(date.length() == 5){
						data.setText(date + "/");
					}else if(date.length() == 6 && date.lastIndexOf("/") != 5){
						data.setText(date.substring(0, 5) + "/" + date.substring(5));
					}
				}
				data.setSelection(data.length());
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			@Override
			public void afterTextChanged(Editable s) {
			}
		});*/
		
		inserirDados.setOnClickListener(new OnClickListener() {
			
			EditText peso = ((EditText)findViewById(R.id.editTextPeso));
			EditText altura = ((EditText)findViewById(R.id.editTextAltura));
			EditText bracoER = ((EditText)findViewById(R.id.editTextBracoEsquerdoRelaxado));
			EditText bracoEC = ((EditText)findViewById(R.id.editTextBracoEsquerdoContraido));
			EditText antebracoE = ((EditText)findViewById(R.id.editTextAntebracoEsquerdo));
			EditText coxaE = ((EditText)findViewById(R.id.editTextCoxaEsquerda));
			EditText panturrilhaE = ((EditText)findViewById(R.id.editTextPanturrilhaEsquerda));
			EditText bracoDR = ((EditText)findViewById(R.id.editTextBracoDireitoRelaxado));
			EditText bracoDC = ((EditText)findViewById(R.id.editTextBracoDireitoContraido));
			EditText antebracoD = ((EditText)findViewById(R.id.editTextAntebracoDireito));
			EditText coxaD = ((EditText)findViewById(R.id.editTextCoxaDireita));
			EditText panturrilhaD = ((EditText)findViewById(R.id.editTextPanturrilhaDireita));
			
			@Override
			public void onClick(View arg0) {
				
				if(verificaDadosInformados(peso.getText(), altura.getText(), bracoER.getText(), bracoEC.getText(), antebracoE.getText(), coxaE.getText(), panturrilhaE.getText(), bracoDR.getText(), bracoDC.getText(), antebracoD.getText(), coxaD.getText(), panturrilhaD.getText())){
					/*java.text.DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
					Date date = null;
					try {
						date = formater.parse(data.getText().toString());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					System.out.println(date.toString());*/
					
					Dados dados = new Dados(
							Double.parseDouble(peso.getText().toString()),
							Double.parseDouble(altura.getText().toString()),
							Double.parseDouble(bracoER.getText().toString()),
							Double.parseDouble(bracoEC.getText().toString()),
							Double.parseDouble(antebracoE.getText().toString()),
							Double.parseDouble(coxaE.getText().toString()),
							Double.parseDouble(panturrilhaE.getText().toString()),
							Double.parseDouble(bracoDR.getText().toString()),
							Double.parseDouble(bracoDC.getText().toString()),
							Double.parseDouble(antebracoD.getText().toString()),
							Double.parseDouble(coxaD.getText().toString()),
							Double.parseDouble(panturrilhaD.getText().toString()),
							new Date());
					
					dadosFachada.adicionaDadosDoAluno(idAluno, dados);
					Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_SHORT).show();
					clearEditTexts();
				}
			}

			private void clearEditTexts() {
				peso.setText("");
				altura.setText("");
				bracoER.setText("");
				bracoEC.setText("");
				antebracoE.setText("");
				coxaE.setText("");
				panturrilhaE.setText("");
				bracoDR.setText("");
				bracoDC.setText("");
				antebracoD.setText("");
				coxaD.setText("");
				panturrilhaD.setText("");
			}

		});
		
	}
	
	private boolean verificaDadosInformados(Editable peso, Editable altura, Editable bracoER, Editable bracoEC, Editable antebracoE, Editable coxaE, Editable panturrilhaE, Editable bracoDR, Editable bracoDC, Editable antebracoD, Editable coxaD, Editable panturrilhaD) {
		if(peso == null || peso.toString().equals("") || !verificaDouble(peso)){
			Toast.makeText(getApplicationContext(), "Peso Inválido", Toast.LENGTH_SHORT).show();
			return false;
		}else if(altura == null || altura.toString().equals("") || !verificaDouble(altura)){
			Toast.makeText(getApplicationContext(), "Altura Inválida", Toast.LENGTH_SHORT).show();
			return false;
		}else if(bracoER == null || bracoER.toString().equals("") || !verificaDouble(bracoER)){
			Toast.makeText(getApplicationContext(), "Tamanho Braço Esquerdo Inválido", Toast.LENGTH_SHORT).show();
			return false;
		}else if(bracoEC == null || bracoEC.toString().equals("") || !verificaDouble(bracoEC)){
			Toast.makeText(getApplicationContext(), "Tamanho Braço Esquerdo Inválido", Toast.LENGTH_SHORT).show();
			return false;
		}else if(antebracoE == null || antebracoE.toString().equals("") || !verificaDouble(antebracoE)){
			Toast.makeText(getApplicationContext(), "Tamanho Antebraço Esquerdo Inválido", Toast.LENGTH_SHORT).show();
			return false;
		}else if(coxaE == null || coxaE.toString().equals("") || !verificaDouble(coxaE)){
			Toast.makeText(getApplicationContext(), "Tamanho Coxa Esquerda Inválida", Toast.LENGTH_SHORT).show();
			return false;
		}else if(panturrilhaE == null || panturrilhaE.toString().equals("") || !verificaDouble(panturrilhaE)){
			Toast.makeText(getApplicationContext(), "Tamanho Panturrilha Esquerda Inválida", Toast.LENGTH_SHORT).show();
			return false;
		}else if(bracoDR == null || bracoDR.toString().equals("") || !verificaDouble(bracoDR)){
			Toast.makeText(getApplicationContext(), "Tamanho Braço Direito Inválido", Toast.LENGTH_SHORT).show();
			return false;
		}else if(bracoDC == null || bracoDC.toString().equals("") || !verificaDouble(bracoDC)){
			Toast.makeText(getApplicationContext(), "Tamanho Braço Direito Inválido", Toast.LENGTH_SHORT).show();
			return false;
		}else if(antebracoD == null || antebracoD.toString().equals("") || !verificaDouble(antebracoD)){
			Toast.makeText(getApplicationContext(), "Tamanho Antebraço Direito Inválido", Toast.LENGTH_SHORT).show();
			return false;
		}else if(coxaD == null || coxaD.toString().equals("") || !verificaDouble(coxaD)){
			Toast.makeText(getApplicationContext(), "Tamanho Coxa Direita Inválida", Toast.LENGTH_SHORT).show();
			return false;
		}else if(panturrilhaD == null || panturrilhaD.toString().equals("") || !verificaDouble(panturrilhaD)){
			Toast.makeText(getApplicationContext(), "Tamanho Panturrilha Direita Inválida", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		/*else if(data == null || data.toString().equals("") || !data.toString().matches("[0-3]{1}[0-9]{1}\\/[0-1]{1}[0-9]{1}\\/[1-2]{1}[0-9]{3}")){
			Toast.makeText(getApplicationContext(), "Data Inválida", Toast.LENGTH_SHORT).show();
			return false;
		}*/
		return true;
	}

	private boolean verificaDouble(Editable editable) {
		try {
			Double.parseDouble(editable.toString());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
////		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_avaliacao_fisica, menu);
//		return true;
//	}
	
	
	private final int VOLTAR = 1;
	private final int AJUDA = 2;
	
	private final int avaliacaoFisica=3;

	String ajuda = "Ajuda";
	String voltar = "Voltar";
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_main, menu);
//		return true;

		
		boolean r = super.onCreateOptionsMenu(menu);
		super.onCreateOptionsMenu(menu);
		//menu.add(0, AJUDA, 0, ajuda).setIcon(R.drawable.alert);
		menu.add(0, VOLTAR, 0, voltar).setIcon(R.drawable.back);
		
		
		SubMenu menuAjuda = menu.addSubMenu(ajuda);
		menuAjuda.setIcon(R.drawable.help);
		menuAjuda.add(0, avaliacaoFisica, 0, "Avaliacao Fisica");
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
