package br.edu.ufcg.fitnessmanagement;

import br.edu.ufcg.fachada.FinancasFachada;
import br.edu.ufcg.util.FitnessManagementSingleton;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
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
				Double valor = null;
				try{
					valor = Double.parseDouble(etAdicionar.getText().toString());
				}catch(Exception e){
					valor = 0D;
				}
				financas.addDivida(idAluno, valor);
				etAdicionar.setText("");
				tvDivida.setText(financas.getDividaTotal(idAluno).toString());
			}
		});
		bQuitar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				EditText etQuitar = (EditText) findViewById(R.id.editTextQuitarGerenciarFinancas);
				Double valor = null;
				try{
					valor = Double.parseDouble(etQuitar.getText().toString());
				}catch(Exception e){
					valor = 0D;
				}
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


	
	private final int VOLTAR = 1;
	private final int AJUDA = 2;
	
	private final int gerenciarFinancas=3;
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
		menuAjuda.add(0, gerenciarFinancas, 0, "Gerenciar Financas");
		
		return r;
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		switch(item.getItemId()){
		
		
		case VOLTAR: mensagemExibir("Voltar", "Voltando",R.drawable.back); finish();  break;
		case AJUDA: mensagemExibir("Ajuda", "Gerenciar Financas",R.drawable.help); break;
		case gerenciarFinancas: mensagemExibir("Gerenciar Financas", "Adicione ou quite uma divida do seu aluno.",R.drawable.help); break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public void mensagemExibir(String titulo, String texto, int icon){
		AlertDialog.Builder mensagem = new AlertDialog.Builder(this);
		mensagem.setTitle(titulo);
		mensagem.setMessage(texto);
		mensagem.setIcon(icon);
		mensagem.setNeutralButton("OK", null);
		mensagem.show();
	}


}
