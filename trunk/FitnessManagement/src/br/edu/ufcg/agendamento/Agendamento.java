package br.edu.ufcg.agendamento;

public class Agendamento {
	private Integer idAluno;
	private String diaPagamento;
	private String horarioTreino;
	private String lembrete;
	private AgendamentoType type;
	public Agendamento(){
	}
	public Agendamento(Integer idAluno, String diaPagamento, String horario, String lembrete){
		this.idAluno = idAluno;
		this.diaPagamento = diaPagamento;
		this.horarioTreino = horario;
		this.lembrete = lembrete;
	}
	public Agendamento(Integer id, String dateFullFormatter,
			AgendamentoType pagamento) {
		this.idAluno = id;
		this.diaPagamento = dateFullFormatter;
		this.setType(pagamento);
	}
	public Integer getIdAluno() {
		return idAluno;
	}
	public void setIdAluno(Integer idAluno) {
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
}
