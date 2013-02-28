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
import android.widget.TextView;

public class GerenciarFinancasActivity extends Activity {

	private FinancasFachada financas;
	private Integer idAluno;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gerenciar_financas);
		setTitle("Gerencia de Financas");
		
		financas = FitnessManagementSingleton.getFinancasFachadaInstance();
		idAluno = getIntent().getIntExtra("id_aluno", -1);
		
		menuFinancas();
	}

	private void menuFinancas() {
		final TextView tvDivida = (TextView) findViewById(R.id.textViewDividaGerenciarFinancas);
		tvDivida.setText(financas.getDividaTotal(idAluno).toString());
		
		Button bVoltar = (Button) findViewById(R.id.buttonVoltarGerenciarFinancas);
		Button bAdicionar = (Button) findViewById(R.id.buttonAdicionarGerenciarFinancas);
		Button bQuitar = (Button) findViewById(R.id.buttonQuitarGerenciarFinancas);
		Button bQuitarTudo = (Button) findViewById(R.id.buttonQuitarTudoGerenciarFinancas);
		
		bAdicionar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText etAdicionar = (EditText) findViewById(R.id.editTextAdicionarGerenciarFinancas);
				
				Double valor = Double.parseDouble(etAdicionar.getText().toString());
				financas.addDivida(idAluno, valor);
				etAdicionar.setText("");
				tvDivida.setText(financas.getDividaTotal(idAluno).toString());
			}
		});
		bQuitar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				EditText etQuitar = (EditText) findViewById(R.id.editTextQuitarGerenciarFinancas);
				
				Double valor = Double.parseDouble(etQuitar.getText().toString());
				financas.quitaDivida(idAluno, valor);
				etQuitar.setText("");
				tvDivida.setText(financas.getDividaTotal(idAluno).toString());
			}
		});
		bQuitarTudo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				financas.quitaDivida(idAluno, financas.getDividaTotal(idAluno));
				tvDivida.setText(financas.getDividaTotal(idAluno).toString());
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
		getMenuInflater().inflate(R.menu.activity_gerenciar_financas, menu);
		return true;
	}

}
