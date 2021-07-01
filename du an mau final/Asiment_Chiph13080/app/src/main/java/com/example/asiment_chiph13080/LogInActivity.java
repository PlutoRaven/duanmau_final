package com.example.asiment_chiph13080;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.asiment_chiph13080.dao.ThuThuDAO;


public class LogInActivity extends AppCompatActivity {
    EditText et_username, et_pass;
    Button btn_dangnhap;
    Intent intent;
    ThuThuDAO thuthuDao;
    CheckBox checkBox;
    String strUser,strPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        et_username=findViewById(R.id.et_username);
        et_pass=findViewById(R.id.et_pass);
        btn_dangnhap=findViewById(R.id.btnLogin);
        checkBox=findViewById(R.id.chkRemember);
        thuthuDao=new ThuThuDAO(this);

        SharedPreferences preferences=getSharedPreferences("USER_FILE",MODE_PRIVATE);
        et_username.setText(preferences.getString("USERNAME",""));
        et_pass.setText(preferences.getString("PASSWORD",""));
        checkBox.setChecked(preferences.getBoolean("REMEMBER",false));

        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            checkLogin();
            }
        });

    }
    
    public void checkLogin(){
    strUser=et_username.getText().toString();
    strPass=et_pass.getText().toString();
    if (strUser.isEmpty()|| strPass.isEmpty()){
        Toast.makeText(getApplicationContext(),"Username and Password can not be empty",
                Toast.LENGTH_LONG).show();
    }
    else {
        if (thuthuDao.checkLogin(strUser,strPass)>0||(strUser.equals("admin")&&strPass.equals("admin"))){
            Toast.makeText(getApplicationContext(),"Login succeeded",Toast.LENGTH_LONG).show();
            rememberUser(strUser,strPass,checkBox.isChecked());
            intent=new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("user",strUser);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(),"Username or Password is not right",Toast.LENGTH_LONG).show();

        }
    }
    }
    
    public void rememberUser(String u,String p,boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor
                = pref.edit();
        if (!status) {
            // xóa tính năng trước đó
            editor.clear();
        } else {
            editor.putString("USERNAME", u);
            editor.putString("PASSWORD", p);
            editor.putBoolean("REMEMBER", true);
        }
        // lưu toàn bộ dữ liệu
        editor.commit();
    }

}