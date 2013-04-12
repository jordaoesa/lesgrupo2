package br.edu.ufcg.fachada;

import android.content.ContentValues;
import android.database.Cursor;
import br.edu.ufcg.sgbd.DB;

public class AnamneseFachada {

	private final String TABLE_NAME = "TABLE_ANAMNESE"; 
	private DB db;
	
	public AnamneseFachada(DB db) {
		this.db = db;
	}
	
	public void adicionarAnamnese(Anamnese anamnese) {
		ContentValues values = new ContentValues();
		values.put("id_aluno", anamnese.getIdAluno());
		values.put("sedentario", anamnese.isSedentario());
		values.put("atividade", anamnese.getAtividade());
		values.put("tempo_parado", anamnese.getTempo_parado());
		values.put("atividade_gosta", anamnese.getAtividade_gosta());
		values.put("tempo_sono", anamnese.getTempo_sono());
		values.put("nivel_stress", anamnese.getNivel_stress());
		values.put("fuma", anamnese.isFuma());
		values.put("tempo_fumante", anamnese.getTempo_fumante());
		values.put("refeicoes_diarias", anamnese.getRefeicoes_diarias());
		values.put("prob_cardiacos", anamnese.isProb_cardiacos());
		values.put("anemia", anamnese.isAnemia());
		values.put("ansiedade", anamnese.isAnsiedade());
		values.put("prob_resp", anamnese.isProb_resp());
		values.put("colesterol", anamnese.isColesterol());
		values.put("depressao", anamnese.isDepressao());
		values.put("insonia", anamnese.isInsonia());
		values.put("diabetes", anamnese.isDiabetes());
		values.put("gastrite", anamnese.isGastrite());
		values.put("tireoide", anamnese.isTireoide());
		values.put("varizes", anamnese.isVarizes());
		values.put("tonturas", anamnese.isTonturas());
		values.put("outros_prob", anamnese.getOutros_prob());
		values.put("usa_medicamentos", anamnese.isUsa_medicamentos());
		values.put("qual_medicamento", anamnese.getQual_medicamento());
		values.put("fez_cirurgia", anamnese.isFez_cirurgia());
		values.put("qual_cirurgia", anamnese.getQual_cirurgia());
		values.put("tempo_cirurgia", anamnese.getTempo_cirurgia());
		values.put("sente_dores", anamnese.isSente_dores());
		values.put("hipertrofia", anamnese.isHipertrofia());
		values.put("condicionamento", anamnese.isCondicionamento());
		values.put("diminuir_gordura", anamnese.isDiminuir_gordura());
		values.put("resistencia", anamnese.isResistencia());
		values.put("enrijecimento", anamnese.isEnrijecimento());
		values.put("postura", anamnese.isPostura());
		values.put("frequencia_semanal", anamnese.getFrequencia_semanal());
		values.put("porcent_gordura", anamnese.getPorcent_gordura());
		values.put("peso", anamnese.getPeso());
		values.put("peso_massa_magra", anamnese.getPeso_massa_magra());
		
		db.insertValues(TABLE_NAME, values);
		db.close();
	}
	
	public AnamneseFachada.Anamnese getAnamneseDoAluno(Integer idAluno) {
		Anamnese anamnese = null;
		Cursor cursor = db.getReadableDatabase().query(TABLE_NAME, null, "id_aluno = " + idAluno, null, null, null, null);
		try{
			if(cursor != null){
				cursor.moveToFirst();
				anamnese = new Anamnese(cursor.getInt(0), cursor.getInt(1),
						cursor.getInt(2), cursor.getDouble(3),
						cursor.getDouble(4), cursor.getDouble(5),
						cursor.getString(6), cursor.getString(7),
						cursor.getString(8), cursor.getString(9),
						cursor.getString(10), cursor.getString(11),
						cursor.getString(12), cursor.getString(13),
						cursor.getString(14), cursor.getString(15),
						cursor.getString(16), cursor.getString(17),
						cursor.getInt(18) == 1, cursor.getInt(19) == 1,
						cursor.getInt(20) == 1, cursor.getInt(21) == 1,
						cursor.getInt(22) == 1, cursor.getInt(23) == 1,
						cursor.getInt(24) == 1, cursor.getInt(24) == 1,
						cursor.getInt(26) == 1, cursor.getInt(27) == 1,
						cursor.getInt(28) == 1, cursor.getInt(29) == 1,
						cursor.getInt(30) == 1, cursor.getInt(31) == 1,
						cursor.getInt(32) == 1, cursor.getInt(33) == 1,
						cursor.getInt(34) == 1, cursor.getInt(35) == 1,
						cursor.getInt(36) == 1, cursor.getInt(37) == 1,
						cursor.getInt(38) == 1, cursor.getInt(39) == 1,
						cursor.getInt(40) == 1);
			}
		}catch(Exception e){
			System.out.println(">>> " + e.getMessage());
		}finally{
			cursor.close();
		}
		return anamnese;
	}

	public class Anamnese{
		private Integer id;
		private Integer idAluno;
		private Integer refeicoes_diarias;
		private Double porcent_gordura;
		private Double peso;
		private Double peso_massa_magra;
		private String atividade;
		private String atividade_gosta;
		private String tempo_parado;
		private String tempo_sono;
		private String nivel_stress;
		private String tempo_fumante;
		private String outros_prob;
		private String quanto_tempo_medic;
		private String qual_medicamento;
		private String qual_cirurgia;
		private String tempo_cirurgia;
		private String frequencia_semanal;
		private boolean sedentario;
		private boolean fuma;
		private boolean prob_cardiacos;
		private boolean anemia;
		private boolean ansiedade;
		private boolean prob_resp;
		private boolean colesterol;
		private boolean depressao;
		private boolean insonia;
		private boolean diabetes;
		private boolean gastrite;
		private boolean tireoide;
		private boolean varizes;
		private boolean tonturas;
		private boolean usa_medicamentos;
		private boolean fez_cirurgia;
		private boolean sente_dores;
		private boolean hipertrofia;
		private boolean condicionamento;
		private boolean diminuir_gordura;
		private boolean resistencia;
		private boolean enrijecimento;
		private boolean postura;
		
		public Anamnese() {}
		
		public Anamnese(Integer idAluno, Integer refeicoes_diarias,
				Double porcent_gordura, Double peso, Double peso_massa_magra,
				String atividade, String atividade_gosta, String tempo_parado, String tempo_sono,
				String nivel_stress, String tempo_fumante, String outros_prob, String quanto_tempo_medic,
				String qual_medicamento, String qual_cirurgia,
				String tempo_cirurgia, String frequencia_semanal,
				boolean sedentario, boolean fuma, boolean prob_cardiacos,
				boolean anemia, boolean ansiedade, boolean prob_resp,
				boolean colesterol, boolean depressao, boolean insonia,
				boolean diabetes, boolean gastrite, boolean tireoide,
				boolean varizes, boolean tonturas, boolean usa_medicamentos,
				boolean fez_cirurgia, boolean sente_dores, boolean hipertrofia,
				boolean condicionamento, boolean diminuir_gordura,
				boolean resistencia, boolean enrijecimento, boolean postura) {
			this.idAluno = idAluno;
			this.refeicoes_diarias = refeicoes_diarias;
			this.porcent_gordura = porcent_gordura;
			this.peso = peso;
			this.peso_massa_magra = peso_massa_magra;
			this.atividade = atividade;
			this.atividade_gosta = atividade_gosta;
			this.tempo_parado = tempo_parado;
			this.tempo_sono = tempo_sono;
			this.nivel_stress = nivel_stress;
			this.tempo_fumante = tempo_fumante;
			this.outros_prob = outros_prob;
			this.quanto_tempo_medic = quanto_tempo_medic;
			this.qual_medicamento = qual_medicamento;
			this.qual_cirurgia = qual_cirurgia;
			this.tempo_cirurgia = tempo_cirurgia;
			this.frequencia_semanal = frequencia_semanal;
			this.sedentario = sedentario;
			this.fuma = fuma;
			this.prob_cardiacos = prob_cardiacos;
			this.anemia = anemia;
			this.ansiedade = ansiedade;
			this.prob_resp = prob_resp;
			this.colesterol = colesterol;
			this.depressao = depressao;
			this.insonia = insonia;
			this.diabetes = diabetes;
			this.gastrite = gastrite;
			this.tireoide = tireoide;
			this.varizes = varizes;
			this.tonturas = tonturas;
			this.usa_medicamentos = usa_medicamentos;
			this.fez_cirurgia = fez_cirurgia;
			this.sente_dores = sente_dores;
			this.hipertrofia = hipertrofia;
			this.condicionamento = condicionamento;
			this.diminuir_gordura = diminuir_gordura;
			this.resistencia = resistencia;
			this.enrijecimento = enrijecimento;
			this.postura = postura;
		}

		public Anamnese(Integer id, Integer idAluno,
				Integer refeicoes_diarias, Double porcent_gordura, Double peso,
				Double peso_massa_magra, String atividade, String atividade_gosta, String tempo_parado,
				String tempo_sono, String nivel_stress, String tempo_fumante,
				String outros_prob, String quanto_tempo_medic, String qual_medicamento, String qual_cirurgia,
				String tempo_cirurgia, String frequencia_semanal,
				boolean sedentario, boolean fuma, boolean prob_cardiacos,
				boolean anemia, boolean ansiedade, boolean prob_resp,
				boolean colesterol, boolean depressao, boolean insonia,
				boolean diabetes, boolean gastrite, boolean tireoide,
				boolean varizes, boolean tonturas, boolean usa_medicamentos,
				boolean fez_cirurgia, boolean sente_dores, boolean hipertrofia,
				boolean condicionamento, boolean diminuir_gordura,
				boolean resistencia, boolean enrijecimento, boolean postura) {
			this.id = id;
			this.idAluno = idAluno;
			this.refeicoes_diarias = refeicoes_diarias;
			this.porcent_gordura = porcent_gordura;
			this.peso = peso;
			this.peso_massa_magra = peso_massa_magra;
			this.atividade = atividade;
			this.atividade_gosta = atividade_gosta;
			this.tempo_parado = tempo_parado;
			this.tempo_sono = tempo_sono;
			this.nivel_stress = nivel_stress;
			this.tempo_fumante = tempo_fumante;
			this.outros_prob = outros_prob;
			this.quanto_tempo_medic = quanto_tempo_medic;
			this.qual_medicamento = qual_medicamento;
			this.qual_cirurgia = qual_cirurgia;
			this.tempo_cirurgia = tempo_cirurgia;
			this.frequencia_semanal = frequencia_semanal;
			this.sedentario = sedentario;
			this.fuma = fuma;
			this.prob_cardiacos = prob_cardiacos;
			this.anemia = anemia;
			this.ansiedade = ansiedade;
			this.prob_resp = prob_resp;
			this.colesterol = colesterol;
			this.depressao = depressao;
			this.insonia = insonia;
			this.diabetes = diabetes;
			this.gastrite = gastrite;
			this.tireoide = tireoide;
			this.varizes = varizes;
			this.tonturas = tonturas;
			this.usa_medicamentos = usa_medicamentos;
			this.fez_cirurgia = fez_cirurgia;
			this.sente_dores = sente_dores;
			this.hipertrofia = hipertrofia;
			this.condicionamento = condicionamento;
			this.diminuir_gordura = diminuir_gordura;
			this.resistencia = resistencia;
			this.enrijecimento = enrijecimento;
			this.postura = postura;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getIdAluno() {
			return idAluno;
		}

		public void setIdAluno(Integer idAluno) {
			this.idAluno = idAluno;
		}

		public Integer getRefeicoes_diarias() {
			return refeicoes_diarias;
		}

		public void setRefeicoes_diarias(Integer refeicoes_diarias) {
			this.refeicoes_diarias = refeicoes_diarias;
		}

		public Double getPorcent_gordura() {
			return porcent_gordura;
		}

		public void setPorcent_gordura(Double porcent_gordura) {
			this.porcent_gordura = porcent_gordura;
		}

		public Double getPeso() {
			return peso;
		}

		public void setPeso(Double peso) {
			this.peso = peso;
		}

		public Double getPeso_massa_magra() {
			return peso_massa_magra;
		}

		public void setPeso_massa_magra(Double peso_massa_magra) {
			this.peso_massa_magra = peso_massa_magra;
		}

		public String getAtividade() {
			return atividade;
		}

		public void setAtividade(String atividade) {
			this.atividade = atividade;
		}

		public String getTempo_parado() {
			return tempo_parado;
		}

		public void setTempo_parado(String tempo_parado) {
			this.tempo_parado = tempo_parado;
		}

		public String getTempo_sono() {
			return tempo_sono;
		}

		public void setTempo_sono(String tempo_sono) {
			this.tempo_sono = tempo_sono;
		}

		public String getNivel_stress() {
			return nivel_stress;
		}

		public void setNivel_stress(String nivel_stress) {
			this.nivel_stress = nivel_stress;
		}

		public String getTempo_fumante() {
			return tempo_fumante;
		}

		public void setTempo_fumante(String tempo_fumante) {
			this.tempo_fumante = tempo_fumante;
		}

		public String getOutros_prob() {
			return outros_prob;
		}

		public void setOutros_prob(String outros_prob) {
			this.outros_prob = outros_prob;
		}

		public String getQual_medicamento() {
			return qual_medicamento;
		}

		public void setQual_medicamento(String qual_medicamento) {
			this.qual_medicamento = qual_medicamento;
		}

		public String getQual_cirurgia() {
			return qual_cirurgia;
		}

		public void setQual_cirurgia(String qual_cirurgia) {
			this.qual_cirurgia = qual_cirurgia;
		}

		public String getTempo_cirurgia() {
			return tempo_cirurgia;
		}

		public void setTempo_cirurgia(String tempo_cirurgia) {
			this.tempo_cirurgia = tempo_cirurgia;
		}

		public String getFrequencia_semanal() {
			return frequencia_semanal;
		}

		public void setFrequencia_semanal(String frequencia_semanal) {
			this.frequencia_semanal = frequencia_semanal;
		}

		public boolean isSedentario() {
			return sedentario;
		}

		public void setSedentario(boolean sedentario) {
			this.sedentario = sedentario;
		}

		public boolean isFuma() {
			return fuma;
		}

		public void setFuma(boolean fuma) {
			this.fuma = fuma;
		}

		public boolean isProb_cardiacos() {
			return prob_cardiacos;
		}

		public void setProb_cardiacos(boolean prob_cardiacos) {
			this.prob_cardiacos = prob_cardiacos;
		}

		public boolean isAnemia() {
			return anemia;
		}

		public void setAnemia(boolean anemia) {
			this.anemia = anemia;
		}

		public boolean isAnsiedade() {
			return ansiedade;
		}

		public void setAnsiedade(boolean ansiedade) {
			this.ansiedade = ansiedade;
		}

		public boolean isProb_resp() {
			return prob_resp;
		}

		public void setProb_resp(boolean prob_resp) {
			this.prob_resp = prob_resp;
		}

		public boolean isColesterol() {
			return colesterol;
		}

		public void setColesterol(boolean colesterol) {
			this.colesterol = colesterol;
		}

		public boolean isDepressao() {
			return depressao;
		}

		public void setDepressao(boolean depressao) {
			this.depressao = depressao;
		}

		public boolean isInsonia() {
			return insonia;
		}

		public void setInsonia(boolean insonia) {
			this.insonia = insonia;
		}

		public boolean isDiabetes() {
			return diabetes;
		}

		public void setDiabetes(boolean diabetes) {
			this.diabetes = diabetes;
		}

		public boolean isGastrite() {
			return gastrite;
		}

		public void setGastrite(boolean gastrite) {
			this.gastrite = gastrite;
		}

		public boolean isTireoide() {
			return tireoide;
		}

		public void setTireoide(boolean tireoide) {
			this.tireoide = tireoide;
		}

		public boolean isVarizes() {
			return varizes;
		}

		public void setVarizes(boolean varizes) {
			this.varizes = varizes;
		}

		public boolean isTonturas() {
			return tonturas;
		}

		public void setTonturas(boolean tonturas) {
			this.tonturas = tonturas;
		}

		public boolean isUsa_medicamentos() {
			return usa_medicamentos;
		}

		public void setUsa_medicamentos(boolean usa_medicamentos) {
			this.usa_medicamentos = usa_medicamentos;
		}

		public boolean isFez_cirurgia() {
			return fez_cirurgia;
		}

		public void setFez_cirurgia(boolean fez_cirurgia) {
			this.fez_cirurgia = fez_cirurgia;
		}

		public boolean isSente_dores() {
			return sente_dores;
		}

		public void setSente_dores(boolean sente_dores) {
			this.sente_dores = sente_dores;
		}

		public boolean isHipertrofia() {
			return hipertrofia;
		}

		public void setHipertrofia(boolean hipertrofia) {
			this.hipertrofia = hipertrofia;
		}

		public boolean isCondicionamento() {
			return condicionamento;
		}

		public void setCondicionamento(boolean condicionamento) {
			this.condicionamento = condicionamento;
		}

		public boolean isDiminuir_gordura() {
			return diminuir_gordura;
		}

		public void setDiminuir_gordura(boolean diminuir_gordura) {
			this.diminuir_gordura = diminuir_gordura;
		}

		public boolean isResistencia() {
			return resistencia;
		}

		public void setResistencia(boolean resistencia) {
			this.resistencia = resistencia;
		}

		public boolean isEnrijecimento() {
			return enrijecimento;
		}

		public void setEnrijecimento(boolean enrijecimento) {
			this.enrijecimento = enrijecimento;
		}

		public boolean isPostura() {
			return postura;
		}

		public void setPostura(boolean postura) {
			this.postura = postura;
		}

		public String getAtividade_gosta() {
			return atividade_gosta;
		}

		public void setAtividade_gosta(String atividade_gosta) {
			this.atividade_gosta = atividade_gosta;
		}

		public String getQuanto_tempo_medic() {
			return quanto_tempo_medic;
		}

		public void setQuanto_tempo_medic(String quanto_tempo_medic) {
			this.quanto_tempo_medic = quanto_tempo_medic;
		}

		@Override
		public String toString() {
			return "Anamnese [id=" + id + ", idAluno=" + idAluno
					+ ", refeicoes_diarias=" + refeicoes_diarias
					+ ", porcent_gordura=" + porcent_gordura + ", peso=" + peso
					+ ", peso_massa_magra=" + peso_massa_magra + ", atividade="
					+ atividade + ", atividade_gosta=" + atividade_gosta
					+ ", tempo_parado=" + tempo_parado + ", tempo_sono="
					+ tempo_sono + ", nivel_stress=" + nivel_stress
					+ ", tempo_fumante=" + tempo_fumante + ", outros_prob="
					+ outros_prob + ", quanto_tempo_medic="
					+ quanto_tempo_medic + ", qual_medicamento="
					+ qual_medicamento + ", qual_cirurgia=" + qual_cirurgia
					+ ", tempo_cirurgia=" + tempo_cirurgia
					+ ", frequencia_semanal=" + frequencia_semanal
					+ ", sedentario=" + sedentario + ", fuma=" + fuma
					+ ", prob_cardiacos=" + prob_cardiacos + ", anemia="
					+ anemia + ", ansiedade=" + ansiedade + ", prob_resp="
					+ prob_resp + ", colesterol=" + colesterol + ", depressao="
					+ depressao + ", insonia=" + insonia + ", diabetes="
					+ diabetes + ", gastrite=" + gastrite + ", tireoide="
					+ tireoide + ", varizes=" + varizes + ", tonturas="
					+ tonturas + ", usa_medicamentos=" + usa_medicamentos
					+ ", fez_cirurgia=" + fez_cirurgia + ", sente_dores="
					+ sente_dores + ", hipertrofia=" + hipertrofia
					+ ", condicionamento=" + condicionamento
					+ ", diminuir_gordura=" + diminuir_gordura
					+ ", resistencia=" + resistencia + ", enrijecimento="
					+ enrijecimento + ", postura=" + postura + "]";
		}

	}
	
}
