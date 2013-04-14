package br.edu.ufcg.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.edu.ufcg.fitnessmanagement.R;

public class ImageAdapterPerfilDoAluno extends BaseAdapter {

	private Context context;
	private String[] itensPerfil;
	//TODO: MODIFIQUEM ESSE ARRAY DE ICONES COM OS ICONES DESEJADOS :P
	private int[] iconItens = { android.R.drawable.ic_media_ff,
			android.R.drawable.ic_media_next,
			android.R.drawable.ic_media_pause,
			android.R.drawable.ic_media_play,
			android.R.drawable.ic_media_previous,
			android.R.drawable.ic_media_rew };

	public ImageAdapterPerfilDoAluno(Context context, String[] itensPerfil) {
		this.context = context;
		this.itensPerfil = itensPerfil;
	}
	
	@Override
	public int getCount() {
		return itensPerfil.length;
	}

	@Override
	public Object getItem(int position) {
		return itensPerfil[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		
		LayoutInflater inflater = LayoutInflater.from(context);
		view = inflater.inflate(R.layout.item_perfil_do_aluno, null);
		
		ImageView itemIcon = (ImageView) view.findViewById(R.id.imageViewItemPerfilDoAluno);
		itemIcon.setImageResource(iconItens[position]);
		
		TextView itemName = (TextView) view.findViewById(R.id.textViewItemPerfilDoAluno);
		itemName.setText(itensPerfil[position]);
		
		return view;
	}

}
