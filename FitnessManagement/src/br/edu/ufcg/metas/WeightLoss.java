package br.edu.ufcg.metas;

import java.text.DecimalFormat;

public class WeightLoss {
	private int idAluno;
	private int idade;
	private int altura;
	private String genero;
	private double pesoPerder;
	private double BMR;
	private double caloriasManterPeso;
	private double caloriasIngeridas;
	private String nivelExercicio;
	private double pesoAtual;
	
	public WeightLoss(String pesoAtual, String pesoVaiPerder, String genero, String altura, String nivelExercicio, String idade, int idAluno) throws Exception{
		verificaDados(pesoAtual,pesoVaiPerder,genero, altura, nivelExercicio, idade);
		this.pesoAtual = Double.parseDouble(pesoAtual);
		this.pesoPerder = Double.parseDouble(pesoVaiPerder);
		this.genero = genero;
		this.altura = Integer.parseInt(altura);
		this.nivelExercicio = nivelExercicio;
		this.idade = Integer.parseInt(idade);
		this.idAluno = idAluno;
	}
	private void verificaDados(String pesoAtual, String pesoVaiPerder,
			String genero, String altura, String nivelExercicio,
			String idade) throws Exception {
		System.out.println("peso atual: " + pesoAtual +"<> pesoavaiperder :" + pesoVaiPerder + "<> genero " + genero + "<> altura " + altura + "<> nivel "+ nivelExercicio
				+ "<> IDADE " + idade);
		if(!verificaNumMaiorQueZero(pesoAtual) || !verificaNumMaiorQueZero(pesoVaiPerder) || !verificaNumMaiorQueZero(altura) || !verificaNumMaiorQueZero(idade)){
			throw new Exception("Dados inválidos.");
		}
	}
	public double getPesoAtual() {
		return pesoAtual;
	}
	public int getIdade() {
		return idade;
	}
	public double getAltura() {
		return altura;
	}
	public String getGenero() {
		return genero;
	}
	public double getPesoPerder() {
		return pesoPerder;
	}
	public double getBMR() {
		if(getGenero().equals("Masculino")){
			BMR = 88.362 + (13.397 * getPesoAtual()) + (4.799 * getAltura()) - (5.677 * getIdade());
		}else if(getGenero().equals("Feminino")){
			BMR = 447.593 + (9.247 * getPesoAtual()) + (3.098 * getAltura()) - (4.330 * getIdade());
		}
		return BMR;
	}
	public double getCaloriasManterPeso(){
		if(getNivelExercicio().equals("Não pratica exercício")){
			caloriasManterPeso = getBMR() * 1.2;
		}else if(getNivelExercicio().equals("1-3 exercícios por semana")){
			caloriasManterPeso = getBMR() * 1.375;
		}else if(getNivelExercicio().equals("3-5 exercícios por semana")){
			caloriasManterPeso = getBMR() * 1.55;
		}else if(getNivelExercicio().equals("Exercita todos os dias")){
			caloriasManterPeso = getBMR() * 1.725;
		}else if(getNivelExercicio().equals("Profissional")){
			caloriasManterPeso = getBMR() * 1.9;
		}
		return caloriasManterPeso;
	}
	public String getNivelExercicio(){
		return nivelExercicio;
	}
	public String getCaloriasPerdePeso(){
		DecimalFormat formatter = new DecimalFormat("0.00");
		return formatter.format(getCaloriasManterPeso() - 500);
	}
	public int getDiasPerderPeso(){
		double pesoPerder = getPesoPerder();
		int dias = 0;
		while(pesoPerder >= 0){
			dias += 7;
			pesoPerder -= 0.45;
		}
		return dias;
	}
	
	public String toString(){
		return	"Dias necessários: " + getDiasPerderPeso() + ".\n" +
				"Calorias/dia: " + getCaloriasPerdePeso() +  ".";
	}
	
	private boolean verificaNumMaiorQueZero(String num) {
		try {
			double aux1 = Double.parseDouble(num);
			if( aux1 > 0)return true;
		} catch (Exception e) {
			System.out.println(" execeeeeeeeeeeeetption  " + e.getMessage());
			return false;
		}
		return false;
	}
	public int getIdALuno() {
		return idAluno;
	}
	public void setIdALuno(int idALuno) {
		this.idAluno = idALuno;
	}
	public double getCaloriasIdeaisPorSemana(){
		return Double.parseDouble(getCaloriasPerdePeso()) * 7;
	}
	
}
