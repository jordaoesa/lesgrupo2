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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarDadosActivity extends Activity {

	private DadosFachada dadosFachada;
	private Integer idAluno;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_dados);
		setTitle("Atualizar Dados");
		
		dadosFachada = FitnessManagementSingleton.getDadosFachadaInstance();
		idAluno = getIntent().getIntExtra("id_aluno", -1);
		atualizarDados();
	}
	
	private void atualizarDados() {
		Button atualizar = (Button) findViewById(R.id.buttonAtualizarDados);
		final EditText data = (EditText)findViewById(R.id.editTextData);
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
		});
		
		atualizar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Editable peso = ((EditText)findViewById(R.id.editTextPeso)).getText();
				Editable calorias = ((EditText)findViewById(R.id.editTextCalorias)).getText();
				Editable braco = ((EditText)findViewById(R.id.editTextBraco)).getText();
				Editable perna = ((EditText)findViewById(R.id.editTextPerna)).getText();
				Editable imc = ((EditText)findViewById(R.id.editTextIMC)).getText();
				
				if(verificaDadosInformados(peso, calorias, braco, perna, imc, data.getText())){
					java.text.DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
					Date date = null;
					try {
						date = formater.parse(data.getText().toString());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					System.out.println(date.toString());
					
					Dados dados = new Dados(Double.parseDouble(peso.toString()), Double.parseDouble(calorias.toString()), Double.parseDouble(braco.toString()), Double.parseDouble(perna.toString()), Double.parseDouble(imc.toString()), date);
					
					dadosFachada.adicionaDadosDoAluno(idAluno, dados);
					Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_SHORT).show();
					finish();
				}
			}

		});
		
		Button voltar = (Button) findViewById(R.id.buttonAtualizarVoltar);
		voltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}
	
	private boolean verificaDadosInformados(Editable peso, Editable calorias, Editable braco, Editable perna, Editable imc, Editable data) {
		if(peso == null || peso.toString().equals("") || !verificaDouble(peso)){
			Toast.makeText(getApplicationContext(), "Peso Inválido", Toast.LENGTH_SHORT).show();
			return false;
		}else if(calorias == null || calorias.toString().equals("") || !verificaDouble(calorias)){
			Toast.makeText(getApplicationContext(), "Calorias Inválido", Toast.LENGTH_SHORT).show();
			return false;
		}else if(braco == null || braco.toString().equals("") || !verificaDouble(braco)){
			Toast.makeText(getApplicationContext(), "Tamanho Braço Inválido", Toast.LENGTH_SHORT).show();
			return false;
		}else if(perna == null || perna.toString().equals("") || !verificaDouble(perna)){
			Toast.makeText(getApplicationContext(), "Tamanho Perna Inválido", Toast.LENGTH_SHORT).show();
			return false;
		}else if(imc == null || imc.toString().equals("") || !verificaDouble(imc)){
			Toast.makeText(getApplicationContext(), "IMC Inválido", Toast.LENGTH_SHORT).show();
			return false;
		}else if(data == null || data.toString().equals("") || !data.toString().matches("[0-3]{1}[0-9]{1}\\/[0-1]{1}[0-9]{1}\\/[1-2]{1}[0-9]{3}")){
			Toast.makeText(getApplicationContext(), "Data Inválida", Toast.LENGTH_SHORT).show();
			return false;
		}
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_cadastrar_dados, menu);
		return true;
	}

}
