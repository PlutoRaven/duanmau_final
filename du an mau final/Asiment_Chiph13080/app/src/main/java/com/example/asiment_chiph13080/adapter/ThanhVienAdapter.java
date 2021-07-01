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
import com.example.asiment_chiph13080.frament.Thanh_Vien_Fragment;
import com.example.asiment_chiph13080.model.ThanhVien;


import java.util.ArrayList;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    Thanh_Vien_Fragment fragment;
    private ArrayList<ThanhVien>lists;
    TextView tvMaTV,tvTenTV,tvNamSinh;
    ImageView imgDel;

    public ThanhVienAdapter(@NonNull Context context, Thanh_Vien_Fragment fragment, ArrayList<ThanhVien> lists) {
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
            v=inflater.inflate(R.layout.thanhvien_item,null);
        }
        final ThanhVien iten=lists.get(position);
        if (iten !=null){
            tvMaTV=v.findViewById(R.id.tvMaTV);
            tvTenTV=v.findViewById(R.id.tvTenTV);
            tvNamSinh=v.findViewById(R.id.tvNamSinh);
            imgDel=v.findViewById(R.id.imgDeleteLS);
            tvMaTV.setText("Mã Thành Viên"+iten.maTV);
            tvTenTV.setText("Tên Thành Viên:"+iten.hoTen);
            tvNamSinh.setText("Năm Sinh:"+iten.namSinh);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(iten.maTV));
            }
        });
        return v;
    }
}
