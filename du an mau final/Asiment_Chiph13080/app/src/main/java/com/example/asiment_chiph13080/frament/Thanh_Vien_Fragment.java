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
import com.example.asiment_chiph13080.adapter.ThanhVienAdapter;
import com.example.asiment_chiph13080.dao.ThanhVienDAO;
import com.example.asiment_chiph13080.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class Thanh_Vien_Fragment extends Fragment {
    ListView lv;
    ArrayList<ThanhVien> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edTenTv,edNamSinh;
    TextView edMaTV;
    Button btnSave,btnCacel;

    public static ThanhVienDAO dao;
    ThanhVienAdapter adapter;
    ThanhVien iten;



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_thanh__vien_,container,false);
        lv=v.findViewById(R.id.lvThanhVien);
        fab=v.findViewById(R.id.fab);
        dao=new ThanhVienDAO(getActivity());
        capNhatlv();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });
        lv.setOnItemLongClickListener((parent, view, position, id) -> {
            iten=list.get(position);
            openDialog(getActivity(),1);
            return false;
        });
        // Inflate the layout for this fragment
        return v;
    }
    protected void openDialog(final Context context,final int type){
        dialog=new Dialog(context);
        dialog.setContentView(R.layout.thanh_vien_dialog);
        edMaTV=dialog.findViewById(R.id.edMaTV);
        edTenTv=dialog.findViewById(R.id.edTenTV);
        edNamSinh=dialog.findViewById(R.id.edNamSinh);
        btnCacel=dialog.findViewById(R.id.btnCacelTV);
        btnSave=dialog.findViewById(R.id.btnSaveTV);
        edMaTV.setEnabled(false);
        if (type!=0){
            edMaTV.setText(String.valueOf(iten.maTV));
            edTenTv.setText(iten.hoTen);
            edNamSinh.setText(iten.namSinh);
        }
        btnCacel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iten=new ThanhVien();
                iten.hoTen=edTenTv.getText().toString();
                iten.namSinh=edNamSinh.getText().toString();
                if (validate()>0){
                    if (type==0){
                        // insert
                        if (dao.insert(iten)>0){
                            Toast.makeText(context,"Thêm thành công",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(context,"Thêm thất bại",Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        // update
                        iten.maTV=Integer.parseInt(edMaTV.getText().toString());
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
    public  void xoa(final String Id){
        // sd alert
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
        list=(ArrayList<ThanhVien>)dao.getAll();
        adapter=new ThanhVienAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }
    public  int validate(){
        int check=1;
        if (edTenTv.getText().length()==0||edNamSinh.getText().length()==0){
            Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_LONG).show();
            check=-1;
        }
        return check;
    }

}