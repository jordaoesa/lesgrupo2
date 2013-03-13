package br.edu.ufcg.fitnessmanagement;
 
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
 
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
                
                
                final Intent intent = new Intent(getApplicationContext(), GerenciadorTreinoDiarioActivity.class);
                
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
                
        }
        
 
 
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.activity_genciar_treino_semanal, menu);
                return true;
        }
        
        
        
 
}