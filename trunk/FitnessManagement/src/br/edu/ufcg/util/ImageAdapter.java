package br.edu.ufcg.util;

import java.util.List;

import br.edu.ufcg.aluno.Aluno;
import br.edu.ufcg.fitnessmanagement.R;
import br.edu.ufcg.fitnessmanagement.R.drawable;
import br.edu.ufcg.fitnessmanagement.R.id;
import br.edu.ufcg.fitnessmanagement.R.layout;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
	
	private Context context;
	private List<Aluno> alunos;
	
	public ImageAdapter(Context context, List<Aluno> alunos) {
		this.context = context;
		this.alunos = alunos;
	}

	@Override
	public int getCount() {
		return alunos.size();
	}

	@Override
	public Object getItem(int position) {
		return alunos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return alunos.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {


		View view = null;
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = new View(context);
			view = inflater.inflate(R.layout.imagebutton, null);
			
			TextView textView = (TextView) view.findViewById(R.id.grid_item_label);
			textView.setText(alunos.get(position).getNome());
			
			ImageView imageView = (ImageView) view.findViewById(R.id.grid_item_image);
			imageView.setImageResource(R.drawable.ic_launcher);//TODO COLOCAR A IMAGEM Q SERA ARMAZENADA EM ALUNO
			
			
		}else{
			view = (View) convertView;
		}

		return view;
	}

	
}
