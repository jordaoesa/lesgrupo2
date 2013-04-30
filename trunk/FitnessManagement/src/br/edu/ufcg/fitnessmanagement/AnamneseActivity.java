package br.edu.ufcg.fitnessmanagement;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import br.edu.ufcg.fachada.AnamneseFachada.Anamnese;
import br.edu.ufcg.fachada.FinancasFachada;
import br.edu.ufcg.util.FitnessManagementSingleton;

public class AnamneseActivity extends Activity {
	
	private Anamnese anamnese;
	private Integer idAluno;
	private final int VOLTAR = 1;
	private final int AJUDA = 2;
	private final int avaliacao = 3;
	private String ajuda = "Ajuda";
	private String voltar = "Voltar";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_anamnese_historico_geral);
		setTitle("Anamnese");
		
		idAluno = getIntent().getIntExtra("id_aluno", -1);
		anamnese = FitnessManagementSingleton.getAnamneseFachadaInstance().getAnamneseDoAluno(idAluno);
		menuDeOpcoes();
		//menuExpandableListView();
	}

	/*private void menuExpandableListView() {
		
		ExpandableListView elv = (ExpandableListView) findViewById(R.id.expandableListViewAnamnese);
		List<ParentListView> parentsAndChildsIDS = initParentAndChildIDS();
		ExpandableListViewAdapterAnamnese expListViewAdapterAnamnese = new ExpandableListViewAdapterAnamnese(getApplicationContext(), parentsAndChildsIDS);
		elv.setAdapter(expListViewAdapterAnamnese);
	}

	private List<ParentListView> initParentAndChildIDS() {
		List<ParentListView> list = new ArrayList<AnamneseActivity.ParentListView>();
		ParentListView p = new ParentListView();
		p.setId("textViewPAI1");
		p.addChild("editTextchild1_1");
		p.addChild("radioButtonchild1_2");
		list.add(p);
		p = new ParentListView();
		p.setId("textViewPAI2");
		p.addChild("editTextchild2_1");
		p.addChild("radioButtonchild2_2");
		list.add(p);
		return list;
	}
	
	public class ParentListView {
		private String id;
		private List<String> childsIds;
		public ParentListView() {
			childsIds = new ArrayList<String>();
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public List<String> getChildsIds() {
			return childsIds;
		}
		public void addChild(String child) {
			this.childsIds.add(child);
		}
	}*/

	private void menuDeOpcoes() {
		final Button buttonAvancar1 = (Button) findViewById(R.id.buttonAvancarAnamneseHistoricoGeral);
		final RadioGroup p1rg = (RadioGroup) findViewById(R.id.radioGroupPergunta1Anamnese);
		final EditText p2et = (EditText) findViewById(R.id.editTextPergunta2Anamnese);
		final EditText p3et = (EditText) findViewById(R.id.editTextPergunta3Anamnese);
		final EditText p4et = (EditText) findViewById(R.id.editTextPergunta4Anamnese);
		final EditText p5et = (EditText) findViewById(R.id.editTextPergunta5Anamnese);
		final RadioGroup p6rg = (RadioGroup) findViewById(R.id.radioGroupPergunta6Anamnese);
		final RadioGroup p7rg = (RadioGroup) findViewById(R.id.radioGroupPergunta7Anamnese);
		final EditText p8et = (EditText) findViewById(R.id.editTextPergunta8Anamnese);
		final EditText p9et = (EditText) findViewById(R.id.editTextPergunta9Anamnese);
		
		if(anamnese != null){
			if(anamnese.isSedentario()) p1rg.check(R.id.radioButtonSimPergunta1Anamnese);
			else if(!anamnese.isSedentario()) p1rg.check(R.id.radioButtonNaoPergunta1Anamnese);
			p2et.setText(anamnese.getAtividade());
			p3et.setText(anamnese.getTempo_parado());
			p4et.setText(anamnese.getAtividade_gosta());
			p5et.setText(anamnese.getTempo_sono());
			if(anamnese.getNivel_stress() != null && anamnese.getNivel_stress().equals("baixo")) p6rg.check(R.id.radioButtonBaixoPergunta6Anamnese);
			else if(anamnese.getNivel_stress() != null && anamnese.getNivel_stress().equals("moderado")) p6rg.check(R.id.radioButtonModeradoPergunta6Anamnese);
			else if(anamnese.getNivel_stress() != null && anamnese.getNivel_stress().equals("alto")) p6rg.check(R.id.radioButtonAltoPergunta6Anamnese);
			if(anamnese.isFuma()) p7rg.check(R.id.radioButtonSimPergunta7Anamnese);
			else if(!anamnese.isFuma()) p7rg.check(R.id.radioButtonNaoPergunta7Anamnese);
			p8et.setText(anamnese.getTempo_fumante());
			p9et.setText(anamnese.getRefeicoes_diarias().toString());
			
			p1rg.setClickable(false);
			p2et.setClickable(false);
			p3et.setClickable(false); 
			p4et.setClickable(false);
			p5et.setClickable(false); 
			p6rg.setClickable(false);
			p7rg.setClickable(false);
			p8et.setClickable(false); 
			p9et.setClickable(false);
			
			p1rg.setEnabled(false);
			p1rg.getChildAt(0).setEnabled(false);
			p1rg.getChildAt(1).setEnabled(false);
			p2et.setEnabled(false);
			p3et.setEnabled(false); 
			p4et.setEnabled(false);
			p5et.setEnabled(false); 
			p6rg.setEnabled(false);
			p6rg.getChildAt(0).setEnabled(false);
			p6rg.getChildAt(1).setEnabled(false);
			p6rg.getChildAt(2).setEnabled(false);
			p7rg.setEnabled(false);
			p7rg.getChildAt(0).setEnabled(false);
			p7rg.getChildAt(1).setEnabled(false);
			p8et.setEnabled(false); 
			p9et.setEnabled(false); 
		}
		
		buttonAvancar1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				if(!verificaDados1(p1rg, p2et, p3et, p4et, p5et, p6rg, p7rg, p8et, p9et)){
					Toast.makeText(getApplicationContext(), "Dados Inválidos", Toast.LENGTH_SHORT).show();
				}else{
					setContentView(R.layout.activity_anamnese_historico_de_saude);
					
					final Button buttonAvancar2 = (Button) findViewById(R.id.buttonAvancarAnamneseHistoricoDeSaude);
					final CheckBox p10cb1 = (CheckBox) findViewById(R.id.checkBox1Pergunta10Anamnese);
					final CheckBox p10cb2 = (CheckBox) findViewById(R.id.checkBox2Pergunta10Anamnese);
					final CheckBox p10cb3 = (CheckBox) findViewById(R.id.checkBox3Pergunta10Anamnese);
					final CheckBox p10cb4 = (CheckBox) findViewById(R.id.checkBox4Pergunta10Anamnese);
					final CheckBox p10cb5 = (CheckBox) findViewById(R.id.checkBox5Pergunta10Anamnese);
					final CheckBox p10cb6 = (CheckBox) findViewById(R.id.checkBox6Pergunta10Anamnese);
					final CheckBox p10cb7 = (CheckBox) findViewById(R.id.checkBox7Pergunta10Anamnese);
					final CheckBox p10cb8 = (CheckBox) findViewById(R.id.checkBox8Pergunta10Anamnese);
					final CheckBox p10cb9 = (CheckBox) findViewById(R.id.checkBox9Pergunta10Anamnese);
					final CheckBox p10cb10 = (CheckBox) findViewById(R.id.checkBox10Pergunta10Anamnese);
					final CheckBox p10cb11 = (CheckBox) findViewById(R.id.checkBox11Pergunta10Anamnese);
					final CheckBox p10cb12 = (CheckBox) findViewById(R.id.checkBox12Pergunta10Anamnese);
					final EditText p10et = (EditText) findViewById(R.id.editTextPergunta10Anamnese);
					
					if(anamnese != null){
						p10cb1.setChecked(anamnese.isProb_cardiacos());
						p10cb2.setChecked(anamnese.isAnemia());
						p10cb3.setChecked(anamnese.isAnsiedade());
						p10cb4.setChecked(anamnese.isProb_resp());
						p10cb5.setChecked(anamnese.isColesterol());
						p10cb6.setChecked(anamnese.isDepressao());
						p10cb7.setChecked(anamnese.isInsonia());
						p10cb8.setChecked(anamnese.isDiabetes());
						p10cb9.setChecked(anamnese.isGastrite());
						p10cb10.setChecked(anamnese.isTireoide());
						p10cb11.setChecked(anamnese.isVarizes());
						p10cb12.setChecked(anamnese.isTonturas());
						p10et.setText(anamnese.getOutros_prob());
						
						p10cb1.setClickable(false); 
						p10cb2.setClickable(false); 
						p10cb3.setClickable(false); 
						p10cb4.setClickable(false); 
						p10cb5.setClickable(false);
						p10cb6.setClickable(false);
						p10cb7.setClickable(false);
						p10cb8.setClickable(false);
						p10cb9.setClickable(false);
						p10cb10.setClickable(false);
						p10cb11.setClickable(false);
						p10cb12.setClickable(false);
						p10et.setClickable(false);
						
						p10cb1.setEnabled(false); 
						p10cb2.setEnabled(false); 
						p10cb3.setEnabled(false); 
						p10cb4.setEnabled(false); 
						p10cb5.setEnabled(false);
						p10cb6.setEnabled(false);
						p10cb7.setEnabled(false);
						p10cb8.setEnabled(false);
						p10cb9.setEnabled(false);
						p10cb10.setEnabled(false);
						p10cb11.setEnabled(false);
						p10cb12.setEnabled(false);
						p10et.setEnabled(false);
					}
					
					buttonAvancar2.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View arg0) {
							
							if(!verificaDados2(p10cb1,p10cb2,p10cb3,p10cb4,p10cb5,p10cb6,p10cb7,p10cb8,p10cb9,p10cb10,p10cb11,p10cb12,p10et)){
								Toast.makeText(getApplicationContext(), "Dados Inválidos", Toast.LENGTH_SHORT).show();
							}else{
								setContentView(R.layout.activity_anamnese_historico_de_medicamentos);
								
								final Button buttonAvancar3 = (Button) findViewById(R.id.buttonAvancarAnamneseHistoricoDeMedicamentos);
								final RadioGroup p11rg = (RadioGroup) findViewById(R.id.radioGroupPergunta11Anamnese);
								final EditText p12et = (EditText) findViewById(R.id.editTextPergunta12Anamnese);
								final EditText p13et = (EditText) findViewById(R.id.editTextPergunta13Anamnese);
								final RadioGroup p14rg = (RadioGroup) findViewById(R.id.radioGroupPergunta14Anamnese);
								final EditText p15et = (EditText) findViewById(R.id.editTextPergunta15Anamnese);
								final EditText p16et = (EditText) findViewById(R.id.editTextPergunta16Anamnese);
								final RadioGroup p17rg = (RadioGroup) findViewById(R.id.radioGroupPergunta17Anamnese);
								
								if(anamnese != null){
									if(anamnese.isUsa_medicamentos()) p11rg.check(R.id.radioButtonSimPergunta11Anamnese);
									else if(!anamnese.isUsa_medicamentos()) p11rg.check(R.id.radioButtonNaoPergunta11Anamnese);
									p12et.setText(anamnese.getQuanto_tempo_medic());
									p13et.setText(anamnese.getQual_medicamento());
									if(anamnese.isFez_cirurgia()) p14rg.check(R.id.radioButtonSimPergunta14Anamnese);
									else if(!anamnese.isFez_cirurgia()) p14rg.check(R.id.radioButtonSimPergunta14Anamnese);
									p15et.setText(anamnese.getQual_cirurgia());
									p16et.setText(anamnese.getTempo_cirurgia());
									if(anamnese.isSente_dores()) p17rg.check(R.id.radioButtonSimPergunta17Anamnese);
									if(!anamnese.isSente_dores()) p17rg.check(R.id.radioButtonNaoPergunta17Anamnese);
									
									p11rg.setClickable(false);
									p12et.setClickable(false);
									p13et.setClickable(false);
									p14rg.setClickable(false);
									p15et.setClickable(false);
									p16et.setClickable(false);
									p17rg.setClickable(false);
									
									p11rg.setEnabled(false);
									p11rg.getChildAt(0).setEnabled(false);
									p11rg.getChildAt(1).setEnabled(false);
									p12et.setEnabled(false);
									p13et.setEnabled(false);
									p14rg.setEnabled(false);
									p14rg.getChildAt(0).setEnabled(false);
									p14rg.getChildAt(1).setEnabled(false);
									p15et.setEnabled(false);
									p16et.setEnabled(false);
									p17rg.setEnabled(false);
									p17rg.getChildAt(0).setEnabled(false);
									p17rg.getChildAt(1).setEnabled(false);
								}
								
								buttonAvancar3.setOnClickListener(new OnClickListener() {
									@Override
									public void onClick(View arg0) {
										
										if(!verificaDados3(p11rg,p12et,p13et,p14rg,p15et,p16et,p17rg)){
											Toast.makeText(getApplicationContext(), "Dados Inválidos", Toast.LENGTH_SHORT).show();
										}else{
											setContentView(R.layout.activity_anamnese_objetivos);
											
											final Button buttonEncerrar = (Button) findViewById(R.id.buttonEncerrarAnamneseObjetivos);
											final CheckBox p18cb1 = (CheckBox) findViewById(R.id.checkBox1Pergunta18Anamnese);
											final CheckBox p18cb2 = (CheckBox) findViewById(R.id.checkBox2Pergunta18Anamnese);
											final CheckBox p18cb3 = (CheckBox) findViewById(R.id.checkBox3Pergunta18Anamnese);
											final CheckBox p18cb4 = (CheckBox) findViewById(R.id.checkBox4Pergunta18Anamnese);
											final CheckBox p18cb5 = (CheckBox) findViewById(R.id.checkBox5Pergunta18Anamnese);
											final CheckBox p18cb6 = (CheckBox) findViewById(R.id.checkBox6Pergunta18Anamnese);
											final EditText p18et1 = (EditText) findViewById(R.id.editText1Pergunta18Anamnese);
											final EditText p18et2 = (EditText) findViewById(R.id.editText2Pergunta18Anamnese);
											final EditText p18et3 = (EditText) findViewById(R.id.editText3Pergunta18Anamnese);
											final EditText p18et4 = (EditText) findViewById(R.id.editText4Pergunta18Anamnese);
											
											if(anamnese != null){
												p18cb1.setChecked(anamnese.isHipertrofia());
												p18cb2.setChecked(anamnese.isCondicionamento());
												p18cb3.setChecked(anamnese.isDiminuir_gordura());
												p18cb4.setChecked(anamnese.isResistencia());
												p18cb5.setChecked(anamnese.isEnrijecimento());
												p18cb6.setChecked(anamnese.isPostura());
												p18et1.setText(anamnese.getFrequencia_semanal());
												p18et2.setText(anamnese.getPorcent_gordura().toString());
												p18et3.setText(anamnese.getPeso().toString());
												p18et4.setText(anamnese.getPeso_massa_magra().toString());
												
												p18cb1.setClickable(false);
												p18cb2.setClickable(false);
												p18cb3.setClickable(false);
												p18cb4.setClickable(false);
												p18cb5.setClickable(false);
												p18cb6.setClickable(false);
												p18et1.setClickable(false);
												p18et2.setClickable(false);
												p18et3.setClickable(false);
												p18et4.setClickable(false);
												buttonEncerrar.setClickable(false);
												
												p18cb1.setEnabled(false);
												p18cb2.setEnabled(false);
												p18cb3.setEnabled(false);
												p18cb4.setEnabled(false);
												p18cb5.setEnabled(false);
												p18cb6.setEnabled(false);
												p18et1.setEnabled(false);
												p18et2.setEnabled(false);
												p18et3.setEnabled(false);
												p18et4.setEnabled(false);
												buttonEncerrar.setEnabled(false);
											}else{
												buttonEncerrar.setOnClickListener(new OnClickListener() {
													@Override
													public void onClick(View arg0) {
														buttonEncerrar.setClickable(false);
														
														anamnese = FitnessManagementSingleton.getAnamneseFachadaInstance().new Anamnese();
														
														if(!verificaDados4(p18cb1,p18cb2,p18cb3,p18cb4,p18cb5,p18cb6,p18et1,p18et2,p18et3,p18et4)){
															Toast.makeText(getApplicationContext(), "Dados Inválidos", Toast.LENGTH_SHORT).show();
														}else{
															getDadosFornecidosPeloUsuario(p1rg, p2et, p3et, p4et, p5et,
																	p6rg, p7rg, p8et, p9et, p10cb1, p10cb2, p10cb3,
																	p10cb4, p10cb5, p10cb6, p10cb7, p10cb8, p10cb9,
																	p10cb10, p10cb11, p10cb12, p10et, p11rg, p12et,
																	p13et, p14rg, p15et, p16et, p17rg, p18cb1, p18cb2,
																	p18cb3, p18cb4, p18cb5, p18cb6, p18et1, p18et2,
																	p18et3, p18et4);
															
															FitnessManagementSingleton.getAnamneseFachadaInstance().adicionarAnamnese(anamnese);
															Toast.makeText(getApplicationContext(), "Anamnese Armazenada", Toast.LENGTH_SHORT).show();
														}
													}
													
													private boolean verificaDados4(
															CheckBox p18cb1,
															CheckBox p18cb2,
															CheckBox p18cb3,
															CheckBox p18cb4,
															CheckBox p18cb5,
															CheckBox p18cb6,
															EditText p18et1,
															EditText p18et2,
															EditText p18et3,
															EditText p18et4) {
														if(p18et1 == null || p18et1.getText().toString().equals("")){
															return false;
														}else if(p18et2 == null || p18et2.getText().toString().equals("")){
															return false;
														}else if(p18et3 == null || p18et3.getText().toString().equals("")){
															return false;
														}if(p18et4 == null || p18et4.getText().toString().equals("")){
															return false;
														}
														return true;
													}

													private void getDadosFornecidosPeloUsuario(
															final RadioGroup p1rg, final EditText p2et,
															final EditText p3et, final EditText p4et,
															final EditText p5et, final RadioGroup p6rg,
															final RadioGroup p7rg, final EditText p8et,
															final EditText p9et, final CheckBox p10cb1,
															final CheckBox p10cb2, final CheckBox p10cb3,
															final CheckBox p10cb4, final CheckBox p10cb5,
															final CheckBox p10cb6, final CheckBox p10cb7,
															final CheckBox p10cb8, final CheckBox p10cb9,
															final CheckBox p10cb10, final CheckBox p10cb11,
															final CheckBox p10cb12, final EditText p10et,
															final RadioGroup p11rg, final EditText p12et,
															final EditText p13et, final RadioGroup p14rg,
															final EditText p15et, final EditText p16et,
															final RadioGroup p17rg, final CheckBox p18cb1,
															final CheckBox p18cb2, final CheckBox p18cb3,
															final CheckBox p18cb4, final CheckBox p18cb5,
															final CheckBox p18cb6, final EditText p18et1,
															final EditText p18et2, final EditText p18et3,
															final EditText p18et4) {
														anamnese.setIdAluno(idAluno);
														if(p1rg.getCheckedRadioButtonId() == R.id.radioButtonSimPergunta1Anamnese) anamnese.setSedentario(true);
														else if(p1rg.getCheckedRadioButtonId() == R.id.radioButtonNaoPergunta1Anamnese) anamnese.setSedentario(false);
														anamnese.setAtividade(p2et.getText().toString());
														anamnese.setTempo_parado(p3et.getText().toString());
														anamnese.setAtividade_gosta(p4et.getText().toString());
														anamnese.setTempo_sono(p5et.getText().toString());
														if(p6rg.getCheckedRadioButtonId() == R.id.radioButtonBaixoPergunta6Anamnese) anamnese.setNivel_stress("baixo");
														else if(p6rg.getCheckedRadioButtonId() == R.id.radioButtonModeradoPergunta6Anamnese) anamnese.setNivel_stress("moderado");
														else if(p6rg.getCheckedRadioButtonId() == R.id.radioButtonAltoPergunta6Anamnese) anamnese.setNivel_stress("alto");
														if(p7rg.getCheckedRadioButtonId() == R.id.radioButtonSimPergunta7Anamnese) anamnese.setFuma(true);
														else if(p7rg.getCheckedRadioButtonId() == R.id.radioButtonNaoPergunta7Anamnese) anamnese.setFuma(false);
														anamnese.setTempo_fumante(p8et.getText().toString());
														if(verificaDouble(p9et.getText())) anamnese.setRefeicoes_diarias(Integer.parseInt(p9et.getText().toString()));
														anamnese.setProb_cardiacos(p10cb1.isChecked());
														anamnese.setAnemia(p10cb2.isChecked());
														anamnese.setAnsiedade(p10cb3.isChecked());
														anamnese.setProb_resp(p10cb4.isChecked());
														anamnese.setColesterol(p10cb5.isChecked());
														anamnese.setDepressao(p10cb6.isChecked());
														anamnese.setInsonia(p10cb7.isChecked());
														anamnese.setDiabetes(p10cb8.isChecked());
														anamnese.setGastrite(p10cb9.isChecked());
														anamnese.setTireoide(p10cb10.isChecked());
														anamnese.setVarizes(p10cb11.isChecked());
														anamnese.setTonturas(p10cb12.isChecked());
														anamnese.setOutros_prob(p10et.getText().toString());
														if(p11rg.getCheckedRadioButtonId() == R.id.radioButtonSimPergunta11Anamnese) anamnese.setUsa_medicamentos(true);
														else if(p11rg.getCheckedRadioButtonId() == R.id.radioButtonNaoPergunta11Anamnese) anamnese.setUsa_medicamentos(false);
														anamnese.setQuanto_tempo_medic(p12et.getText().toString());
														anamnese.setQual_medicamento(p13et.getText().toString());
														if(p14rg.getCheckedRadioButtonId() == R.id.radioButtonSimPergunta14Anamnese) anamnese.setFez_cirurgia(true);
														else if(p14rg.getCheckedRadioButtonId() == R.id.radioButtonNaoPergunta14Anamnese) anamnese.setFez_cirurgia(false);
														anamnese.setQual_cirurgia(p15et.getText().toString());
														anamnese.setTempo_cirurgia(p16et.getText().toString());
														if(p17rg.getCheckedRadioButtonId() == R.id.radioButtonSimPergunta17Anamnese) anamnese.setSente_dores(true);
														else if(p17rg.getCheckedRadioButtonId() == R.id.radioButtonNaoPergunta17Anamnese) anamnese.setSente_dores(false);
														anamnese.setHipertrofia(p18cb1.isChecked());
														anamnese.setCondicionamento(p18cb2.isChecked());
														anamnese.setDiminuir_gordura(p18cb3.isChecked());
														anamnese.setResistencia(p18cb4.isChecked());
														anamnese.setEnrijecimento(p18cb5.isChecked());
														anamnese.setPostura(p18cb6.isChecked());
														anamnese.setFrequencia_semanal(p18et1.getText().toString());
														if(verificaDouble(p18et2.getText())) anamnese.setPorcent_gordura(Double.parseDouble(p18et2.getText().toString()));
														if(verificaDouble(p18et3.getText())) anamnese.setPeso(Double.parseDouble(p18et3.getText().toString()));
														if(verificaDouble(p18et4.getText())) anamnese.setPeso_massa_magra(Double.parseDouble(p18et4.getText().toString()));
													}
												});
											}
										}
									}

									private boolean verificaDados3(
											RadioGroup p11rg, EditText p12et,
											EditText p13et, RadioGroup p14rg,
											EditText p15et, EditText p16et,
											RadioGroup p17rg) {
										if(p11rg == null || p11rg.getCheckedRadioButtonId() == -1){
											return false;
										}else if(p14rg == null || p14rg.getCheckedRadioButtonId() == -1){
											return false;
										}else if(p17rg == null || p17rg.getCheckedRadioButtonId() == -1){
											return false;
										}
										return true;
									}
								});
							}
						}

						private boolean verificaDados2(CheckBox p10cb1,
								CheckBox p10cb2, CheckBox p10cb3,
								CheckBox p10cb4, CheckBox p10cb5,
								CheckBox p10cb6, CheckBox p10cb7,
								CheckBox p10cb8, CheckBox p10cb9,
								CheckBox p10cb10, CheckBox p10cb11,
								CheckBox p10cb12, EditText p10et) {
							return true;
						}
					});
				}
			}

			private boolean verificaDados1(RadioGroup p1rg, EditText p2et,
					EditText p3et, EditText p4et, EditText p5et,
					RadioGroup p6rg, RadioGroup p7rg, EditText p8et,
					EditText p9et) {
				if(p3et == null || p3et.getText().toString().equals("")){
					return false;
				}else if(p4et == null || p4et.getText().toString().equals("")){
					return false;
				}else if(p5et == null || p5et.getText().toString().equals("")){
					return false;
				}else if(p9et == null || p9et.getText().toString().equals("")){
					return false;
				}else if(p1rg == null || p1rg.getCheckedRadioButtonId() == -1){
					return false;
				}else if(p6rg == null || p6rg.getCheckedRadioButtonId() == -1){
					return false;
				}else if(p7rg == null || p7rg.getCheckedRadioButtonId() == -1){
					return false;
				}
				return true;
			}
		});
		
		
	}

	private boolean verificaDouble(Editable editable) {
		try {
			Double.parseDouble(editable.toString());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean r = super.onCreateOptionsMenu(menu);
		super.onCreateOptionsMenu(menu);
		menu.add(0, VOLTAR, 0, voltar).setIcon(R.drawable.back);

		SubMenu menuAjuda = menu.addSubMenu(ajuda);
		menuAjuda.setIcon(R.drawable.help);
		menuAjuda.add(0, avaliacao, 0, "Avaliacao");
		
		return r;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		switch(item.getItemId()){
		case VOLTAR:
			mensagemExibir("Voltar", "Voltando",R.drawable.back);
			finish();
			break;
		case AJUDA:
			mensagemExibir("Ajuda", "Avaliacao",R.drawable.help);
			break;
		case avaliacao:
			mensagemExibir("Avaliacao", "Podemos visualizar Anamnese, Dados estatisticos e fazer uma avaliacao do aluno.",R.drawable.help);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void mensagemExibir(String titulo, String texto,int icon){
		AlertDialog.Builder mensagem = new AlertDialog.Builder(this);
		mensagem.setTitle(titulo);
		mensagem.setIcon(icon);
		mensagem.setMessage(texto);
		mensagem.setNeutralButton("OK", null);
		mensagem.show();
	}

}
