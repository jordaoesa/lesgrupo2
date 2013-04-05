package br.edu.ufcg.fitnessmanagement;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufcg.gerenciar.Atividade;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MetasActivity extends Activity {

	int itemSpinnerSelecionado = 0;
	int valorIdadeMetas;
	int valorAlturaMetas;
	int valorPesoPerdaMetas;
	final Spinner spinnerPraticaMetas= (Spinner) findViewById(R.id.spinnerPraticaMeta);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_metas);
		menuMostrarMeta();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_metas, menu);
		return true;
	}
	
	private void menuMostrarMeta(){
		final EditText idadeMetas = (EditText)findViewById(R.id.editTextIdadeMeta);
		final EditText alturaMetas = (EditText)findViewById(R.id.editTextAlturaMeta);
		final EditText pesoMetas = (EditText)findViewById(R.id.editTextPesoPerderMeta);
		
		//adiciona os elementos no spinner
		final List<String> list=new ArrayList<String>();
	    list.add("Não pratica exercício");
	    list.add("1-3 exercícios por semana");
	    list.add("3-5 exercícios por semana");
	    list.add("Exercita todos os dias");
	    list.add("Profissional");
	    ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
	    arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinnerPraticaMetas.setAdapter(arrayAdapter1);
	    //pega o elemento selecionado do spinner
	    
	    spinnerPraticaMetas.setOnItemSelectedListener(new OnItemSelectedListener()
        {
			  @Override
			  public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long id) {
				  	itemSpinnerSelecionado = position;
			  }
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
        });
	    
	    //Aqui eu so preciso agora criar o Objeto metas não sei como tu fez ai, mas ja tem 
	    //todos os dados coletados ai em cima.
	    //o que foi escolhido no spinner ta no atributo da classe "itemSpinnerSelecionado" ele
	    //é o índice da lista de opções.
	    //agora é o botão mostrar Meta que vai aparecer uma caixa de dialogo com as informações
	    //que so precisa chamar algum getMeta do objeto que tu cria e mostrar,
	    //so precisa jogar ele dentro do dialogo ai no try;
	    
	    final Button botaoMostrarMetas = (Button)findViewById(R.id.buttonCalcularMeta);
	    botaoMostrarMetas.setOnClickListener(new OnClickListener() {
	    	@Override
			public void onClick(View v) {
				try{
					valorIdadeMetas = Integer.parseInt(idadeMetas.getText().toString());
					valorAlturaMetas = Integer.parseInt(alturaMetas.getText().toString());
					valorPesoPerdaMetas = Integer.parseInt(pesoMetas.getText().toString());
					//cria o objeto aqui passando os parametros
					//e joga um possível "getMeta()" numa caixinha de dialogo
				}catch(Exception e){
					//aqui em baixo tu muda o texto pra alguma exceção se teu objeto lançar.
					Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
				}
				
			}
		});

	}
}