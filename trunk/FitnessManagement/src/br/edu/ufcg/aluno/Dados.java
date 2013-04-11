package br.edu.ufcg.aluno;

import java.util.Date;

/**
 * @author jesa
 *
 */
public class Dados {
	
	private Double peso;
	private Double altura;
	private Double bracoER;
	private Double bracoEC;
	private Double antebracoE;
	private Double coxaE;
	private Double panturrilhaE;
	private Double bracoDR;
	private Double bracoDC;
	private Double antebracoD;
	private Double coxaD;
	private Double panturrilhaD;
	private Date data;
	
	public Dados() {
	}
	
	public Dados(Double peso, Double altura, Double bracoER, Double bracoEC,
			Double antebracoE, Double coxaE, Double panturrilhaE,
			Double bracoDR, Double bracoDC, Double antebracoD, Double coxaD,
			Double panturrilhaD, Date data) {
		this.peso = peso;
		this.altura = altura;
		this.bracoER = bracoER;
		this.bracoEC = bracoEC;
		this.antebracoE = antebracoE;
		this.coxaE = coxaE;
		this.panturrilhaE = panturrilhaE;
		this.bracoDR = bracoDR;
		this.bracoDC = bracoDC;
		this.antebracoD = antebracoD;
		this.coxaD = coxaD;
		this.panturrilhaD = panturrilhaD;
		this.data = data;
	}

	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public Double getAltura() {
		return altura;
	}
	public void setAltura(Double altura) {
		this.altura = altura;
	}
	public Double getBracoER() {
		return bracoER;
	}
	public void setBracoER(Double bracoER) {
		this.bracoER = bracoER;
	}
	public Double getBracoEC() {
		return bracoEC;
	}
	public void setBracoEC(Double bracoEC) {
		this.bracoEC = bracoEC;
	}
	public Double getAntebracoE() {
		return antebracoE;
	}
	public void setAntebracoE(Double antebracoE) {
		this.antebracoE = antebracoE;
	}
	public Double getCoxaE() {
		return coxaE;
	}
	public void setCoxaE(Double coxaE) {
		this.coxaE = coxaE;
	}
	public Double getPanturrilhaE() {
		return panturrilhaE;
	}
	public void setPanturrilhaE(Double panturrilhaE) {
		this.panturrilhaE = panturrilhaE;
	}
	public Double getBracoDR() {
		return bracoDR;
	}
	public void setBracoDR(Double bracoDR) {
		this.bracoDR = bracoDR;
	}
	public Double getBracoDC() {
		return bracoDC;
	}
	public void setBracoDC(Double bracoDC) {
		this.bracoDC = bracoDC;
	}
	public Double getAntebracoD() {
		return antebracoD;
	}
	public void setAntebracoD(Double antebracoD) {
		this.antebracoD = antebracoD;
	}
	public Double getCoxaD() {
		return coxaD;
	}
	public void setCoxaD(Double coxaD) {
		this.coxaD = coxaD;
	}
	public Double getPanturrilhaD() {
		return panturrilhaD;
	}
	public void setPanturrilhaD(Double panturrilhaD) {
		this.panturrilhaD = panturrilhaD;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
}
