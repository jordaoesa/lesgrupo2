package br.edu.ufcg.agendamento;

public enum AgendamentoType {
	TREINO("Treino"),
	PAGAMENTO("Pagamento");

	private final String value;

	AgendamentoType(String value){
		this.value = value;
	}

	public String value(){
		return value;
	}
}
