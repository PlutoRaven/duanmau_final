package com.example.asiment_chiph13080.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.asiment_chiph13080.R;
import com.example.asiment_chiph13080.frament.Loai_Sach_Fragment;
import com.example.asiment_chiph13080.model.LoaiSach;

import java.util.ArrayList;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {
    Loai_Sach_Fragment fragment;
    private ArrayList<LoaiSach>lists;
    TextView tvMaLS,tvTenLS;
    ImageView imgDel;
    private Context context;

    public LoaiSachAdapter(@NonNull Context context, Loai_Sach_Fragment fragment, ArrayList<LoaiSach> lists) {
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
            v=inflater.inflate(R.layout.loaisach_item,null);
        }
        final LoaiSach iten=lists.get(position);
        if (iten !=null){
            tvMaLS=v.findViewById(R.id.tvMaLS);
            tvTenLS=v.findViewById(R.id.tvTenLS);

            imgDel=v.findViewById(R.id.imgDeleteLS);
            tvMaLS.setText(""+iten.maLoai);
            tvTenLS.setText(""+iten.tenLoai);

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
