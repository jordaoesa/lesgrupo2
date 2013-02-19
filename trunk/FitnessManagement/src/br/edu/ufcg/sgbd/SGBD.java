package br.edu.ufcg.sgbd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.edu.ufcg.Aluno.Aluno;
import br.edu.ufcg.Aluno.Dados;

public class SGBD {
	
	private static Integer id = 0;
	private static List<Aluno> alunos = new ArrayList<Aluno>();
	private static HashMap<Integer, List<Dados>> dados = new HashMap<Integer, List<Dados>>();
	
	public static void addAluno(Aluno aluno) {
		if(aluno == null)
			System.out.println("erro");
		else{
			aluno.setId(id++);
			alunos.add(aluno);
		}
	}
	
	public static List<Aluno> getAlunos() {
		return alunos;
	}
	
	public static String[] getOnlyNamesOfAlunos() {
		String returnArray[] = new String[alunos.size()];
		for(int i=0; i<alunos.size(); i++){
			returnArray[i] = alunos.get(i).getNome();
		}
		return returnArray;
	}
	public static Integer[] getOnlyIdsOfAlunos(){
		Integer returnArray[] = new Integer[alunos.size()];
		for(int i=0; i<alunos.size(); i++){
			returnArray[i] = alunos.get(i).getId();
		}
		return returnArray;
	}
	
	public static void addDados(Integer id, Dados d) {
		if(d == null)
			System.out.println("erro");
		else{
			if(dados.get(id) == null){
				dados.put(id, new ArrayList<Dados>());
			}
			dados.get(id).add(d);
		}
	}
	
	public static List<Dados> getDadosFromUser(Integer id){
		return dados.get(id);
	}

}
