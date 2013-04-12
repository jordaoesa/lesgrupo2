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

	public Intent execute(Context context, WeightLoss weightLoss, int id_aluno) {
		String[] titles = new String[] { "Ideal", "Real"};
		List<double[]> x = new ArrayList<double[]>();
		List<AcompanhamentoWeightLoss> acompanhamentos = FitnessManagementSingleton.getMetaFachadaInstance().getAcompanhamentoWeightLoss(id_aluno);
		double[] valueSemanasReal =  new double[acompanhamentos.size()];
		for (int i = 0; i < valueSemanasReal.length; i++) {
			valueSemanasReal[i] = i+1;
		}
		x.add(valueSemanasReal);
		double[] valueSemanasIdeal =  new double[weightLoss.getDiasPerderPeso()/7];
		for (int i = 0; i < valueSemanasIdeal.length; i++) {
			valueSemanasIdeal[i] = i + 1;
		}
		x.add(valueSemanasIdeal);
		List<double[]> valuesCalorias = new ArrayList<double[]>();
		valuesCalorias.add(new double[] { 12.3, 12.5, 13.8, 16.8, 20.4, 24.4, 26.4, 26.1, 23.6, 20.3, 17.2,
				13.9 });
		valuesCalorias.add(new double[] { 10, 10, 12, 15, 20, 24, 26, 26, 23, 18, 14, 11 });
		int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.CYAN, Color.YELLOW };
		PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE, PointStyle.DIAMOND, PointStyle.TRIANGLE, PointStyle.SQUARE };
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
		}
		setChartSettings(renderer, "Average temperature", "Semanas", "Total de Calorias", 0.5, 12.5, -10, 40,
				Color.LTGRAY, Color.LTGRAY);
		renderer.setXLabels(12);
		renderer.setYLabels(10);
		renderer.setShowGrid(true);
		renderer.setXLabelsAlign(Align.RIGHT);
		renderer.setYLabelsAlign(Align.RIGHT);
	//	renderer.setYLabelsPadding(10);
		renderer.setZoomButtonsVisible(true);
		renderer.setPanLimits(new double[] { -10, 20, -10, 40 });
		renderer.setZoomLimits(new double[] { -10, 20, -10, 40 });
		//renderer.setXLabelsPadding(-30);

		XYMultipleSeriesDataset dataset = buildDataset(titles, x, valuesCalorias);
		XYSeries series = dataset.getSeriesAt(0);
		//series.addAnnotation("Vacation", 6, 30);
		Intent intent = ChartFactory.getLineChartIntent(context, dataset, renderer,
				"Average temperature");
		return intent;
	}

	protected XYMultipleSeriesRenderer buildRenderer(int[] colors, PointStyle[] styles) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		setRenderer(renderer, colors, styles);
		return renderer;
	}

	protected void setRenderer(XYMultipleSeriesRenderer renderer, int[] colors, PointStyle[] styles) {
		renderer.setAxisTitleTextSize(16);
		renderer.setChartTitleTextSize(20);
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(15);
		renderer.setPointSize(5f);
		renderer.setMargins(new int[] { 20, 30, 15, 20 });
		int length = colors.length;
		for (int i = 0; i < length; i++) {
			XYSeriesRenderer r = new XYSeriesRenderer();
			r.setColor(colors[i]);
			r.setPointStyle(styles[i]);
			renderer.addSeriesRenderer(r);
		}
	}

	protected void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle,
			String yTitle, double xMin, double xMax, double yMin, double yMax, int axesColor,
			int labelsColor) {
		renderer.setChartTitle(title);
		renderer.setXTitle(xTitle);
		renderer.setYTitle(yTitle);
		renderer.setXAxisMin(xMin);
		renderer.setXAxisMax(xMax);
		renderer.setYAxisMin(yMin);
		renderer.setYAxisMax(yMax);
		renderer.setAxesColor(axesColor);
		renderer.setLabelsColor(labelsColor);
	}
	
	protected XYMultipleSeriesDataset buildDataset(String[] titles, List<double[]> xValues,
		      List<double[]> yValues) {
		    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		    addXYSeries(dataset, titles, xValues, yValues, 0);
		    return dataset;
		  }
	
	public void addXYSeries(XYMultipleSeriesDataset dataset, String[] titles, List<double[]> xValues,
		      List<double[]> yValues, int scale) {
		    int length = titles.length;
		    for (int i = 0; i < length; i++) {
		      XYSeries series = new XYSeries(titles[i], scale);
		      double[] xV = xValues.get(i);
		      double[] yV = yValues.get(i);
		      int seriesLength = xV.length;
		      for (int k = 0; k < seriesLength; k++) {
		        series.add(xV[k], yV[k]);
		      }
		      dataset.addSeries(series);
		    }
		  }

}
