package br.edu.ufcg.agendamento;

public class ItemAgendamento {
	
	private String texto;
    private int iconeId;

    public ItemAgendamento() {
    }

    public ItemAgendamento(String texto, int iconeId) {
        this.texto = texto;
        this.iconeId = iconeId;
    }

    public int getIconeId() {
        return iconeId;
    }

    public void setIconeId(int iconeId) {
        this.iconeId = iconeId;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}