package com.example.asiment_chiph13080;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.example.asiment_chiph13080.fragment.AddUserFragment;
import com.example.asiment_chiph13080.fragment.ChangePassFragment;
import com.example.asiment_chiph13080.fragment.RevenueFragment;
import com.example.asiment_chiph13080.fragment.TypeOfBookFragment;
import com.example.asiment_chiph13080.fragment.CallSlipFragment;
import com.example.asiment_chiph13080.fragment.BookFragment;
import com.example.asiment_chiph13080.fragment.MemberFragment;
import com.example.asiment_chiph13080.fragment.TopFragment;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ListView listView;
    View mHeaderView;
    TextView edUser;
    FrameLayout fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment=findViewById(R.id.flContent);

        toolbar=findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.rgb(255, 182, 181));
        toolbar.setTitleTextColor(Color.rgb(18, 37, 67));

        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navigationview);
        listView=findViewById(R.id.lv);

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
        if (user.equalsIgnoreCase("Admin")){
            nv.getMenu().findItem(R.id.sub_addUser).setVisible(true);
        }
        nv.setNavigationItemSelectedListener(item -> {
            FragmentManager manager1=getSupportFragmentManager();
            switch (item.getItemId()){
                case R.id.nav_PhieuMuon:
                    setTitle("Call Slip Management");
                    CallSlipFragment callSlipFragment1 =new CallSlipFragment();
                    manager1.beginTransaction().replace(R.id.flContent, callSlipFragment1).commit();
                    break;
                case R.id.nav_LoaiSach:
                    setTitle("Type Of Book Management");
                    TypeOfBookFragment typeOfBook_fragment =new TypeOfBookFragment();
                    manager1.beginTransaction().replace(R.id.flContent, typeOfBook_fragment).commit();
                    break;
                case R.id.nav_Sach:
                    setTitle("Book Management");
                    BookFragment bookFragment =new BookFragment();
                    manager1.beginTransaction().replace(R.id.flContent, bookFragment).commit();

                    break;
                case  R.id.nav_ThanhVien:
                    setTitle("Member Mangagement");
                    MemberFragment member_fragment =new MemberFragment();
                    manager1.beginTransaction().replace(R.id.flContent, member_fragment).commit();

                    break;
                case R.id.sub_top:
                    setTitle("Top 10 Book");
                    TopFragment topFragment=new TopFragment();
                    manager1.beginTransaction().replace(R.id.flContent,topFragment).commit();

                    break;
                case R.id.sub_doanhthu:
                    setTitle("Revenue");
                    RevenueFragment revenueFragment =new RevenueFragment();
                    manager1.beginTransaction().replace(R.id.flContent, revenueFragment).commit();

                    break;
                case R.id.sub_addUser:
                    setTitle("Add User");
                    AddUserFragment addUserFragment=new AddUserFragment();
                    manager1.beginTransaction().replace(R.id.flContent,addUserFragment).commit();

                    break;
                case R.id.sub_Pass:
                    setTitle("Change Password");
                    ChangePassFragment changePassFragment=new ChangePassFragment();
                    manager1.beginTransaction().replace(R.id.flContent,changePassFragment).commit();

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




}