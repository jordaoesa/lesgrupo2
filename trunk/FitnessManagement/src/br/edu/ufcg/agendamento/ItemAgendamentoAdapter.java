package br.edu.ufcg.agendamento;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import br.edu.ufcg.fitnessmanagement.R;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore.Images.Media;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAgendamentoAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<ItemAgendamento> itens;
	private ContentResolver contentResolver;

    public ItemAgendamentoAdapter(Context context, ArrayList<ItemAgendamento> itens, ContentResolver contentResolver) {
        this.itens = itens;
        this.contentResolver = contentResolver;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return itens.size();
    }

    public ItemAgendamento getItem(int position) {
        return itens.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ItemAgendamento item = itens.get(position);
        view = mInflater.inflate(R.layout.item_agendamento, null);

        TextView nameItem = ((TextView) view.findViewById(R.id.textViewItemAgendamentoName));
        nameItem.setText(item.getName());
        TextView infoItem = ((TextView) view.findViewById(R.id.textViewDataHorario));
        infoItem.setText(item.getInfo());
        nameItem.setTextColor(item.getColor());
        infoItem.setTextColor(item.getColor());
        Uri uri = Uri.fromFile(new File(item.getImagePath()));
        
        try {
			((ImageView) view.findViewById(R.id.imagemviewItemAgendamento)).setImageBitmap(Media.getBitmap(contentResolver, uri));
		} catch (FileNotFoundException e) {
			((ImageView) view.findViewById(R.id.imagemviewItemAgendamento)).setImageResource(R.drawable.ic_launcher);
		} catch (IOException e) {
			((ImageView) view.findViewById(R.id.imagemviewItemAgendamento)).setImageResource(R.drawable.ic_launcher);
		}

        return view;
    }

}