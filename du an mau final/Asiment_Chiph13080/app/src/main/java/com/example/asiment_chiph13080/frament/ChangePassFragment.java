package com.example.asiment_chiph13080.frament;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.asiment_chiph13080.R;
import com.example.asiment_chiph13080.dao.ThuThuDAO;
import com.example.asiment_chiph13080.model.ThuThu;


public class ChangePassFragment extends Fragment {
    EditText edpassold,edpassnew,edrepass;
    Button btnluu,btnhuy;
    ThuThuDAO dao;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_change_pass,container,false);
        edpassold=view.findViewById(R.id.framnetpasscu);
        edpassnew=view.findViewById(R.id.framnetpassmoi);
        edrepass=view.findViewById(R.id.framnetpassnhaplaimoi);
        btnluu=view.findViewById(R.id.btnluu);
        btnhuy=view.findViewById(R.id.btnhuy);
        dao=new ThuThuDAO(getActivity());
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref=getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user=pref.getString("USERNAME","");
                if (validate()>0){
                    ThuThu thuThu=dao.getID(user);
                    thuThu.matKhau=edpassnew.getText().toString();
                    dao.update(thuThu);
                    if (dao.update(thuThu)>0){
                        Toast.makeText(getActivity(),"Thay Đổi Mật Khẩu Thành Công",Toast.LENGTH_LONG).show();
                        edpassold.setText("");
                        edpassnew.setText("");
                        edrepass.setText("");
                    }else {
                        Toast.makeText(getActivity(),"Thay Đổi Mật Khẩu Thất Bại",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edpassnew.setText("");
                edpassold.setText("");
                edrepass.setText("");
            }
        });

        return view;
    }
    public int validate() {
        int check = 1;
        if (edpassold.getText().length() == 0 || edpassnew.getText().length() == 0 || edrepass.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
            check = -1;
        } else {
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passOld = pref.getString("PASSWORD", "");
            String pass = edpassnew.getText().toString();
            String rePass = edrepass.getText().toString();
            if (!passOld.equals(edpassold.getText().toString())) {
                Toast.makeText(getContext(), "Mật khẩu cũ không đúng", Toast.LENGTH_LONG).show();
                check = -1;
                if (!pass.equals(rePass)) {
                    Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_LONG).show();
                    check = -1;
                }
            }

        }
        return check;
    }
}