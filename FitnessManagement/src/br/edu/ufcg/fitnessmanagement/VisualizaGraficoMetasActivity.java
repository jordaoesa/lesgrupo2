package br.edu.ufcg.fitnessmanagement;

import br.edu.ufcg.grafico.GraficoDeLinha;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class VisualizaGraficoMetasActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GraficoDeLinha grafico = new GraficoDeLinha();
	}
}
