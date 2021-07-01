package com.example.asiment_chiph13080.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.asiment_chiph13080.R;
import com.example.asiment_chiph13080.dao.UserDAO;
import com.example.asiment_chiph13080.model.User;


public class AddUserFragment extends Fragment {
    EditText et_add_name, et_add_hoten, et_add_pass, et_add_repass;
    Button btn_add_save, btn_add_cancel;
    UserDAO dao;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);
        et_add_name = view.findViewById(R.id.et_add_name);
        et_add_hoten = view.findViewById(R.id.et_add_hoten);
        et_add_pass = view.findViewById(R.id.et_add_pass);
        et_add_repass = view.findViewById(R.id.et_add_repass);
        btn_add_save = view.findViewById(R.id.btn_add_save);
        btn_add_cancel = view.findViewById(R.id.btn_add_cancel);
        dao = new UserDAO(getActivity());
        btn_add_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User();
                user.matKhau = et_add_pass.getText().toString();
                user.maTT = et_add_name.getText().toString();
                user.hoTen = et_add_hoten.getText().toString();
                if (validate() > 0) {

                    if (dao.insert(user) > 0) {
                        Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();
                        et_add_name.setText("");
                        et_add_hoten.setText("");
                        et_add_pass.setText("");
                        et_add_repass.setText("");
                    } else {
                        Toast.makeText(getActivity(), "Not saved", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        btn_add_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_add_name.setText("");
                et_add_hoten.setText("");
                et_add_pass.setText("");
                et_add_repass.setText("");
            }
        });

        return view;
    }

    public int validate() {
        int check = 1;
        if (et_add_name.getText().length() == 0 || et_add_hoten.getText().length() == 0 ||
                et_add_pass.getText().length() == 0 || et_add_repass.getText().length() == 0) {
            Toast.makeText(getContext(), "Please fill in the form", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String pass = et_add_pass.getText().toString();
            String repass = et_add_repass.getText().toString();
            if (!pass.equals(repass)) {
                Toast.makeText(getContext(), "Password doesn't match", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}