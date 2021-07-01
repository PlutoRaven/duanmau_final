package com.example.asiment_chiph13080.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.asiment_chiph13080.R;
import com.example.asiment_chiph13080.fragment.MemberFragment;
import com.example.asiment_chiph13080.model.Member;


import java.util.ArrayList;

public class MemberAdapter extends ArrayAdapter<Member> implements Filterable {
    private Context context;
    MemberFragment fragment;
     ArrayList<Member>lists;
     //arr search list
     ArrayList<Member> listSe;
    TextView tvIdM,tvNameM,tvBirthM;
    ImageView imgDeleteM;

    public MemberAdapter(@NonNull Context context, MemberFragment fragment, ArrayList<Member> lists) {
        super(context,0,lists);
        this.fragment = fragment;
        this.lists = lists;
        //searchlist
        this.listSe = lists ;
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
         Member member=lists.get(position);
        if (member !=null){
            tvIdM=v.findViewById(R.id.tvIdM);
            tvNameM=v.findViewById(R.id.tvNameM);
            tvBirthM=v.findViewById(R.id.tvBirthM);
            imgDeleteM=v.findViewById(R.id.imgDeleteM);
            tvIdM.setText("Mã Thành Viên"+member.maTV);
            tvNameM.setText("Tên Thành Viên:"+member.hoTen);
            tvBirthM.setText("Năm Sinh:"+member.namSinh);
            if (position == 0 || position % 2 == 0){
                tvIdM.setTextColor(Color.RED);
                tvNameM.setTextColor(Color.RED);
                tvBirthM.setTextColor(Color.RED);
            }
            else{
                tvIdM.setTextColor(Color.BLUE);
                tvNameM.setTextColor(Color.BLUE);
                tvBirthM.setTextColor(Color.BLUE);
            }
        }
        imgDeleteM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(member.maTV));
            }
        });
        return v;
    }

    @NonNull

    //filter search
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String search  = constraint.toString();
                if (search.isEmpty()){
                    lists = listSe;
                }
                else{
                    ArrayList<Member> listTrungGian = new ArrayList<>();
                    for (Member member11:listSe){
                        if (member11.hoTen.toLowerCase().contains(search.toLowerCase())){
                            listTrungGian.add(member11);
                        }
                    }
                    lists = (ArrayList<Member>) listTrungGian ;
                }
                FilterResults results = new FilterResults();
                results.values = lists ;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                lists = (ArrayList<Member>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @Nullable
    @Override
    public Member getItem(int position) {
        return lists.get(position);
    }

    @Override
    public int getCount() {
        return lists.size();
    }
}
