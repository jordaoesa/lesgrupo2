package br.edu.ufcg.agendamento;

public class ItemAgendamento {

	private String texto;
	private String iconePath;
	private String name;
	private int colorTexto;
	private int idAgendamento;
	public ItemAgendamento() {
	}

	public ItemAgendamento(int id,String name,String texto, String imagePath, int colorTexto) {
		this.idAgendamento = id;
		this.name = name;
		this.texto = texto;
		this.iconePath = imagePath;
		this.colorTexto = colorTexto;
	}
	public int getId(){
		return this.idAgendamento;
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