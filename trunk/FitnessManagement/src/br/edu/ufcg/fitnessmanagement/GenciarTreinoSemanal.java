package br.edu.ufcg.fitnessmanagement;
 
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
 
public class GenciarTreinoSemanal extends Activity {
 
    @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_genciar_treino_semanal);
                setTitle("Gerenciar Treino Semanal");
                
                
                telaGerenciarTreinoSemanal();
        }
 
        private void telaGerenciarTreinoSemanal() {
                
                
                Button buttonSegunda = (Button) findViewById(R.id.botaoSegundaCS);
                Button buttonTerca = (Button) findViewById(R.id.botaoTercaCS);
                Button buttonQuarta = (Button) findViewById(R.id.botaoQuartaCS);
                Button buttonQuinta = (Button) findViewById(R.id.botaoQuintaCS);
                Button buttonSexta = (Button) findViewById(R.id.botaoSextaCS);
                Button buttonSabado = (Button) findViewById(R.id.botaoSabadoCS);
                Button buttonDomingo = (Button) findViewById(R.id.botaoDomingoCS);
                ImageButton backBotton = (ImageButton) findViewById(R.id.imgBtVoltar);
//                RelativeLayout rlTreinoSemanal = (RelativeLayout)findViewById(R.id.rlTreinoSemanal);
//                rlTreinoSemanal.setBackgroundColor(Color.DKGRAY);
                
                final Intent intent = new Intent(getApplicationContext(), GerenciadorTreinoDiarioActivity.class);
                final Intent intentB = new Intent(getApplicationContext(), PerfilDoAlunoActivity.class);
                buttonSegunda.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        	intent.putExtra("id_aluno", getIntent().getIntExtra("id_aluno", -1));
                    		intent.putExtra("diaSemana", "segunda");
                            startActivity(intent);
                        }
                });
                
                buttonTerca.setOnClickListener(new OnClickListener() {
                        
                        @Override
                        public void onClick(View v) {
                    		intent.putExtra("diaSemana", "terca");
                            startActivity(intent);
                        }
                });
                buttonQuarta.setOnClickListener(new OnClickListener() {
                        
                        @Override
                        public void onClick(View v) {
                        	intent.putExtra("diaSemana", "quarta");
                            startActivity(intent);
                        }
                });
                buttonQuinta.setOnClickListener(new OnClickListener() {
                        
                        @Override
                        public void onClick(View v) {
                    		intent.putExtra("diaSemana", "quinta");
                            startActivity(intent);
                        }
                });
                buttonSexta.setOnClickListener(new OnClickListener() {
                        
                        @Override
                        public void onClick(View v) {
                    		intent.putExtra("diaSemana", "sexta");
                            startActivity(intent);
                        }
                });
                buttonSabado.setOnClickListener(new OnClickListener() {
                        
                        @Override
                        public void onClick(View v) {
                    		intent.putExtra("diaSemana", "sabado");
                            startActivity(intent);
                        }
                });
                buttonDomingo.setOnClickListener(new OnClickListener() {
                        
                        @Override
                        public void onClick(View v) {
                    		intent.putExtra("diaSemana", "domingo");
                            startActivity(intent);
                        }
                });
                backBotton.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						finish();
					}
				});
                
        }
        
    	private final int SAIR = 1;
    	private final int AJUDA = 2;
    	
    	private final int gerenciarTreinoSemanal=3;
    	String ajuda = "Ajuda";
    	String sair = "Sair";
    	

    	@Override
    	public boolean onCreateOptionsMenu(Menu menu) {
//    		// Inflate the menu; this adds items to the action bar if it is present.
//    		getMenuInflater().inflate(R.menu.activity_main, menu);
//    		return true;

    		
    		boolean r = super.onCreateOptionsMenu(menu);
    		super.onCreateOptionsMenu(menu);
    		//menu.add(0, AJUDA, 0, ajuda).setIcon(R.drawable.alert);
    		menu.add(0, SAIR, 0, sair).setIcon(R.drawable.alert);
    		
    		
    		SubMenu menuAjuda = menu.addSubMenu(ajuda);
    		menuAjuda.add(0, gerenciarTreinoSemanal, 0, "Gerenciar Treino Semanal");
    		
    		return r;
    		
    	}
    	
    	@Override
    	public boolean onOptionsItemSelected(MenuItem item){
    		
    		switch(item.getItemId()){
    		
    		
    		case SAIR: mensagemExibir("Sair", "Saindo"); finish();  break;
    		case AJUDA: mensagemExibir("Ajuda", "Gerenciar Treino Semanal"); break;
    		case gerenciarTreinoSemanal: mensagemExibir("Gerenciar Treino Semanal", "Selecione um dia e cadastre um novo treino."); break;
    		}
    		
    		return super.onOptionsItemSelected(item);
    	}
    	
    	public void mensagemExibir(String titulo, String texto){
    		AlertDialog.Builder mensagem = new AlertDialog.Builder(this);
    		mensagem.setTitle(titulo);
    		mensagem.setMessage(texto);
    		mensagem.setNeutralButton("OK", null);
    		mensagem.show();
    	}

        
        
 
}