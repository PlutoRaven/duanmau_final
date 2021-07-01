package com.example.asiment_chiph13080;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.example.asiment_chiph13080.dao.ThuThuDAO;

import com.example.asiment_chiph13080.frament.AddUserFragment;
import com.example.asiment_chiph13080.frament.ChangePassFragment;
import com.example.asiment_chiph13080.frament.DoanhThuFragment;
import com.example.asiment_chiph13080.frament.Loai_Sach_Fragment;
import com.example.asiment_chiph13080.frament.PhieuMuonFragment;
import com.example.asiment_chiph13080.frament.SachFragment;
import com.example.asiment_chiph13080.frament.Thanh_Vien_Fragment;
import com.example.asiment_chiph13080.frament.TopFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ListView listView;
    View mHeaderView;
    ThuThuDAO thuThuDAO;
    TextView edUser;
    FrameLayout fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        AnhXa();


        setSupportActionBar(toolbar);
        ActionBar ab=getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_action_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        FragmentManager manager=getSupportFragmentManager();
        NavigationView nv=findViewById(R.id.navigationview);
        mHeaderView=nv.getHeaderView(0);
        edUser=mHeaderView.findViewById(R.id.tvUSer);
        Intent i=getIntent();
        String user=i.getStringExtra("user");

        edUser.setText("Welcome "+user+"!");
        if (user.equalsIgnoreCase("admin")){
            nv.getMenu().findItem(R.id.sub_addUser).setVisible(true);
        }
        nv.setNavigationItemSelectedListener(item -> {
            FragmentManager manager1=getSupportFragmentManager();
            switch (item.getItemId()){
                case R.id.nav_PhieuMuon:
                    setTitle("Quản Lý Phiếu Mượn");
                    PhieuMuonFragment phieuMuonFragment1=new PhieuMuonFragment();
                    manager1.beginTransaction().replace(R.id.flConten,phieuMuonFragment1).commit();
                    break;
                case R.id.nav_LoaiSach:
                    setTitle("Quản Lý Loại Sách");
                    Loai_Sach_Fragment loai_sach_fragment=new Loai_Sach_Fragment();
                    manager1.beginTransaction().replace(R.id.flConten,loai_sach_fragment).commit();
                    break;
                case R.id.nav_Sach:
                    setTitle("Quản Lý Sách");
                    SachFragment sachFragment=new SachFragment();
                    manager1.beginTransaction().replace(R.id.flConten,sachFragment).commit();
                    break;
                case  R.id.nav_ThanhVien:
                    setTitle("Quản Lý Thành Viên");
                    Thanh_Vien_Fragment thanh_vien_fragment=new Thanh_Vien_Fragment();
                    manager1.beginTransaction().replace(R.id.flConten,thanh_vien_fragment).commit();
                    break;
                case R.id.sub_top:
                    setTitle("Top 10 sách cho thuê nhiều nhất");
                    TopFragment topFragment=new TopFragment();
                    manager1.beginTransaction().replace(R.id.flConten,topFragment).commit();
                    break;
                case R.id.sub_doanhthu:
                    setTitle("Thống kê doanh thu");
                    DoanhThuFragment doanhThuFragment=new DoanhThuFragment();
                    manager1.beginTransaction().replace(R.id.flConten,doanhThuFragment).commit();
                    break;
                case R.id.sub_addUser:
                    setTitle("Thêm người dùng");
                    AddUserFragment addUserFragment=new AddUserFragment();
                    manager1.beginTransaction().replace(R.id.flConten,addUserFragment).commit();
                    break;
                case R.id.sub_Pass:
                    setTitle("Thay đổi mật khẩu");
                    ChangePassFragment changePassFragment=new ChangePassFragment();
                    manager1.beginTransaction().replace(R.id.flConten,changePassFragment).commit();
                    break;
                case R.id.sub_logout:
                    startActivity(new Intent(getApplicationContext(),LogInActivity.class));
                    finish();
                    break;

            }
            drawerLayout.closeDrawers();
            return  false;
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void AnhXa(){
        fragment=findViewById(R.id.flConten);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.rgb(255, 182, 181));
        toolbar.setTitleTextColor(Color.rgb(18, 37, 67));
        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navigationview);
        listView=findViewById(R.id.lv);
    }
}