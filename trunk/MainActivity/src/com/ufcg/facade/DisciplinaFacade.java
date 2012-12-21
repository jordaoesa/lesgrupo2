package com.ufcg.facade;

import java.util.List;

import android.content.ContentValues;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ufcg.disciplinas.Disciplina;
import com.ufcg.disciplinas.PlanoCurso;
import com.ufcg.disciplinas.PreRequisito;
import com.ufcg.exception.DataBaseException;
import com.ufcg.sgbd.SGBD;

public class DisciplinaFacade {
	private SGBD sgbd;

	public DisciplinaFacade(SGBD banco) throws DataBaseException{
		//TODO FAZER SINGLETON
		this.sgbd = banco;
		insertPreRequisitos();
		populaPreRequisitos();
	}
	private void populaPreRequisitos() throws DataBaseException {
		List<String> requisitoBD = sgbd.queryREFATORED(new String[] {"disciplina","requisito"}, "requisitos");
		String[] infoRequisitos;
		for (String requisitos : requisitoBD) {
			infoRequisitos = requisitos.split("#");
			System.out.println(" info requisitos >> " + infoRequisitos[0] + "< >" + infoRequisitos[1]);
			PreRequisito.getInstance().add(infoRequisitos[0], infoRequisitos[1]);
		}
	}
	public void salvarDisciplina(LinearLayout form)throws DataBaseException{
		System.out.println("counter registros: " + this.sgbd.getCountRegistros("disciplinas", new String[] {"nome"}));
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
	
	private void insertPreRequisitos() throws DataBaseException{
		ContentValues valores = new ContentValues();

		valores.put("disciplina","Programação II");
		valores.put("requisito", "Programação I");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Programação II");
		valores.put("requisito", "Laboratório de Programação I");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Programação II");
		valores.put("requisito", "Introdução à Computação");
		this.sgbd.insertValues("requisitos", valores);
		
		terceiroSemestre();
		quartoSemestre();
		quintoSemestre();
		sextoSemestre();
		setimoSemestre();
		oitavoSemestre();
	}
	private void quartoSemestre() throws DataBaseException {
		ContentValues valores = new ContentValues();
		valores.put("disciplina", "Organização e Arquitetura de Computadores I");
		valores.put("requisito", "Fundamentos de Física Moderna");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Organização e Arquitetura de Computadores I");
		valores.put("requisito", "Estrutura de Dados e Algoritmos");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Organização e Arquitetura de Computadores I");
		valores.put("requisito", "Laboratório de Estrutura de Dados e Algoritmos");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Laboratório de Organização e Arquitetura de Computadores");
		valores.put("requisito", "Fundamentos de Física Moderna");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Laboratório de Organização e Arquitetura de Computadores");
		valores.put("requisito", "Estrutura de Dados e Algoritmos");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Laboratório de Organização e Arquitetura de Computadores");
		valores.put("requisito", "Laboratório de Estrutura de Dados e Algoritmos");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Paradigmas de Linguagens de Programação");
		valores.put("requisito", "Laboratório de Estrutura de Dados e Algoritmos");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Paradigmas de Linguagens de Programação");
		valores.put("requisito", "Estrutura de Dados e Algoritmos");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Paradigmas de Linguagens de Programação");
		valores.put("requisito", "Teoria da Computação");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Métodos Estatísticos");
		valores.put("requisito", "Álgebra Linear");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Métodos Estatísticos");
		valores.put("requisito", "Probabilidade e Estatística");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Lógica Matemática");
		valores.put("requisito", "Teoria da Computação");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Sistemas de Informação I");
		valores.put("requisito", "Gerência da Informação");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Engenharia de Software I");
		valores.put("requisito", "Probabilidade e Estatística");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Engenharia de Software I");
		valores.put("requisito", "Programação II");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Engenharia de Software I");
		valores.put("requisito", "Laboratório de Programação II");
		this.sgbd.insertValues("requisitos", valores);
	}
	private void quintoSemestre() 	throws DataBaseException{
		ContentValues valores = new ContentValues();
		valores.put("disciplina", "Análise Técnicas de Algoritmos");
		valores.put("requisito", "Estrutura de Dados e Algoritmos");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Análise Técnicas de Algoritmos");
		valores.put("requisito", "Laboratório de Estrutura de Dados e Algoritmos");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Análise Técnicas de Algoritmos");
		valores.put("requisito", "Cálculo Diferencial e Integral II");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Análise Técnicas de Algoritmos");
		valores.put("requisito", "Lógica Matemática");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Rede de Computadores");
		valores.put("requisito", "Laboratório de Organização e Arquitetura de Computadores");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Rede de Computadores");
		valores.put("requisito", "Organização e Arquitetura de Computadores");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Compiladores");
		valores.put("requisito", "Organização e Arquitetura de Computadores");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Compiladores");
		valores.put("requisito", "Laboratório de Organização e Arquitetura de Computadores");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Compiladores");
		valores.put("requisito", "Paradigmas de Linguagens de Programação");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Banco de Dados I");
		valores.put("requisito", "Sistemas de Informação I");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Sistemas de Informação II");
		valores.put("requisito", "Sistemas de Informação I");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Laboratório de Engenharia de Software");
		valores.put("requisito", "Engenharia de Software");
		this.sgbd.insertValues("requisitos", valores);
		
	}
	
	private void sextoSemestre() throws DataBaseException {
		ContentValues valores = new ContentValues();
		valores.put("disciplina", "Sistemas Operacionais");
		valores.put("requisito", "Organização e Arquitetura de Computadores I");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Sistemas Operacionais");
		valores.put("requisito", "Laboratório de Organização e Arquitetura de Computadores I");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Interconexão de Redes de Computadores");
		valores.put("requisito", "Rede de Computadores");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Laboratório de Interconexão de Redes de Computadores");
		valores.put("requisito", "Rede de Computadores");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Inteligência Artificial I");
		valores.put("requisito", "Análise Técnicas de Algoritmos");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Inteligência Artificial I");
		valores.put("requisito", "Métodos Estatísticos");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Inteligência Artificial I");
		valores.put("requisito", "Paradigmas de Linguagens de Programação");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Banco de Dados II");
		valores.put("requisito", "Banco de Dados I");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Banco de Dados II");
		valores.put("requisito", "Sistemas de Informação II");
		this.sgbd.insertValues("requisitos", valores);
		
	}
	
	private void setimoSemestre()	throws DataBaseException {
		ContentValues valores = new ContentValues();
		valores.put("disciplina", "Métodos e Software Numéricos");
		valores.put("requisito", "Análise Técnica de Algoritmos");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Métodos e Software Numéricos");
		valores.put("requisito", "Álgebra Linear");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Avaliação de Desempenho de Sistemas Discretos");
		valores.put("requisito", "Probabilidade e Estatística");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Projeto em Computação I");
		valores.put("requisito", "Metodologia Científica");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Projeto em Computação I");
		valores.put("requisito", "Laboratório de Engenharia de Software");
		this.sgbd.insertValues("requisitos", valores);
		
	}
	
	private void oitavoSemestre()	throws DataBaseException {
		ContentValues valores = new ContentValues();
		valores.put("disciplina", "Projeto em Computação II");
		valores.put("requisito", "Projeto em Computação I");
		this.sgbd.insertValues("requisitos", valores);
	}
	private void terceiroSemestre()
			throws DataBaseException {
		ContentValues valores = new ContentValues();
		valores.put("disciplina", "Fundamentos de Física Moderna");
		valores.put("requisito", "Fundamentos de Física Clássica");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Fundamentos de Física Moderna");
		valores.put("requisito", "Cálculo Diferencial e Integral II");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Teoria da Computação");
		valores.put("requisito", "Teoria dos Grafos");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Teoria da Computação");
		valores.put("requisito", "Matemática Discreta");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Teoria da Computação");
		valores.put("requisito", "Introdução à Computação");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Estrutura de Dados e Algoritmos");
		valores.put("requisito", "Programação II");
		this.sgbd.insertValues("requisitos", valores);

		valores.put("disciplina", "Estrutura de Dados e Algoritmos");
		valores.put("requisito", "Laboratório de Programação II");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Estrutura de Dados e Algoritmos");
		valores.put("requisito", "Teoria dos Grafos");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Laboratório de Estrutura de Dados e Algoritmos");
		valores.put("requisito", "Teoria dos Grafos");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Laboratório de Estrutura de Dados e Algoritmos");
		valores.put("requisito", "Laboratório de Programação II");
		this.sgbd.insertValues("requisitos", valores);
		
		valores.put("disciplina", "Laboratório de Estrutura de Dados e Algoritmos");
		valores.put("requisito", "Programação II");
		this.sgbd.insertValues("requisitos", valores);
	}

}
