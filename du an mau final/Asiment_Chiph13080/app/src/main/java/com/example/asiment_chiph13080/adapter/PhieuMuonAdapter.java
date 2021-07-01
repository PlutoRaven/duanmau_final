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
import com.example.asiment_chiph13080.dao.SachDAO;
import com.example.asiment_chiph13080.dao.ThanhVienDAO;
import com.example.asiment_chiph13080.frament.PhieuMuonFragment;
import com.example.asiment_chiph13080.model.PhieuMuon;
import com.example.asiment_chiph13080.model.Sach;
import com.example.asiment_chiph13080.model.ThanhVien;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {
    private Context context;
    PhieuMuonFragment fragment;
    private ArrayList<PhieuMuon> lists;
    TextView tvMaPM,tvTenTv,tvTenSach,tvTienThue,tvNgay,tvTraSach;
    ImageView imgdel;
    SachDAO sachDAO;
    ThanhVienDAO thanhVienDAO;
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");



    public PhieuMuonAdapter(@NonNull Context context, PhieuMuonFragment fragment, ArrayList<PhieuMuon> lists) {
        super(context, 0,lists);
        this.context = context;
        this.fragment = fragment;
        this.lists = lists;
    }
        @Override
    public int getCount() {
        if (lists==null)
        {return 0;}
        else {
            return lists.size();
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if (v==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.phieumuon_item,null);

        }
        final PhieuMuon item=lists.get(position);
        if (item != null) {
          PhieuMuon phieuMuon=lists.get(position);
            tvMaPM=v.findViewById(R.id.tvMaPM);
            sachDAO=new SachDAO(context);
           Sach sach=sachDAO.getID(String.valueOf(item.maSach));
            tvTenSach=v.findViewById(R.id.tvTenSach);
            thanhVienDAO=new ThanhVienDAO(context);
          ThanhVien thanhVien=thanhVienDAO.getID(String.valueOf(item.maTV));
            tvTenTv=v.findViewById(R.id.tvTenTV1);
            tvTienThue=v.findViewById(R.id.tvTienThue);
            tvNgay=v.findViewById(R.id.tvNgayPM);
            tvMaPM.setText(""+phieuMuon.maPH);
            tvTenSach.setText(""+sach.tenSach);
            tvTenTv.setText(""+thanhVien.hoTen);
            tvTienThue.setText(""+phieuMuon.tienThue);
            tvNgay.setText(""+phieuMuon.ngay);
            tvTraSach=v.findViewById(R.id.tvTraSach);
            if (item.traSach==1){
                tvTraSach.setTextColor(Color.BLUE);
                tvTraSach.setText("Đã trả sách");
            }
            else {
                tvTraSach.setTextColor(Color.RED);
                tvTraSach.setText("Chưa trả sách");
            }

        }
        imgdel=v.findViewById(R.id.imgDeletePM);
        imgdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoaa(String.valueOf(item.maPH));
            }
        });
        return v;
    }

}
