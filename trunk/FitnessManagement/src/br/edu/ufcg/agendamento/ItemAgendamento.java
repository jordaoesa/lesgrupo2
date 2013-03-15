package br.edu.ufcg.agendamento;

public class ItemAgendamento {

	private String texto;
	private String iconePath;
	private String name;

	public ItemAgendamento() {
	}

	public ItemAgendamento(String name,String texto, String imagePath) {
		this.name = name;
		this.texto = texto;
		this.iconePath = imagePath;
	}

	public String getName(){
		return this.name;
	}
	public String getImagePath() {
		return iconePath;
	}

	public void setImagePath(String iconeId) {
		this.iconePath = iconeId;
	}

	public String getInfo() {
		return texto;
	}

	public void setInfo(String texto) {
		this.texto = texto;
	}
}