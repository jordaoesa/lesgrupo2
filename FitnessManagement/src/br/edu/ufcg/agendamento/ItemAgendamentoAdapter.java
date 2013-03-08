package br.edu.ufcg.agendamento;

import java.util.ArrayList;

import br.edu.ufcg.fitnessmanagement.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAgendamentoAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<ItemAgendamento> itens;

    public ItemAgendamentoAdapter(Context context, ArrayList<ItemAgendamento> itens) {
        this.itens = itens;
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
        view = mInflater.inflate(R.layout.tela_visualizar_agendamento, null);

        ((TextView) view.findViewById(R.id.textViewItemAgendamento)).setText(item.getTexto());
        ((ImageView) view.findViewById(R.id.imagemviewItemAgendamento)).setImageResource(item.getIconeId());

        return view;
    }

}