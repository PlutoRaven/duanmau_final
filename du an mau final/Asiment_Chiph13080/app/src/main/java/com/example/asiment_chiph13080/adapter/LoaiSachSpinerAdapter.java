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
import com.example.asiment_chiph13080.model.LoaiSach;


import java.util.ArrayList;

public class LoaiSachSpinerAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    private ArrayList<LoaiSach> lists;
    TextView tvMALoaiSp, tvTenLoaiSp;

    public LoaiSachSpinerAdapter(@NonNull Context context, ArrayList<LoaiSach> lists) {
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
            v = inflater.inflate(R.layout.loaisach_item_spiner, parent, false);
        }
       final LoaiSach item = lists.get(position);
        if (item != null) {
            tvMALoaiSp = v.findViewById(R.id.tvMALoai);
            tvTenLoaiSp = v.findViewById(R.id.tvTenLoai);
            tvMALoaiSp.setText(item.maLoai +"");
            tvTenLoaiSp.setText(item.tenLoai);
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loaisach_item_spiner, parent, false);
        }
        LoaiSach item = lists.get(position);
        if (item != null) {
            tvMALoaiSp = v.findViewById(R.id.tvMALoai);
            tvTenLoaiSp = v.findViewById(R.id.tvTenLoai);
            tvMALoaiSp.setText(item.maLoai +"");
            tvTenLoaiSp.setText(item.tenLoai);
        }
        return v;
    }
}
