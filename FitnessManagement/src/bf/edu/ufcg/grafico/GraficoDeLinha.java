package bf.edu.ufcg.grafico;

import java.util.Date;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class GraficoDeLinha {
	
	public Intent getIntent(Context context, List<Double> dados, List<Date> datas, String titulo){
		TimeSeries series = new TimeSeries("variação");
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

}
