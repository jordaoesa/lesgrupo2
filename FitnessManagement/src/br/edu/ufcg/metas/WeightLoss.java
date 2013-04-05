package br.edu.ufcg.metas;

import java.text.DecimalFormat;

public class WeightLoss {
	private int idade;
	private int altura;
	private String genero;
	private double pesoPerder;
	private double BMR;
	private double caloriasManterPeso;
	private String nivelExercicio;
	private double pesoAtual;
	
	public WeightLoss(double pesoAtual, double pesoVaiPerder, String genero, int altura, String nivelExercicio, int idade){
		this.pesoAtual = pesoAtual;
		this.pesoPerder = pesoVaiPerder;
		this.genero = genero;
		this.altura = altura;
		this.nivelExercicio = nivelExercicio;
		this.idade = idade;
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
}
