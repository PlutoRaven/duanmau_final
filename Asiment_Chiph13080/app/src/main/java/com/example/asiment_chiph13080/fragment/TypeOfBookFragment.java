package com.example.asiment_chiph13080.fragment;

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
import com.example.asiment_chiph13080.adapter.TypeOfBookAdapter;
import com.example.asiment_chiph13080.dao.TypeOfBookDAO;
import com.example.asiment_chiph13080.model.TypeOfBook;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static com.example.asiment_chiph13080.fragment.CallSlipFragment.getScreenWidth;

public class TypeOfBookFragment extends Fragment {
    public static TypeOfBookDAO dao;
    ListView lv;
    ArrayList<TypeOfBook> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edNameTob, edNcc;
    TextView edIdTob;
    Button btnSaveTob, btnCancelTob;
    TypeOfBookAdapter adapter;
    TypeOfBook TypeOfBook;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_loai_sach, container, false);
        lv = v.findViewById(R.id.lvLoaiSach);
        fab = v.findViewById(R.id.fab);
        dao = new TypeOfBookDAO(getActivity());
        capNhatlv();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiaLog(getActivity(), 0);
            }
        });
        lv.setOnItemLongClickListener((parent, view, position, id) -> {
            TypeOfBook = list.get(position);
            openDiaLog(getActivity(), 1);
            return false;
        });
        // Inflate the layout for this fragment
        return v;
    }

    protected void openDiaLog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.loai_sach_dialog);
        dialog.getWindow().setLayout((int) (getScreenWidth(getActivity()) * .9), ViewGroup.LayoutParams.WRAP_CONTENT);
        edIdTob = dialog.findViewById(R.id.edIdTob);
        edNameTob = dialog.findViewById(R.id.edNameTob);
        edNcc = dialog.findViewById(R.id.edNcc);
        btnCancelTob = dialog.findViewById(R.id.btnCacelTob);
        btnSaveTob = dialog.findViewById(R.id.btnSaveTob);
        edIdTob.setEnabled(false);
        if (type != 0) {
            edIdTob.setText(String.valueOf(TypeOfBook.maLoai));
            edNameTob.setText(TypeOfBook.tenLoai);
            edNcc.setText(TypeOfBook.nCC);
        }
        btnCancelTob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSaveTob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TypeOfBook = new TypeOfBook();
                TypeOfBook.tenLoai = edNameTob.getText().toString();
                TypeOfBook.nCC = edNcc.getText().toString();
                if (validate() > 0) {
                    if (type == 0) {
                        if (dao.insert(TypeOfBook) > 0) {
                            Toast.makeText(context, "Added", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        TypeOfBook.maLoai = Integer.parseInt(edIdTob.getText().toString());
                        if (dao.update(TypeOfBook) > 0) {
                            Toast.makeText(context, "Fixed", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                    capNhatlv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Do you want to delete?");
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
        AlertDialog alertDialog = builder.create();
        builder.show();
    }

    void capNhatlv() {
        list = (ArrayList<TypeOfBook>) dao.getAll();
        adapter = new TypeOfBookAdapter(getActivity(), this, list);
        lv.setAdapter(adapter);
    }

    public int validate() {
        int check = 1;
        if (edNameTob.getText().length() == 0 || edNcc.getText().length() == 0) {
            Toast.makeText(getContext(), "Please fill in the form", Toast.LENGTH_LONG).show();
            check = -1;
        }
        return check;
    }
}