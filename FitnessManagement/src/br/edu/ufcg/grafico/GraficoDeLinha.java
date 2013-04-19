package br.edu.ufcg.grafico;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import br.edu.ufcg.metas.AcompanhamentoWeightLoss;
import br.edu.ufcg.metas.WeightLoss;
import br.edu.ufcg.util.FitnessManagementSingleton;

public class GraficoDeLinha {

	public Intent getIntent(Context context, List<Double> dados, List<Date> datas, String titulo){
		TimeSeries series = new TimeSeries("varia��o");
		for(int i=0; i<dados.size(); i++){
			series.add(datas.get(i), dados.get(i));
		}

		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series);

		XYSeriesRenderer renderer = new XYSeriesRenderer();

		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		mRenderer.addSeriesRenderer(renderer);
		mRenderer.setXTitle("Datas");
		mRenderer.setYTitle(titulo);
		//		mRenderer.setZoomButtonsVisible(true);
		//		mRenderer.setBackgroundColor(Color.BLACK);
		//		mRenderer.setApplyBackgroundColor(true);

		Intent intent = ChartFactory.getLineChartIntent(context, dataset, mRenderer, titulo);
		return intent;
	}

	public Intent getGraficoMetas(Context context, WeightLoss weightLoss, int id_aluno) {
		List<AcompanhamentoWeightLoss> acompanhamentos = FitnessManagementSingleton.getMetaFachadaInstance().getAcompanhamentoWeightLoss(id_aluno);
		double[] valueSemanasReal =  new double[acompanhamentos.size()];
		for (int i = 0; i < valueSemanasReal.length; i++) {
			valueSemanasReal[i] = Double.parseDouble(acompanhamentos.get(i).getTotalCalorias());
		}
		double[] valueSemanasIdeal =  new double[weightLoss.getDiasPerderPeso()/7];
		for (int i = 0; i < valueSemanasIdeal.length; i++) {
			valueSemanasIdeal[i] = weightLoss.getCaloriasIdeaisPorSemana();
		}

		TimeSeries serieAlunoReal = new TimeSeries("Calorias Ingeridas"); 
		for( int i = 1; i <= valueSemanasReal.length; i++){
			serieAlunoReal.add(i, valueSemanasReal[i - 1]);
		}

		TimeSeries seriesAlunoIdeal = new TimeSeries("Calorias Recomendadas"); 
		for( int i = 1; i <= valueSemanasIdeal.length; i++)
		{
			seriesAlunoIdeal.add(i, valueSemanasIdeal[i - 1]);
		}

		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(serieAlunoReal);
		dataset.addSeries(seriesAlunoIdeal);

		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer(); // Holds a collection of XYSeriesRenderer and customizes the graph
		mRenderer.setScale(200);
		mRenderer.setZoomButtonsVisible(true);
		mRenderer.setBackgroundColor(Color.BLACK);
		mRenderer.setApplyBackgroundColor(true);


		XYSeriesRenderer renderer = new XYSeriesRenderer(); // This will be used to customize line 1
		XYSeriesRenderer renderer2 = new XYSeriesRenderer(); // This will be used to customize line 2
		mRenderer.addSeriesRenderer(renderer);
		mRenderer.addSeriesRenderer(renderer2);
		// Customization time for line 1!
		renderer.setColor(Color.WHITE);
		renderer.setPointStyle(PointStyle.SQUARE);
		// Customization time for line 2!
		renderer2.setColor(Color.YELLOW);
		renderer2.setPointStyle(PointStyle.DIAMOND);

		Intent intent = ChartFactory.getLineChartIntent(context, dataset, mRenderer, "Line Graph Title");
		return intent;
	}
}
