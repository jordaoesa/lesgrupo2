package br.edu.ufcg.fitnessmanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import br.edu.ufcg.fachada.MetasFachada;
import br.edu.ufcg.grafico.GraficoDeLinha;
import br.edu.ufcg.util.FitnessManagementSingleton;

public class VisualizaGraficoMetasActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GraficoDeLinha grafico = new GraficoDeLinha();
		int id_aluno = getIntent().getIntExtra("id_aluno", -1);
		MetasFachada metaFachada = FitnessManagementSingleton.getMetaFachadaInstance();
		Intent intent = grafico.getGraficoMetas(this, metaFachada.getMetaWeightLoss(id_aluno), id_aluno);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
		startActivity(intent);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	    	System.out.println("OPAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
	    	finish();
	    	return true;
	    }
	    return false;
	}
	@Override
    public void onBackPressed() {
        super.onBackPressed();   
        //start activity A here

    }
}
