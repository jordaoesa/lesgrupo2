package br.edu.ufcg.fitnessmanagement;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.view.SubMenu;
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

	
	
	
	
	
	private final int SAIR = 1;
	private final int AJUDA = 2;
	
	private final int historicoAgendamento=4;
	String ajuda = "Ajuda";
	String sair = "Sair";
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_main, menu);
//		return true;

		
		boolean r = super.onCreateOptionsMenu(menu);
		super.onCreateOptionsMenu(menu);
		//menu.add(0, AJUDA, 0, ajuda).setIcon(R.drawable.alert);
		menu.add(0, SAIR, 0, sair).setIcon(R.drawable.alert);
		
		
		SubMenu menuAjuda = menu.addSubMenu(ajuda);
		menuAjuda.add(0, historicoAgendamento, 0, "Historico Agendamento");
		
		return r;
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		switch(item.getItemId()){
		
		
		case SAIR: mensagemExibir("Sair", "Saindo"); finish();  break;
		case AJUDA: mensagemExibir("Ajuda", "Menu Ajuda"); break;
		case historicoAgendamento: mensagemExibir("Historico Agendamento", "Aqui temos uma lista com todas as atividades pendentes a serem resolvidas, ex: pagamento ou aula."); break;
		
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public void mensagemExibir(String titulo, String texto){
		AlertDialog.Builder mensagem = new AlertDialog.Builder(this);
		mensagem.setTitle(titulo);
		mensagem.setMessage(texto);
		mensagem.setNeutralButton("OK", null);
		mensagem.show();
	}
	
	
	

}