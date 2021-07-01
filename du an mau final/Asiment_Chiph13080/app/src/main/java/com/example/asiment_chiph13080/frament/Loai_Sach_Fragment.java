package com.example.asiment_chiph13080.frament;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import com.example.asiment_chiph13080.R;
import com.example.asiment_chiph13080.adapter.LoaiSachAdapter;
import com.example.asiment_chiph13080.dao.LoaiSachDAO;
import com.example.asiment_chiph13080.model.LoaiSach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Loai_Sach_Fragment extends Fragment {
    ListView lv;
    ArrayList<LoaiSach>list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edTenLS;
    TextView edMaLS;
    Button btnSave,btnCacl;

    public static LoaiSachDAO dao;
    LoaiSachAdapter adapter;
    LoaiSach iten;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_loai__sach_,container,false);
        lv=v.findViewById(R.id.lvLoaiSach);
        fab=v.findViewById(R.id.fab);
        dao=new LoaiSachDAO(getActivity());
        capNhatlv();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiaLog(getActivity(),0);
            }
        });
        lv.setOnItemLongClickListener((parent, view, position, id) -> {
            iten=list.get(position);
            openDiaLog(getActivity(),1);
            return  false;
        });
        // Inflate the layout for this fragment
        return v;
    }
    protected  void openDiaLog(final Context context,final int type){
        dialog=new Dialog(context);
        dialog.setContentView(R.layout.loai_sach_dialog);
        edMaLS=dialog.findViewById(R.id.edMaLS);
        edTenLS=dialog.findViewById(R.id.edTenLS);
        btnCacl=dialog.findViewById(R.id.btnCacelLS);
        btnSave=dialog.findViewById(R.id.btnSaveLS);
        edMaLS.setEnabled(false);
        if (type!=0){
            edMaLS.setText(String.valueOf(iten.maLoai));
            edTenLS.setText(iten.tenLoai);
        }
        btnCacl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iten=new LoaiSach();
                iten.tenLoai=edTenLS.getText().toString();
                if (validate()>0){
                    if (type==0){
                        if (dao.insert(iten)>0){
                            Toast.makeText(context,"Thêm thành công",Toast.LENGTH_LONG).show();

                        }
                        else {
                            Toast.makeText(context,"Thêm thất bại",Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        iten.maLoai=Integer.parseInt(edMaLS.getText().toString());
                        if (dao.update(iten)>0){
                            Toast.makeText(context,"Sửa thành công",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(context,"Sửa thất bại",Toast.LENGTH_LONG).show();
                        }
                    }
                    capNhatlv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
    public void xoa(final String Id){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(Id);
                capNhatlv();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog=builder.create();
        builder.show();
    }
    void capNhatlv(){
        list=(ArrayList<LoaiSach>)dao.getAll();
        adapter=new LoaiSachAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }
    public int validate(){
        int check=1;
        if (edTenLS.getText().length()==0){
            Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_LONG).show();
            check=-1;
        }
        return  check;
    }
}