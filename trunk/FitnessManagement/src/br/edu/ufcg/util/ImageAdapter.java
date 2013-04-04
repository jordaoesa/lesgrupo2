package br.edu.ufcg.util;

import java.io.File;
import java.util.List;

import br.edu.ufcg.aluno.Aluno;
import br.edu.ufcg.fitnessmanagement.R;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore.Images.Media;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
	
	private ContentResolver contentResolver;
	private Context context;
	private List<Aluno> alunos;
	
	public ImageAdapter(Context context, List<Aluno> alunos, ContentResolver contentResolver) {
		this.context = context;
		this.alunos = alunos;
		this.contentResolver = contentResolver;
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
	
	public void removeItem(int position) {
		alunos.remove(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = null;
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = new View(context);
			view = inflater.inflate(R.layout.imagebutton, null);
		}else{
			view = convertView;
		}
		
		TextView textView = (TextView) view.findViewById(R.id.grid_item_label);
		textView.setText(alunos.get(position).getNome());
		
		ImageView imageView = (ImageView) view.findViewById(R.id.grid_item_image);
		//imageView.setImageResource(R.drawable.ic_launcher);
		try {
			Uri uri = Uri.fromFile(new File(alunos.get(position).getCaminhoImagem()));
			imageView.setImageBitmap(Media.getBitmap(contentResolver, uri));
		} catch (Exception e) {
			imageView.setImageResource(R.drawable.ic_launcher);
		}

		return view;
	}

	
}
