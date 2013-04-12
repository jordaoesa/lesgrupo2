package br.edu.ufcg.agendamento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Agendamento {
	private int idAluno;
	private int id;
	private String diaPagamento;
	private String horarioTreino;
	private String lembrete;
	private AgendamentoType type;
	private String dateInMillis;
	private int diasAtrasados;
	public Agendamento(){
	}
	public Agendamento(int id,int idAluno, String dateFullFormatter,AgendamentoType pagamento,String timeInMillis) {
		this.id = id;
		this.idAluno = idAluno;
		this.diaPagamento = dateFullFormatter;
		this.setType(pagamento);
		this.setDateInMillis(timeInMillis);
	}
	public int getId(){
		return this.id;
	}
	public int getIdAluno() {
		return idAluno;
	}
	public void setIdAluno(int idAluno) {
		this.idAluno = idAluno;
	}
	public String getDiaPagamento() {
		return diaPagamento;
	}
	public void setDiaPagamento(String diaPagamento) {
		this.diaPagamento = diaPagamento;
	}
	public String getHorarioTreino() {
		return horarioTreino;
	}
	public void setHorarioTreino(String horarioTreino) {
		this.horarioTreino = horarioTreino;
	}
	public String getLembrete() {
		return lembrete;
	}
	public void setLembrete(String lembrete) {
		this.lembrete = lembrete;
	}
	public AgendamentoType getType() {
		return type;
	}
	public void setType(AgendamentoType type) {
		this.type = type;
	}
	public String getDateInMillis() {
		return dateInMillis;
	}
	public void setDateInMillis(String dateInMillis) {
		this.dateInMillis = dateInMillis;
	}
	public int getDiasAtrasados(){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy",new Locale("pt","br"));
		
		formatter.setLenient(false);
		Date hoje;
		try {
			hoje = formatter.parse(formatter.format(new Date()));// para teste formatter.parse("20/04/2013");
			Date diaPagamento = new Date(Long.parseLong(getDateInMillis()));
			long diferencaDias = (hoje.getTime() - diaPagamento.getTime());
			diasAtrasados = (int) ((diferencaDias + 60L * 60 * 1000) / (24L * 60 * 60 * 1000)) + 1;  
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return diasAtrasados;
	}
}
