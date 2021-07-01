package com.example.asiment_chiph13080.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.asiment_chiph13080.R;
import com.example.asiment_chiph13080.fragment.TypeOfBookFragment;
import com.example.asiment_chiph13080.model.Book;
import com.example.asiment_chiph13080.model.TypeOfBook;

import java.util.ArrayList;

public class
TypeOfBookAdapter extends ArrayAdapter<TypeOfBook> {
    TypeOfBookFragment fragment;
    private ArrayList<TypeOfBook>lists;
    TextView tvMaLS,tvTenLS, tvNcc;
    ImageView imgDel;
    private Context context;

    public TypeOfBookAdapter(@NonNull Context context, TypeOfBookFragment fragment, ArrayList<TypeOfBook> lists) {
        super(context,0,lists);
        this.fragment = fragment;
        this.lists = lists;
        this.context=context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if (v==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE
            );
            v=inflater.inflate(R.layout.loai_sach_item,null);
        }
        final TypeOfBook iten=lists.get(position);
        if (iten !=null){
            tvMaLS=v.findViewById(R.id.tvIdTob);
            tvTenLS=v.findViewById(R.id.tvNameTob);
            tvNcc = v.findViewById(R.id.tvNcc);
            imgDel=v.findViewById(R.id.imgDeleteLS);
            tvMaLS.setText(""+iten.maLoai);
            tvTenLS.setText(""+iten.tenLoai);
            tvNcc.setText(""+ iten.nCC);
            /*for ( [1]:listSe){
                if (Book11.tacGia.toLowerCase().contains(search.toLowerCase())){
                    listTrungGian.add(Book11);
                }
            }

             */
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(iten.maLoai));
            }
        });
        return v;
    }
}
