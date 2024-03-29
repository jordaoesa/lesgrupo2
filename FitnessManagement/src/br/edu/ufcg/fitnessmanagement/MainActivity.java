package br.edu.ufcg.fitnessmanagement;

import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;
import br.edu.ufcg.aluno.Aluno;
import br.edu.ufcg.aluno.Dados;
import br.edu.ufcg.fachada.AlunoFachada;
import br.edu.ufcg.fachada.DadosFachada;
import br.edu.ufcg.sgbd.DB;
import br.edu.ufcg.util.FitnessManagementSingleton;

public class MainActivity extends Activity {

	private DB db;
	private AlunoFachada alunoFachada;
	private DadosFachada dadosFachada;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		FitnessManagementSingleton.setContext(this);
		db = FitnessManagementSingleton.getDBInstance();
		alunoFachada = FitnessManagementSingleton.getAlunoFachadaInstance();
		dadosFachada = FitnessManagementSingleton.getDadosFachadaInstance();

		menuInicial();
	}

	private void menuInicial() {
		setContentView(R.layout.activity_fitness_management);

		setTitle("Fitness Management");

		if(getIntent().getExtras() != null){
			//Caso clique na notificacao.Codigo pra cancelar o servico.
			NotificationManager nm = (NotificationManager) 
					getSystemService(NOTIFICATION_SERVICE);
	        nm.cancel(getIntent().getExtras().getInt("NotifID")); 
		}
		//ImageButton bCadastrar = (ImageButton) findViewById(R.id.buttonCadastrarAlunosMain) ;
		
		Button bCadastrar = (Button) findViewById(R.id.buttonCadastrarAlunosMain);
		
		Button bGerenciar = (Button) findViewById(R.id.buttonGerenciarAlunosMain);
		/*Button bAddAluno = (Button) findViewById(R.id.buttonAddAlunoMain);*/

//		Button buttonHelp = (Button) findViewById(R.id.buttonHelp);
//		final Intent intent = new Intent(getApplicationContext(),AjudaCadastroAluno.class);
//		buttonHelp.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//
//				startActivity(intent);
//			}
//		});
		
		
		
		
		
		final Intent intentGerenciar = new Intent(this, VisualizarAlunosActivity.class);

		bCadastrar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentCadastrarAluno = new Intent(getApplicationContext(), CadastrarAlunoActivity.class);
				startActivity(intentCadastrarAluno);
				
			}
		});
		bGerenciar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(intentGerenciar);
			}
		});

		/*bAddAluno.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				inserirUsuarioNoBanco();
			}
		});*/

	}

	/*private void inserirUsuarioNoBanco() {
		Aluno jordao = new Aluno("aluno", 20, "Campina", "Masculino", "(83) 9999-9999");

		Dados dados1Jordao = new Dados(75.0, 100.0, 31.0, 55.0, 20.0, new Date("01/01/2013"));
		Dados dados2Jordao = new Dados(76.0, 200.0, 32.0, 57.0, 21.0, new Date("02/01/2013"));
		Dados dados3Jordao = new Dados(77.0, 50.0, 33.0, 60.0, 21.0, new Date("03/01/2013"));

		Dados dados4Jordao = new Dados(78.0, 100.0, 34.0, 51.0, 20.0, new Date("04/01/2013"));
		Dados dados5Jordao = new Dados(79.0, 200.0, 35.0, 52.0, 21.0, new Date("05/01/2013"));
		Dados dados6Jordao = new Dados(82.0, 50.0, 36.0, 63.0, 21.0, new Date("06/01/2013"));

		Dados dados7Jordao = new Dados(85.0, 100.0, 37.0, 54.0, 20.0, new Date("07/01/2013"));
		Dados dados8Jordao = new Dados(89.0, 200.0, 38.0, 55.0, 21.0, new Date("08/01/2013"));
		Dados dados9Jordao = new Dados(97.0, 50.0, 39.0, 66.0, 21.0, new Date("09/01/2013"));

		alunoFachada.adicionarAluno(jordao);
		int id = alunoFachada.getIdUltimoAlunoAdicionado();

		dadosFachada.adicionaDadosDoAluno(id, dados1Jordao);
		dadosFachada.adicionaDadosDoAluno(id, dados2Jordao);
		dadosFachada.adicionaDadosDoAluno(id, dados3Jordao);
		dadosFachada.adicionaDadosDoAluno(id, dados4Jordao);
		dadosFachada.adicionaDadosDoAluno(id, dados5Jordao);
		dadosFachada.adicionaDadosDoAluno(id, dados6Jordao);
		dadosFachada.adicionaDadosDoAluno(id, dados7Jordao);
		dadosFachada.adicionaDadosDoAluno(id, dados8Jordao);
		dadosFachada.adicionaDadosDoAluno(id, dados9Jordao);

	}*/
	
	
	
	
	
	private final int SAIR = 1;
	private final int AJUDA = 2;
	
	private final int cadastrarAluno=3;
	private final int gerenciarAluno=4;
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
		menu.add(0, SAIR, 0, sair).setIcon(R.drawable.sair);
		
		
		SubMenu menuAjuda = menu.addSubMenu(ajuda);
		menuAjuda.setIcon(R.drawable.help);
		menuAjuda.add(0, cadastrarAluno, 0, "Cadastrar Aluno");
		menuAjuda.add(0, gerenciarAluno, 0, "Gerenciar Aluno");
		return r;
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		switch(item.getItemId()){
		
		
		case SAIR: mensagemExibir("Sair", "Saindo",R.drawable.sair); finish();  break;
		case AJUDA: mensagemExibir("Ajuda", "Menu Ajuda",R.drawable.help); break;
		case cadastrarAluno: mensagemExibir("Cadastrar Aluno", "Para cadastrar um aluno preencha os campos necessarios e tire uma foto para o perfil do aluno.",R.drawable.help); break;
		case gerenciarAluno: mensagemExibir("Gerenciar ALuno", "Ao clicar em Gerenciar Alunos sera exibida uma tela com todos os seus alunos cadastrados, selecione um aluno especifico e adicione mais detalhes a seu perfil.",R.drawable.help); break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public void mensagemExibir(String titulo, String texto, int icone){
		AlertDialog.Builder mensagem = new AlertDialog.Builder(this);
		mensagem.setTitle(titulo);
		mensagem.setIcon(icone);
		mensagem.setMessage(texto);
		mensagem.setNeutralButton("OK", null);
		mensagem.show();
	}

}
