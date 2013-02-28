package br.edu.ufcg.fitnessmanagement;

import br.edu.ufcg.fachada.FinancasFachada;
import br.edu.ufcg.util.FitnessManagementSingleton;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AgendarAcompanhamentoActivity extends Activity {

	private FinancasFachada financas;
	private Integer idAluno;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agendar_acompanhamento);
		setTitle("Agendar Acompanhamento");
		
		financas = FitnessManagementSingleton.getFinancasFachadaInstance();
		idAluno = getIntent().getIntExtra("id_aluno", -1);
		
		menuAgendamento();
	}


	private void menuAgendamento() {
		Button bVoltar = (Button) findViewById(R.id.buttonVoltarAgendarAcompanhamento);
		Button bAgendar = (Button) findViewById(R.id.buttonAgendarAgendarAcompanhamento);
		
		
		bAgendar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				
				
				//TODO FAZ A CHAMADA DO METODO COM A FUNCOES QUE TU QUER AQUI IRVILE E DEIXA MEU EDIT TEXT AQUI EM BAIXO.. kkk =P
				
				
				EditText etValor = (EditText) findViewById(R.id.editTextValorAgendarAcompanhamento);
				Double valor = null;
				try{
					valor = Double.parseDouble(etValor.getText().toString());
				}catch(NumberFormatException ne){
					valor = 0D;
				}
				financas.addDivida(idAluno, valor);
				finish();
			}
		});
		bVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_agendar_acompanhamento, menu);
		return true;
	}

}
