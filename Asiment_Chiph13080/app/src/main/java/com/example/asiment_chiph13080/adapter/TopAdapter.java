package com.example.asiment_chiph13080.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.asiment_chiph13080.R;
import com.example.asiment_chiph13080.model.Top;


import java.util.ArrayList;

public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;

    private ArrayList<Top> lists;
        TextView tvSach,tvSL;

    public TopAdapter(@NonNull Context context,ArrayList<Top> lists) {
        super(context, 0,lists);
        this.context=context;
        this.lists=lists;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if (v==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.top_item,null);
        }
        final Top item=lists.get(position);
        if (item!=null){
            tvSach=v.findViewById(R.id.tvSach);
            tvSach.setText("Book name: "+item.tenSach);
            tvSL=v.findViewById(R.id.tvSoLuong);
            tvSL.setText("Quantity: "+item.soLuong);
        }
        return v;
    }
}
