package com.example.asiment_chiph13080.frament;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.asiment_chiph13080.R;
import com.example.asiment_chiph13080.adapter.LoaiSachSpinerAdapter;
import com.example.asiment_chiph13080.adapter.SachAdapter;
import com.example.asiment_chiph13080.adapter.SachSpinerAdapter;
import com.example.asiment_chiph13080.dao.LoaiSachDAO;
import com.example.asiment_chiph13080.dao.SachDAO;
import com.example.asiment_chiph13080.model.LoaiSach;
import com.example.asiment_chiph13080.model.Sach;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class SachFragment extends Fragment {

    ListView lv;
    Dialog dialog;
    View view;
    Sach sach;
    int maloaiSach;
    SachDAO sachDAO;
    ArrayList<Sach> lists;
    LoaiSachSpinerAdapter loaiSachSpinerAdapter;
    List<LoaiSach> listloaisach;
    SachAdapter adapter;
    LoaiSachDAO loaiSachDAO;

    FloatingActionButton fab;

    int positionLS;
    SachSpinerAdapter sachSpinerAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sach, container, false);
        lv = view.findViewById(R.id.lvsach);
        fab = view.findViewById(R.id.fab);
        sachDAO = new SachDAO(getActivity());
        capNhatlv();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lv.setOnItemLongClickListener((parent, view1, position, id) -> {
            sach = lists.get(position);
            openDialog(getActivity(), 1);
            return false;
        });

        // Inflate the layout for this fragment
        return view;
    }

    protected void openDialog(Context context, final int type) {
        dialog = new Dialog(context);

        dialog.setContentView(R.layout.sach_dialog);
        TextView edMaSach = dialog.findViewById(R.id.edMaSachS);

        EditText tvTenSach = dialog.findViewById(R.id.tvTenSach1);
        EditText tvGiaThue = dialog.findViewById(R.id.tvGiaThueS);
        Button btnSavePM = dialog.findViewById(R.id.btnSavePM1);
        Button btnCancelPM = dialog.findViewById(R.id.btnCancelPMS);
        Spinner spinner = dialog.findViewById(R.id.spLoaiSachS);
        loaiSachDAO = new LoaiSachDAO(context);
        listloaisach =  loaiSachDAO.getAll();
        loaiSachSpinerAdapter = new LoaiSachSpinerAdapter(context, (ArrayList<LoaiSach>) listloaisach);
        spinner.setAdapter(loaiSachSpinerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maloaiSach = listloaisach.get(position).maLoai;
                Toast.makeText(context, "Ch???n" + listloaisach.get(position).tenLoai, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        edMaSach.setEnabled(false);
        if (type != 0) {
            edMaSach.setText(String.valueOf(sach.maSach));
            for (int i = 0; i < lists.size(); i++) {
                if (sach.maLoai == (lists.get(i).maLoai)) {
                    positionLS = i;
                }

                Log.i("Demo","posSach"+positionLS);
                spinner.setSelection(positionLS);
//                tvTenSach.setText("T??n S??ch:" + sach.tenSach);
//                tvGiaThue.setText("Ti???n thu??:" + sach.giaThue);
            }

        }
        btnCancelPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSavePM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sach = new Sach();

                sach.maLoai = maloaiSach;
                sach.tenSach = tvTenSach.getText().toString();
                sach.giaThue = Integer.parseInt(tvGiaThue.getText().toString());
             if (validate() > 0) {
                    if (type == 0) {
                        if (sachDAO.insert(sach) > 0) {
                            Toast.makeText(context, "Th??m th??nh c??ng", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "Th??m th???t b???i", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        sach.maSach = Integer.parseInt(edMaSach.getText().toString());
                        if (sachDAO.update(sach) > 0) {
                            Toast.makeText(context, "S???a th??nh c??ng", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "S???a th???t b???i", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatlv();
                    dialog.dismiss();
                }
          }
        });
        dialog.show();
  }

    public void xoaa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("B???n c?? mu???n x??a kh??ng?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sachDAO.delete(Id);
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
        AlertDialog alertDialog = builder.create();
        builder.show();
    }

    void capNhatlv() {
        lists = (ArrayList<Sach>) sachDAO.getAll();
        adapter = new SachAdapter(getActivity(), this,lists);
        lv.setAdapter(adapter);
    }

    public int validate() {
        int check = 1;
//        if (ed.getText().length()==0){
//            Toast.makeText(getContext(),"B???n ph???i nh???p ?????y ????? th??ng tin",Toast.LENGTH_LONG).show();
//            check=-1;
//        }
        return check;
    }
}