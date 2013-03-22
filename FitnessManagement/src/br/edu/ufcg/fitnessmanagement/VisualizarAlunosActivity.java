package br.edu.ufcg.fitnessmanagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.edu.ufcg.agendamento.Agendamento;
import br.edu.ufcg.agendamento.ItemAgendamento;
import br.edu.ufcg.agendamento.ItemAgendamentoAdapter;
import br.edu.ufcg.aluno.Aluno;
import br.edu.ufcg.fachada.AgendamentoFachada;
import br.edu.ufcg.fachada.AlunoFachada;
import br.edu.ufcg.util.FitnessManagementSingleton;
import br.edu.ufcg.util.ImageAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class VisualizarAlunosActivity extends Activity {

	private AlunoFachada alunoFachada;
	private ListView listView;
	private ItemAgendamentoAdapter adapterListView;
	private ArrayList<ItemAgendamento> itens;
	private List<Aluno> alunos;
	private AgendamentoFachada agendamentoFachada;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.alunoFachada = FitnessManagementSingleton.getAlunoFachadaInstance();
		this.agendamentoFachada = FitnessManagementSingleton.getAgendamentoFachadaInstance();
		this.alunos = alunoFachada.getAlunos();
		
		showListaAlunosCadastrados();
	}
	
	private void showListaAlunosCadastrados() {
		setTitle("Alunos Cadastrados");
		setContentView(R.layout.activity_visualizar_alunos);
		GridView grade = (GridView) findViewById(R.id.gridViewActivityVisualizar);
		grade.setAdapter(new ImageAdapter(this, alunos, getContentResolver()));
		grade.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				selecionaUsuario(position, alunos);
			}

		});

		Button voltar = (Button) findViewById(R.id.buttonVoltarAcivityVisualizar);
		voltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		Button buttonHistoricoAgendamento = (Button) findViewById(R.id.buttonHistoricoAgendamentoAcivity);
		buttonHistoricoAgendamento.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showHistoricoAgendamentos();
			}
		});

	}

	private void showHistoricoAgendamentos(){
		setContentView(R.layout.tela_visualizar_agendamento);
		setTitle("Histórico Agendamentos");
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
		Button buttonVoltar = (Button) findViewById(R.id.buttonVoltarItemHistoricoAgendamento);
		buttonVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showListaAlunosCadastrados();
			}
		});
		
		createListView();
	}
	private void createListView() {
		itens = new ArrayList<ItemAgendamento>();
		for (Aluno aluno : alunos) {
			for (Agendamento agendamento : agendamentoFachada.getAgendamentoPorAluno(aluno.getId())) {
				if(isDateValid(agendamento.getDateInMillis())){
					itens.add(new ItemAgendamento(aluno.getNome(),agendamento.getDiaPagamento(),aluno.getCaminhoImagem()));
				}
			}
		}
		
		System.out.println("itens  " + itens.size());
	     //Cria o adapter
        adapterListView = new ItemAgendamentoAdapter(this, itens,getContentResolver());
 
        //Define o Adapter
        listView.setAdapter(adapterListView);
        //Cor quando a lista é selecionada para ralagem.
        listView.setCacheColorHint(Color.TRANSPARENT);
        
	}

	private void selecionaUsuario(int position, List<Aluno> alunos) {
		finish();
		Intent activityFicha = new Intent(this, PerfilDoAlunoActivity.class);
		activityFicha.putExtra("id_aluno", alunos.get(position).getId());
		startActivity(activityFicha);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.visualizar_alunos, menu);
		return true;
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
