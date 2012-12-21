package com.ufcg.facade;

import java.util.List;

import android.content.ContentValues;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ufcg.disciplinas.Disciplina;
import com.ufcg.disciplinas.PlanoCurso;
import com.ufcg.exception.DataBaseException;
import com.ufcg.sgbd.SGBD;

public class DisciplinaFacade {
	private SGBD sgbd;

	public DisciplinaFacade(SGBD banco){
		this.sgbd = banco;
	}
	public void salvarDisciplina(LinearLayout form)throws DataBaseException{

		TextView disciplinas;
		for (int i = 0; i < form.getChildCount(); i++) {
			disciplinas = (TextView) form.getChildAt(i);
			insertDisciplinaBD(PlanoCurso.getInstance().getObrigatorias(disciplinas.getText().toString()));
		}
	}
	private void insertDisciplinaBD(Disciplina obrigatorias) throws DataBaseException {
		ContentValues valores = new ContentValues();

		System.out.println("cursando>> " + obrigatorias.isCursando());
		valores.put("nome",obrigatorias.getNome());
		valores.put("semestre", obrigatorias.getSemestre());
		valores.put("cursando", obrigatorias.isCursando());
		valores.put("cursada",obrigatorias.isCursada());
		valores.put("creditos",obrigatorias.getCreditos());

		this.sgbd.insertValues("disciplinas", valores);

	}

	public void getDisciplinaSalvas(){
		try {
			List<String> planoCursoSalvo = sgbd.queryREFATORED(new String[] { "nome", "semestre", "cursando", "cursada","KEY_disciplina", "creditos" }, "disciplinas");
			if(!planoCursoSalvo.isEmpty()){
				PlanoCurso.getInstance().getObrigatorias().clear();
				Disciplina disciplina;
				String[] infoDisciplina;
				for (String disciplinas : planoCursoSalvo) {
					System.out.println(">>FULL DISCIPLINA BD: " + disciplinas);
					infoDisciplina = disciplinas.split("#");
					disciplina = new Disciplina(infoDisciplina[0], Integer.parseInt(infoDisciplina[5]));
					disciplina.setSemestre(Integer.parseInt(infoDisciplina[1]));
					if(infoDisciplina[2].equals("0")){
						System.out.println(disciplina.getNome() +"<cursando false>" );
						disciplina.setCursando(false);
					} if(infoDisciplina[2].equals("1")){
						System.out.println(disciplina.getNome() +"<cursando true>" );
						disciplina.setCursando(true);
					} if(infoDisciplina[3].equals("0")){
						System.out.println(disciplina.getNome() +"<cursada false>" );
						disciplina.setCursada(false);
					} if(infoDisciplina[3].equals("1")){
						System.out.println(disciplina.getNome() +"<cursada true>" );
						disciplina.setCursada(true);
					}
					PlanoCurso.getInstance().addObrigatorias(disciplina);
				}
			}
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
	}

}
