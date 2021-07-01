package com.example.asiment_chiph13080.fragment;

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
import com.example.asiment_chiph13080.dao.UserDAO;
import com.example.asiment_chiph13080.model.User;


public class ChangePassFragment extends Fragment {
    EditText edOldPass, edNewPass, edReNewPass;
    Button btnSave, btnCancel;
    UserDAO dao;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_pass, container, false);
        edOldPass = view.findViewById(R.id.et_old_pass);
        edNewPass = view.findViewById(R.id.et_new_pass);
        edReNewPass = view.findViewById(R.id.et_re_new_pass);
        btnSave = view.findViewById(R.id.btnSaveCp);
        btnCancel = view.findViewById(R.id.btnCancelCp);
        dao = new UserDAO(getActivity());
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = pref.getString("USERNAME", "");
                if (validate() > 0) {
                    User thuThu = dao.getID(user);
                    thuThu.matKhau = edNewPass.getText().toString();
                    dao.update(thuThu);
                    if (dao.update(thuThu) > 0) {
                        Toast.makeText(getActivity(), "Password changed", Toast.LENGTH_LONG).show();
                        edOldPass.setText("");
                        edNewPass.setText("");
                        edReNewPass.setText("");
                    } else {
                        Toast.makeText(getActivity(), "Failed", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edNewPass.setText("");
                edOldPass.setText("");
                edReNewPass.setText("");
            }
        });

        return view;
    }

    public int validate() {
        int check = 1;
        if (edOldPass.getText().length() == 0 || edNewPass.getText().length() == 0 || edReNewPass.getText().length() == 0) {
            Toast.makeText(getContext(), "Please fill in the form", Toast.LENGTH_LONG).show();
            check = -1;
        } else {
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passOld = pref.getString("PASSWORD", "");
            String pass = edNewPass.getText().toString();
            String rePass = edReNewPass.getText().toString();
            if (!passOld.equals(edOldPass.getText().toString())) {
                Toast.makeText(getContext(), "False password", Toast.LENGTH_LONG).show();
                check = -1;
                if (!pass.equals(rePass)) {
                    Toast.makeText(getContext(), "Password doesn't match", Toast.LENGTH_LONG).show();
                    check = -1;
                }
            }

        }
        return check;
    }
}