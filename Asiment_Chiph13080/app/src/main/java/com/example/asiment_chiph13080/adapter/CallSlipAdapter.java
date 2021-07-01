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
import com.example.asiment_chiph13080.dao.BookDAO;
import com.example.asiment_chiph13080.dao.MemberDAO;
import com.example.asiment_chiph13080.fragment.CallSlipFragment;
import com.example.asiment_chiph13080.model.Book;
import com.example.asiment_chiph13080.model.CallSlip;
import com.example.asiment_chiph13080.model.Member;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CallSlipAdapter extends ArrayAdapter<CallSlip> {
    private Context context;
    CallSlipFragment fragment;
    private ArrayList<CallSlip> lists;
    TextView tvMaPM,tvTenTv,tvTenSach,tvTienThue,tvNgay,tvTraSach;
    ImageView imgdel;
    BookDAO bookDAO;
    MemberDAO memberDAO;
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");



    public CallSlipAdapter(@NonNull Context context, CallSlipFragment fragment, ArrayList<CallSlip> lists) {
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
            v=inflater.inflate(R.layout.phieu_muon_item,null);

        }
        final CallSlip item=lists.get(position);
        if (item != null) {
          CallSlip callSlip =lists.get(position);
            tvMaPM=v.findViewById(R.id.tvMaPM);
            bookDAO =new BookDAO(context);
           Book book = bookDAO.getID(String.valueOf(item.maSach));
            tvTenSach=v.findViewById(R.id.tvTenSach);
            memberDAO =new MemberDAO(context);
          Member member = memberDAO.getID(String.valueOf(item.maTV));
            tvTenTv=v.findViewById(R.id.tvTenTV1);
            tvTienThue=v.findViewById(R.id.tvTienThue);
            tvNgay=v.findViewById(R.id.tvNgayPM);
            tvMaPM.setText(""+ callSlip.maPH);
            tvTenSach.setText(""+ book.tenSach);
            tvTenTv.setText(""+ member.hoTen);
            tvTienThue.setText(""+ callSlip.tienThue);
            tvNgay.setText(""+ callSlip.ngay);
            tvTraSach=v.findViewById(R.id.tvTraSach);
            if (item.traSach==1){
                tvTraSach.setTextColor(Color.WHITE);
                tvTraSach.setText("Da tra book");
            }
            else {
                tvTraSach.setTextColor(Color.rgb(255, 182, 181));
                tvTraSach.setText("Chua tra book");
            }

        }
        imgdel=v.findViewById(R.id.imgDeletePM);
        imgdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.maPH));
            }
        });
        return v;
    }

}
