package br.edu.ufcg.util;

import br.edu.ufcg.fitnessmanagement.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapterVisualizarEstatisticas extends BaseAdapter {

	private Context context;
	private String[] nameItens = {"Peso", 
			"Altura", 
			"Braço Esquerdo Contraído", 
			"Braço Direito Contraído", 
			"Coxa Esquerda", 
			"Coxa Direita", 
			"Panturrilha Esquerda", 
			"Panturrilha Direita"};
	//COLOCAR NESSE ARRAY OS IDS DOS ICONES
	private int[] iconItens = {android.R.drawable.ic_media_play,
			android.R.drawable.ic_media_play,
			android.R.drawable.ic_media_play,
			android.R.drawable.ic_media_play,
			android.R.drawable.ic_media_play,
			android.R.drawable.ic_media_play,
			android.R.drawable.ic_media_play,
			android.R.drawable.ic_media_play};

	public ImageAdapterVisualizarEstatisticas(Context context) {
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return nameItens.length;
	}

	@Override
	public Object getItem(int position) {
		return nameItens[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		
		if(view == null){
			LayoutInflater inflater = LayoutInflater.from(context);
			view = inflater.inflate(R.layout.item_visualizar_estatisticas, null);
		}
		
		ImageView itemIcon = (ImageView) view.findViewById(R.id.imageViewItemVisualizarEstatisticas);
		itemIcon.setImageResource(iconItens[position]);
		
		TextView itemName = (TextView) view.findViewById(R.id.textViewItemVisualizarEstatisticas);
		itemName.setText(nameItens[position]);
		
		return view;
	}

}
