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
import com.example.asiment_chiph13080.model.Sach;


import java.util.ArrayList;

public class SachSpinerAdapter extends ArrayAdapter<Sach> {
    private Context context;
    private ArrayList<Sach> lists;
    TextView tvMaSach,tvTenSach;

    public SachSpinerAdapter(@NonNull Context context, int resource, ArrayList<Sach> lists) {
        super(context,0,lists);
        this.context=context;
        this.lists=lists;

    }

    public SachSpinerAdapter(Context context, ArrayList<Sach> listSach) {

        super(context, 0,listSach);
        this.context=context;
        this.lists=listSach;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if (v==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.sach_item_spiner,null,false);
        }
        final Sach item=lists.get(position);
        if (item !=null){
            tvMaSach=v.findViewById(R.id.tvMASachSp);
            tvTenSach=v.findViewById(R.id.tvTenSachSp);
            tvMaSach.setText(item.maSach+".");
            tvTenSach.setText(item.tenSach);
        }
        return v;

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if (v==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.sach_item_spiner,null,false);
        }
        final Sach item=lists.get(position);
        if (item !=null){
            tvMaSach=v.findViewById(R.id.tvMASachSp);
            tvTenSach=v.findViewById(R.id.tvTenSachSp);
            tvMaSach.setText(item.maSach+".");
            tvTenSach.setText(item.tenSach);
        }

        return v;
    }
}
