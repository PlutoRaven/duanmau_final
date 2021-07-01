package com.example.asiment_chiph13080.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.asiment_chiph13080.R;
import com.example.asiment_chiph13080.model.TypeOfBook;


import java.util.ArrayList;

public class TypeOfBookSpinerAdapter extends ArrayAdapter<TypeOfBook> {
    private Context context;
    private ArrayList<TypeOfBook> lists;
    TextView tvIdTobSp, tvNameTobSp, tvnCC;

    public TypeOfBookSpinerAdapter(@NonNull Context context, ArrayList<TypeOfBook> lists) {
        super(context, 0, lists);
        this.context = context;
        this.lists = lists;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_sach_item_spiner, parent, false);
        }
       final TypeOfBook item = lists.get(position);
        if (item != null) {
            tvIdTobSp = v.findViewById(R.id.tvIdTob);
            tvNameTobSp = v.findViewById(R.id.tvNameTob);
            tvIdTobSp.setText(item.maLoai +"");
            tvNameTobSp.setText(item.tenLoai);
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_sach_item_spiner, parent, false);
        }
        TypeOfBook item = lists.get(position);
        if (item != null) {
            tvIdTobSp = v.findViewById(R.id.tvIdTob);
            tvNameTobSp = v.findViewById(R.id.tvNameTob);
            tvnCC = v.findViewById(R.id.tvNcc);
            tvIdTobSp.setText(item.maLoai +"");
            tvNameTobSp.setText(item.tenLoai);
            tvnCC.setText(item.nCC);
        }
        return v;
    }
}
