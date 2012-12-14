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
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ufcg.disciplinas.Disciplina;
import com.ufcg.disciplinas.PlanoCurso;
import com.ufcg.util.Message;
import com.ufcg.util.Utils;


public class MainActivity extends Activity {
	private GridLayout grid;
	private ImageView image;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		//mostraPlanoCurso();
		menuInicial();

	}
	private void menuInicial(){
		setContentView(R.layout.menu_login);
		ImageButton buttonEntrar = (ImageButton) findViewById(R.id.buttonMenuLoginEntrar);
		buttonEntrar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mostraPlanoCurso();
			}
		});
	}
	private void mostraPlanoCurso() {
		setContentView(R.layout.activity_main);

		grid = (GridLayout) findViewById(R.id.gridlayout);

		List<String> primeiroSemestre = new ArrayList<String>();
		primeiroSemestre.add("Programação I");
		primeiroSemestre.add("Laboratório de Programação I");
		primeiroSemestre.add("Cálculo Diferencial e Integral I");
		primeiroSemestre.add("Algebra Vetorial e Geometria Analítica");
		primeiroSemestre.add("Introdução à Computação");
		primeiroSemestre.add("Leitura e Produção de Texto");

		LinearLayout semestre1 = (LinearLayout) findViewById(R.id.semestre1);
		preencheSemestre(semestre1,primeiroSemestre,false);
		LinearLayout semestre2 = (LinearLayout)findViewById(R.id.semestre2);

		List<String> segundoSemestre = new ArrayList<String>();
		segundoSemestre.add("Teoria dos Grafos");
		segundoSemestre.add("Programação II");
		segundoSemestre.add("Laboratório de Programação II");
		segundoSemestre.add("Matemática Discreta");
		segundoSemestre.add("Fundamentos de Física Clássica");
		segundoSemestre.add("Cálculo Diferencial e Integral II");

		preencheSemestre(semestre2,segundoSemestre,true);

		semestre2.setOnDragListener(new MyDragListener());

		LinearLayout semestre3 = (LinearLayout)findViewById(R.id.semestre3);
		List<String> terceiroSemestre = new ArrayList<String>();
		terceiroSemestre.add("Teoria da Computação");
		preencheSemestre(semestre3, terceiroSemestre, true);
		semestre3.setOnDragListener(new MyDragListener());
		
		LinearLayout semestre4 = (LinearLayout)findViewById(R.id.semestre4);
		List<String> quartoSemestre = new ArrayList<String>();
		quartoSemestre.add("Sistemas de Informação");
		preencheSemestre(semestre4, quartoSemestre, true);
		semestre4.setOnDragListener(new MyDragListener());
		
		LinearLayout semestre5 = (LinearLayout)findViewById(R.id.semestre5);
		semestre5.setOnDragListener(new MyDragListener());
		LinearLayout semestre6 = (LinearLayout)findViewById(R.id.semestre6);
		semestre6.setOnDragListener(new MyDragListener());
		LinearLayout semestre7 = (LinearLayout)findViewById(R.id.semestre7);
		semestre7.setOnDragListener(new MyDragListener());
		LinearLayout semestre8 = (LinearLayout)findViewById(R.id.semestre8);
		semestre8.setOnDragListener(new MyDragListener());
	}

	private void preencheSemestre(LinearLayout semestre1, List<String> disciplinas, boolean onTouch) {
		
		for (String disciplinaNome : disciplinas) {
			final TextView disciplina = new TextView(this);
			disciplina.setText(disciplinaNome);
			disciplina.setGravity(Gravity.CENTER);
			disciplina.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_disciplina));
			disciplina.setWidth(160);
			disciplina.setHeight(40);

			semestre1.addView(disciplina);
			if(onTouch){
				disciplina.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						System.out.println(" @@@@@@@@@@@@@@@@@@@@@@@@@@@@ ");
						AlertDialog.Builder caixa = new AlertDialog.Builder(MainActivity.this);
						caixa.setTitle("Opções");
						final CharSequence[] opcoes = { "Cursada", "Não cursada", "Cursando" };
						caixa.setSingleChoiceItems(opcoes, 0, new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int item) {
								Toast.makeText(getBaseContext(), opcoes[item], Toast.LENGTH_LONG).show();
								if(opcoes[item].equals("Cursada")){
									disciplina.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_disciplina_2));
								}else if(opcoes[item].equals("Não cursada")){
									disciplina.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_disciplina_3));
								}else{
									disciplina.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_disciplina_4));
								}
								
							}
						} );
						caixa.create();
						caixa.show();
					}
				});
				disciplina.setOnTouchListener(new MyTouchListener());
			}
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
	private void init(){
		PlanoCurso plano = PlanoCurso.getInstance();
		plano.addObrigatorias("Teoria dos Grafos");
		plano.addObrigatorias("Teoria da Computação");
		
		plano.getObrigatorias("Teoria da Computação").addPreRequisito("Teoria dos Grafos");
		
		plano.addObrigatorias("Programação I");
		plano.addObrigatorias("Programação II");
		plano.getObrigatorias("Programação II").addPreRequisito("Programação I");
		plano.addObrigatorias("Laboratório de Programação II");
		plano.getObrigatorias("Laboratório de Programação II").addPreRequisito("Laboratório de Programação I");
		plano.addObrigatorias("Matemática Discreta");
		plano.addObrigatorias("Fundamentos de Física Clássica");
		plano.addObrigatorias("Cálculo Diferencial e Integral II");
		plano.addObrigatorias("Gerência da Informação");
		plano.addObrigatorias("Sistemas de Informação I");
		plano.getObrigatorias("Sistemas de Informação I").addPreRequisito("Gerência da Informação");
		
	}
}