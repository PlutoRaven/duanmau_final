package com.example.asiment_chiph13080.frament;

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


public class AddUserFragment extends Fragment {
    EditText tv_add_name,tv_add_hovaten,tv_add_pass,tv_add_repass;
    Button btn_add_save,btn_add_cancel;
    ThuThuDAO dao;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_user, container, false);
        tv_add_name=view.findViewById(R.id.tv_add_name);
        tv_add_hovaten=view.findViewById(R.id.tv_add_hovaten);
        tv_add_pass=view.findViewById(R.id.tv_add_pass);
        tv_add_repass=view.findViewById(R.id.tv_add_repass);
        btn_add_save=view.findViewById(R.id.btn_add_save);
        btn_add_cancel=view.findViewById(R.id.btn_add_cancel);
        dao=new ThuThuDAO(getActivity());
        btn_add_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ThuThu thuThu=new ThuThu();
                thuThu.matKhau=tv_add_pass.getText().toString();
                thuThu.maTT=tv_add_name.getText().toString();
                thuThu.hoTen=tv_add_hovaten.getText().toString();
                if (validate()>0){

                    if (dao.insert(thuThu)>0){
                        Toast.makeText(getActivity(), "Lưu Thành Công", Toast.LENGTH_SHORT).show();
                        tv_add_name.setText("");
                        tv_add_hovaten.setText("");
                        tv_add_pass.setText("");
                        tv_add_repass.setText("");
                    }
                    else {
                        Toast.makeText(getActivity(), "Lưu Thất Bại", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        btn_add_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_add_name.setText("");
                tv_add_hovaten.setText("");
                tv_add_pass.setText("");
                tv_add_repass.setText("");
            }
        });

        return  view;
    }
    public  int validate(){
        int check=1;
        if (tv_add_name.getText().length()==0||tv_add_hovaten.getText().length()==0||
        tv_add_pass.getText().length()==0||tv_add_repass.getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check =-1;
        }
        else {
        String pass=tv_add_pass.getText().toString();
        String repass=tv_add_repass.getText().toString();
        if (!pass.equals(repass)){
            Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        }
        return check;
    }
}