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
				
				dadosFachada.adicionaDadosDoAluno(idAluno, dados);
				Toast.makeText(getApplicationContext(), "Sucess", Toast.LENGTH_SHORT).show();
				finish();
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_cadastrar_dados, menu);
		return true;
	}

}
