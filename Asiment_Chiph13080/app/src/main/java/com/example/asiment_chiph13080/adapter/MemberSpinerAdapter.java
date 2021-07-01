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
import com.example.asiment_chiph13080.model.Member;


import java.util.ArrayList;

public class MemberSpinerAdapter extends ArrayAdapter<Member> {
    private Context context;
    private ArrayList<Member>lists;
    TextView tvIdM,tvNameM;

    public MemberSpinerAdapter(@NonNull Context context, ArrayList<Member>lists) {
        super(context,0,lists);
        this.context=context;
        this.lists=lists;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if (v==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.thanh_vien_item_spiner,null,false);
        }
        final Member item=lists.get(position);
        if (item !=null){
            tvIdM=v.findViewById(R.id.tvIdM);
            tvNameM=v.findViewById(R.id.tvNameM);
            tvIdM.setText(item.maTV+"");
            tvNameM.setText(item.hoTen);
        }
        return v;
    }
    // khi người dùng click spiner hiện thị lên

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if (v==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.thanh_vien_item_spiner,null,false);
        }
        final Member item=lists.get(position);
        if (item !=null){
            tvIdM=v.findViewById(R.id.tvIdM);
            tvNameM=v.findViewById(R.id.tvNameM);
            tvIdM.setText(item.maTV+"");
            tvNameM.setText(item.hoTen);
        }
        return v;
    }
}
