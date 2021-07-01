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
import com.example.asiment_chiph13080.model.Book;


import java.util.ArrayList;

public class BookSpinerAdapter extends ArrayAdapter<Book> {
    private Context context;
    private ArrayList<Book> lists;
    TextView tvIdB,tvNameB;

    public BookSpinerAdapter(@NonNull Context context, int resource, ArrayList<Book> lists) {
        super(context,0,lists);
        this.context=context;
        this.lists=lists;

    }

    public BookSpinerAdapter(Context context, ArrayList<Book> listBooks) {

        super(context, 0, listBooks);
        this.context=context;
        this.lists= listBooks;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if (v==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.sach_item_spiner,null,false);
        }
        final Book item=lists.get(position);
        if (item !=null){
            tvIdB=v.findViewById(R.id.tvIdB);
            tvNameB=v.findViewById(R.id.tvNameB);
            tvIdB.setText(item.maSach+".");
            tvNameB.setText(item.tenSach);
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
        final Book item=lists.get(position);
        if (item !=null){
            tvIdB=v.findViewById(R.id.tvIdB);
            tvNameB=v.findViewById(R.id.tvNameB);
            tvIdB.setText(item.maSach+".");
            tvNameB.setText(item.tenSach);
        }

        return v;
    }
}
