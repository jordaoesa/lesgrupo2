package br.edu.ufcg.fitnessmanagement;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import br.edu.ufcg.metas.WeightLoss;
import br.edu.ufcg.util.Message;
import br.edu.ufcg.util.Utils;

public class MetasActivity extends Activity {

	private int itemSpinnerSelecionado = 0;
	private int valorIdadeMetas;
	private int valorAlturaMetas;
	private double valorPesoPerdaMetas;
	
	
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
		final EditText pesoAtual = (EditText)findViewById(R.id.editTextPesoAtualMeta);
		
		
		//adiciona os elementos no spinner
		final List<String> nivelExercicioFisico = new ArrayList<String>();
	    nivelExercicioFisico.add("Não pratica exercício");
	    nivelExercicioFisico.add("1-3 exercícios por semana");
	    nivelExercicioFisico.add("3-5 exercícios por semana");
	    nivelExercicioFisico.add("Exercita todos os dias");
	    nivelExercicioFisico.add("Profissional");
	    ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nivelExercicioFisico);
	    arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    final Spinner spinnerPraticaMetas= (Spinner) findViewById(R.id.spinnerPraticaMeta);
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
					
					int selectedRadioId = ((RadioGroup) findViewById(R.id.radioGroup1)).getCheckedRadioButtonId();
					final RadioButton radioButton = (RadioButton) findViewById(selectedRadioId);
					
					WeightLoss metaWeightLoss = new WeightLoss(Double.parseDouble(pesoAtual.getText().toString()), valorPesoPerdaMetas, radioButton.getText().toString(), valorAlturaMetas, nivelExercicioFisico.get(itemSpinnerSelecionado), valorIdadeMetas);
					Builder dialog = Utils.showMessage(MetasActivity.this, Message.CONFIRM, "Meta estimada", metaWeightLoss.toString());
					dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int which) {
			            }
			        });
					dialog.show();
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