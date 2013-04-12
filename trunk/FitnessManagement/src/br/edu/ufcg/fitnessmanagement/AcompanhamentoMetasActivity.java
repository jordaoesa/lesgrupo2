package br.edu.ufcg.fitnessmanagement;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import br.edu.ufcg.fachada.MetasFachada;
import br.edu.ufcg.fitnessmanagement.R;
import br.edu.ufcg.metas.WeightLoss;
import br.edu.ufcg.util.FitnessManagementSingleton;
import br.edu.ufcg.util.Message;
import br.edu.ufcg.util.Utils;

public class AcompanhamentoMetasActivity  extends Activity {


	private int id_aluno;
	private MetasFachada metaFachada;
	private int itemSpinnerSelecionado = 0;
	private WeightLoss weightLoss;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acompanhamento_metas);
		id_aluno = getIntent().getIntExtra("id_aluno", -1);
		metaFachada = FitnessManagementSingleton.getMetaFachadaInstance();
		weightLoss = FitnessManagementSingleton.getMetaFachadaInstance().getMetaWeightLoss(id_aluno);
		showLayout();
	}

	private void showLayout() {
		
		final EditText caloriasIngeridas = (EditText)findViewById(R.id.editTextCaloriasIngeridasMeta);
		
		//adiciona os elementos no spinner
		final List<String> semanaAcompanhamento = new ArrayList<String>();
		for (int i = 0; i < weightLoss.getDiasPerderPeso() / 7; i++) {
			semanaAcompanhamento.add((i+1) + " ª semana");
		}
		ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,semanaAcompanhamento);
		arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		final Spinner spinnerSemanaAcompanhamento= (Spinner) findViewById(R.id.spinnerSemanaAcompanhamento);
		spinnerSemanaAcompanhamento.setAdapter(arrayAdapter1);
		spinnerSemanaAcompanhamento.setOnItemSelectedListener(new OnItemSelectedListener()
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
		
		Button bVoltar = (Button) findViewById(R.id.buttonVoltarAcompanhamentoActivityMetas);
		bVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		Button bSalvar = (Button) findViewById(R.id.buttonSalvarAcompanhamentoMeta);
		bSalvar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Builder dialogSalvar = Utils.showMessage(AcompanhamentoMetasActivity.this, Message.CONFIRM, "Atenção", "Deseja salvar?");
        		dialogSalvar.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) {
			        	metaFachada.salvarAcompanhamentoWeightLoss(id_aluno, semanaAcompanhamento.get(itemSpinnerSelecionado),caloriasIngeridas.getText().toString());
			        }
        		});
        		dialogSalvar.setNegativeButton("Não",null);
        		dialogSalvar.show();
			}
		});
	}
}
