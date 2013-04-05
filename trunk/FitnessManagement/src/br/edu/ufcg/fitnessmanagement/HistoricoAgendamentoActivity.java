package br.edu.ufcg.fitnessmanagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.edu.ufcg.agendamento.Agendamento;
import br.edu.ufcg.agendamento.ItemAgendamento;
import br.edu.ufcg.agendamento.ItemAgendamentoAdapter;
import br.edu.ufcg.aluno.Aluno;
import br.edu.ufcg.fachada.AgendamentoFachada;
import br.edu.ufcg.util.FitnessManagementSingleton;

public class HistoricoAgendamentoActivity extends Activity {

	private AgendamentoFachada agendamentoFachada;
	private List<Aluno> alunos;
	private ArrayList<ItemAgendamento> itens;
	private ItemAgendamentoAdapter adapterListView;
	private ListView listView;
	private String tipoAgendamento;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_visualizar_agendamento);
		setTitle("Histórico Agendamentos");
		tipoAgendamento = getIntent().getExtras().getString("tipo");
		this.agendamentoFachada = FitnessManagementSingleton.getAgendamentoFachadaInstance();
		this.alunos = FitnessManagementSingleton.getAlunoFachadaInstance().getAlunos();

		//Pega a referencia do ListView
		listView = (ListView) findViewById(R.id.listViewAgendamento);
		//Define o Listener quando alguem clicar no item.
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				//Pega o item que foi selecionado.
				ItemAgendamento item = adapterListView.getItem(arg2);
			}
		});
		createListView();
	}

	private void createListView() {
		itens = new ArrayList<ItemAgendamento>();
		for (Aluno aluno : alunos) {
			for (Agendamento agendamento : agendamentoFachada.getAgendamentoPorAluno(aluno.getId())) {
				if(agendamento.getType().value().equals(tipoAgendamento.trim())){
					if(isDateValid(agendamento.getDateInMillis())){
						itens.add(new ItemAgendamento(aluno.getNome(),agendamento.getDiaPagamento(),aluno.getCaminhoImagem()));
					}
				}
			}
		}

		//Cria o adapter
		adapterListView = new ItemAgendamentoAdapter(this, itens,getContentResolver());

		//Define o Adapter
		listView.setAdapter(adapterListView);
		//Cor quando a lista é selecionada para ralagem.
		listView.setCacheColorHint(Color.TRANSPARENT);

	}

	public boolean isDateValid(String date){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy",new Locale("pt","br"));
		formatter.setLenient(false);
		try {
			Date hoje = formatter.parse(formatter.format(new Date()));
			Date dateSelected = new Date(Long.parseLong(date));
			if(dateSelected.equals(hoje)){
				return true;
			}
			else if(dateSelected.before(Calendar.getInstance().getTime())){
				return false;
			}else if(dateSelected.after(Calendar.getInstance().getTime())){
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

}
