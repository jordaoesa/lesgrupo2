package com.ufcg.util;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ufcg.R;


public class ImageAdapter extends BaseAdapter {

	private Context  context;
	private final String[] itensValues;

	public ImageAdapter(Context context, String[] values) {
		this.context = context;
		this.itensValues = values;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View gridView;

		if (convertView == null) {

			gridView = new View(context);

			gridView = inflater.inflate(R.layout.imagebutton, null);

			TextView textView = (TextView) gridView
					.findViewById(R.id.grid_item_label);
			textView.setText(itensValues[position]);

			ImageView imageView = (ImageView) gridView
					.findViewById(R.id.grid_item_image);

			String itens = itensValues[position];
			if (itens.equals("Visualizar")) {
				imageView.setImageResource(R.drawable.visualizar);
			}else if(itens.equals("Novo")){
				imageView.setImageResource(R.drawable.novo);
			}else if(itens.equals("Sair")){
				imageView.setImageResource(R.drawable.logout);
			}
			

		} else {
			gridView = (View) convertView;
		}

		return gridView;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return itensValues.length;
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

}
