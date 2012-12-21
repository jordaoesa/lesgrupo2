package com.ufcg;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ufcg.disciplinas.Disciplina;
import com.ufcg.disciplinas.PlanoCurso;
import com.ufcg.exception.DataBaseException;
import com.ufcg.facade.DisciplinaFacade;
import com.ufcg.facade.UsuarioFacade;
import com.ufcg.sgbd.SGBD;
import com.ufcg.util.ImageAdapter;
import com.ufcg.util.Message;
import com.ufcg.util.Utils;


public class MainActivity extends Activity {
	
	private GridLayout grid;
	private ImageView image;
	private SGBD sgbd;
	private UsuarioFacade usuarioFacade;
	private DisciplinaFacade disciplinasFacade;
	private GridView gridMenuInicial;

	public static final String[] itensMenuInicial = {"Novo","Visualizar","Ajuda","Sair"};
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		sgbd = new SGBD(this);
		usuarioFacade = new UsuarioFacade(sgbd);
		disciplinasFacade = new DisciplinaFacade(sgbd);


		//mostraPlanoCurso();
		menuInicial();

	}
	private void menuInicial(){
		setContentView(R.layout.menu_inicial_2);
		setTitle("Plano de curso");
		
		gridMenuInicial = (GridView) findViewById(R.id.gridViewMenuIniciar);
		gridMenuInicial.setAdapter(new ImageAdapter(this, itensMenuInicial));
		
		gridMenuInicial.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				if(itensMenuInicial[position].equals("Novo")){
					PlanoCurso.getInstance().getObrigatorias().clear();
					planoDeCursoDefault();
					mostraPlanoCurso();
				}else if(itensMenuInicial[position].equals("Visualizar")){
					disciplinasFacade.getDisciplinaSalvas();
					mostraPlanoCurso();
				}else if(itensMenuInicial[position].equals("Ajuda")){
					//System.out.println("ajuda");
					mostrarAjuda();
				}else if(itensMenuInicial[position].equals("Sair")){
					finish();
				}
				
				
			}
		});

//		ImageButton buttonEntrar = (ImageButton) findViewById(R.id.buttonMenuLoginEntrar);
//		buttonEntrar.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View v) {
//				//mostraPlanoCurso();
//				for(String semestre: disciplinasFacade.getAllDisciplinas()){
//					System.out.println(">>>semestre: " + semestre);
//				}
//				//				EditText matricula = (EditText) findViewById(R.id.editTextMatricula);
//				//				System.out.println("#### matricula:" + matricula.getText().toString());
//				//				//usuarioFacade.cadastrarUsuario(matricula.getText().toString());
//				//				System.out.println("usuario : " + usuarioFacade.getAllMatriculas().get(0) );
//			}
//		});
	}
	private void mostrarAjuda() {
		setContentView(R.layout.help_activity);
		ImageButton voltar = (ImageButton) findViewById(R.id.voltarHelp);
		voltar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				menuInicial();
			}
		});
	}
	private void salvarPlanoCurso(){
		try {
			disciplinasFacade.salvarDisciplina((LinearLayout) findViewById(R.id.semestre1));
			disciplinasFacade.salvarDisciplina((LinearLayout) findViewById(R.id.semestre2));
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
	}
	private void mostraPlanoCurso() {
		setContentView(R.layout.activity_main);

		grid = (GridLayout) findViewById(R.id.gridlayout);

		populaPlanoDeCurso();
		LinearLayout semestre4 = (LinearLayout)findViewById(R.id.semestre4);
		List<String> quartoSemestre = new ArrayList<String>();
		quartoSemestre.add("Sistemas de Informação");
		semestre4.setOnDragListener(new MyDragListener());

		ImageButton botaoSalvar = (ImageButton) findViewById(R.id.buttonSalvar);
		botaoSalvar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				salvarPlanoCurso();
			}
		});

		ImageButton botaoVoltar = (ImageButton) findViewById(R.id.buttonVoltar);
		botaoVoltar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				menuInicial();
			}
		});


	}

	private void populaPlanoDeCurso() {
		List<Disciplina> todasDisciplinas = PlanoCurso.getInstance().getObrigatorias();
		for (Disciplina disciplina : todasDisciplinas) {
			if(disciplina.getSemestre() == 1){
				addDisciplinaLayout(disciplina,(LinearLayout) findViewById(R.id.semestre1),false);	
			}else if(disciplina.getSemestre() == 2){
				addDisciplinaLayout(disciplina,(LinearLayout) findViewById(R.id.semestre2),true);
			}
			else if(disciplina.getSemestre() == 3){
				addDisciplinaLayout(disciplina,(LinearLayout) findViewById(R.id.semestre3),true);
			}
		}

	}
	private void addDisciplinaLayout(final Disciplina disciplina, LinearLayout semestre, boolean onTouch){

		final TextView disciplinaTextView = new TextView(this);
		disciplinaTextView.setText(disciplina.getNome());
		disciplinaTextView.setGravity(Gravity.CENTER);
		if(disciplina.isCursada() && !disciplina.isCursando()){
			disciplinaTextView.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_disciplina_2));
		}else if(disciplina.isCursando() && !disciplina.isCursada()){
			disciplinaTextView.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_disciplina_4));
		}else if(!disciplina.isCursada()){
			disciplinaTextView.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_disciplina_3));
		}else if(!disciplina.isCursada() && !disciplina.isCursando()){
			disciplinaTextView.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_disciplina));
		}
		disciplinaTextView.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder caixa = new AlertDialog.Builder(MainActivity.this);
				caixa.setTitle("Opções");
				final CharSequence[] opcoes = { "Cursada", "Não cursada", "Cursando" };
				caixa.setSingleChoiceItems(opcoes, 0, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						Toast.makeText(getBaseContext(), opcoes[item], Toast.LENGTH_LONG).show();
						System.out.println("opcoes selecionada: " + opcoes[item]);
						if(opcoes[item].equals("Cursada")){
							PlanoCurso.getInstance().getObrigatorias(disciplina.getNome()).setCursada(true);
							disciplinaTextView.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_disciplina_2));
						}else if(opcoes[item].equals("Não cursada")){
							PlanoCurso.getInstance().getObrigatorias(disciplina.getNome()).setCursada(false);
							disciplinaTextView.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_disciplina_3));
						}else{
							PlanoCurso.getInstance().getObrigatorias(disciplina.getNome()).setCursando(true);
							disciplinaTextView.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_disciplina_4));
						}

					}
				} );
				caixa.create();
				caixa.show();
			}
		});

		semestre.addView(disciplinaTextView);
		if(onTouch){
			semestre.setOnDragListener(new MyDragListener());
			disciplinaTextView.setOnTouchListener(new MyTouchListener());
		}
	}
	private final class MyClickListener implements OnClickListener {

		public void onClick(View arg0) {
			System.out.println("######### click  click lick #############");
			//			
		}

	}

	private final class MyTouchListener implements OnTouchListener {

		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
				ClipData data = ClipData.newPlainText("", "");
				DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
						view);
				view.startDrag(data, shadowBuilder, view, 0);
				view.setVisibility(View.INVISIBLE);
				return true;
			} else {
				return false;
			}
		}
	}

	class MyDragListener implements OnDragListener {

		public MyDragListener(){
		}

		Drawable enterShape = getResources().getDrawable(
				R.drawable.shape_droptarget);
		Drawable normalShape = getResources().getDrawable(R.drawable.shape);

		public boolean onDrag(View v, DragEvent event) {
			View viewLocal = (View) event.getLocalState();
			int action = event.getAction();
			boolean containsDragable = true;
			Disciplina disciplinaTrocar = PlanoCurso.getInstance().getObrigatorias(((TextView)viewLocal).getText().toString());
			LinearLayout container = (LinearLayout) v;
			switch (event.getAction()) {
			case DragEvent.ACTION_DRAG_STARTED:
				break;
			case DragEvent.ACTION_DRAG_LOCATION:
				break;
			case DragEvent.ACTION_DRAG_ENTERED:
				v.setBackgroundDrawable(enterShape);
				containsDragable = true;
				break;
			case DragEvent.ACTION_DRAG_EXITED:
				v.setBackgroundDrawable(normalShape);
				containsDragable = false;
				break;
			case DragEvent.ACTION_DROP:
				if(containsDragable && !existePreRequisito(container, disciplinaTrocar)){
					ViewGroup owner = (ViewGroup) viewLocal.getParent();
					PlanoCurso.getInstance().setSemestreDisciplina(((TextView) viewLocal).getText().toString(), container.getTag().toString());
					System.out.println("container: " + container.getTag().toString());
					System.out.println("owner: " + owner.getTag().toString());
					System.out.println("viewLocal: " + ((TextView) viewLocal).getText());
					owner.removeView(viewLocal);
					viewLocal.setVisibility(View.VISIBLE);
					container.addView(viewLocal);
				}
				break;
			case DragEvent.ACTION_DRAG_ENDED:
				if (dropEventNotHandled(event) || existePreRequisito(container, disciplinaTrocar)) {
					Utils.showMessage(MainActivity.this, Message.ALERT,"Cuidado","Disciplina com pre-requisitos não pagos.");

					viewLocal.setVisibility(View.VISIBLE);
				}

				v.setBackgroundDrawable(normalShape);
			}
			return true;
		}
		private boolean existePreRequisito(LinearLayout container,Disciplina disciplinaTrocar) {
			PlanoCurso plano = PlanoCurso.getInstance();
			for (int i = 0; i < container.getChildCount(); i++) {
				Disciplina disciplina = plano.getObrigatorias(((TextView)container.getChildAt(i)).getText().toString());
				if(disciplina != null && disciplinaTrocar != null){
					if(disciplinaTrocar.getPreRequisitos().contains(disciplina) || disciplina.getPreRequisitos().contains(disciplinaTrocar)){
						return true;
					}
					//return disciplina.getPreRequisitos() != null ? disciplina.getPreRequisitos().contains(disciplinaTrocar) : true;
				}
			}
			return false;
		}

		private boolean dropEventNotHandled(DragEvent dragEvent) {
			return !dragEvent.getResult();
		}

	}
	private void planoDeCursoDefault(){
		PlanoCurso plano = PlanoCurso.getInstance();

		plano.addObrigatorias("Programação I",1);
		plano.addObrigatorias("Laboratório de Programação I",1);
		plano.addObrigatorias("Cálculo Diferencial e Integral I",1);
		plano.addObrigatorias("Algebra Vetorial e Geometria Analítica",1);
		plano.addObrigatorias("Introdução à Computação",1);
		plano.addObrigatorias("Leitura e Produção de Texto",1);

		plano.addObrigatorias("Cálculo Diferencial e Integral II",2);
		plano.addObrigatorias("Matemática Discreta",2);
		plano.addObrigatorias("Metodologia Científica",2);
		plano.addObrigatorias("Programação II",2);
		plano.addObrigatorias("Teoria dos Grafos",2);
		plano.addObrigatorias("Fundamentos de Física Clássica",2);
		plano.addObrigatorias("Laboratório de Programação II",2);

		plano.addObrigatorias("Álgebra Linear",3);
		plano.addObrigatorias("Probabilidade e Estatística",3);
		plano.addObrigatorias("Teoria da Computação",3);
		plano.addObrigatorias("Estruturas de Dados e Algoritmos",3);
		plano.addObrigatorias("Fundamentos de Física Moderna",3);
		plano.addObrigatorias("Gerência da Informação",3);
		plano.addObrigatorias("Laboratório de Estruturas de Dados e Algoritmos",3);

		plano.getObrigatorias("Teoria da Computação").addPreRequisito("Teoria dos Grafos");

		plano.getObrigatorias("Programação II").addPreRequisito("Programação I");
		plano.getObrigatorias("Laboratório de Programação II").addPreRequisito("Laboratório de Programação I");
		plano.addObrigatorias("Sistemas de Informação I");
		plano.getObrigatorias("Sistemas de Informação I").addPreRequisito("Gerência da Informação");

	}
}