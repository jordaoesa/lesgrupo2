package br.edu.ufcg.metas;

public class AcompanhamentoWeightLoss {

	private  int idALuno;
	private  String semana;
	private  String totalCalorias;

	public AcompanhamentoWeightLoss(int idALuno, String semana, String totalCalorias){
		this.setIdALuno(idALuno);
		this.setSemana(semana);
		this.setTotalCalorias(totalCalorias);
		
	}

	public int getIdALuno() {
		return idALuno;
	}

	public void setIdALuno(int idALuno) {
		this.idALuno = idALuno;
	}

	public String getSemana() {
		return semana;
	}

	public void setSemana(String semana) {
		this.semana = semana;
	}

	public String getTotalCalorias() {
		return totalCalorias;
	}

	public void setTotalCalorias(String totalCalorias) {
		this.totalCalorias = totalCalorias;
	}
	
}
