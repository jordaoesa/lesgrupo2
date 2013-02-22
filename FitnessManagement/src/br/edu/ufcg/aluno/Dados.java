package br.edu.ufcg.aluno;

import java.util.Date;

public class Dados {
	
	private Double peso;
	private Double calorias;
	private Double tamanhoBraco;
	private Double tamanhoPerna;
	private Double imc;
	private Date data;
	
	public Dados() {
	}
	
	public Dados(Double peso, Double calorias, Double tamanhoBraco, Double tamanhoPerna, Double imc, Date data) {
		this.peso = peso;
		this.calorias = calorias;
		this.tamanhoBraco = tamanhoBraco;
		this.tamanhoPerna = tamanhoPerna;
		this.imc = imc;
		this.data = data;
	}
	
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public Double getCalorias() {
		return calorias;
	}
	public void setCalorias(Double calorias) {
		this.calorias = calorias;
	}
	public Double getTamanhoBraco() {
		return tamanhoBraco;
	}
	public void setTamanhoBraco(Double tamanhoBraco) {
		this.tamanhoBraco = tamanhoBraco;
	}
	public Double getTamanhoPerna() {
		return tamanhoPerna;
	}
	public void setTamanhoPerna(Double tamanhoPerna) {
		this.tamanhoPerna = tamanhoPerna;
	}
	public Double getImc() {
		return imc;
	}
	public void setImc(Double imc) {
		this.imc = imc;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "Peso: " + peso + "\nCalorias: + " + calorias + "\nBraço: " + tamanhoBraco + "\nPerna: " + tamanhoPerna + "\nIMC:" + imc;
	}
}
