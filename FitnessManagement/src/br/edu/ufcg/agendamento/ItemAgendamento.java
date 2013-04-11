package br.edu.ufcg.agendamento;

public class ItemAgendamento {

	private String texto;
	private String iconePath;
	private String name;
	private int colorTexto;

	public ItemAgendamento() {
	}

	public ItemAgendamento(String name,String texto, String imagePath, int colorTexto) {
		this.name = name;
		this.texto = texto;
		this.iconePath = imagePath;
		this.colorTexto = colorTexto;
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
	public int getColor(){
		return this.colorTexto;
	}
}