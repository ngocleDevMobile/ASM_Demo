package com.example.asm_demo;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.example.asm_demo.Fragment.Fragment_phanloai;
import com.example.asm_demo.Fragment.Fragment_thongke;
import com.example.asm_demo.Fragment.Fragment_thu_chi;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout dr_ly;
    Toolbar tb;
    NavigationView nv;
    ActionBarDrawerToggle drawerToggle;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.colorOrange));


        dr_ly = findViewById(R.id.dr_ly);
        tb = findViewById(R.id.tg_bar);
        nv = findViewById(R.id.nv_view);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerToggle =setupDrawerToogle();

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        dr_ly.addDrawerListener(drawerToggle);
        if (savedInstanceState == null){
            nv.setCheckedItem(R.id.khoanthu);
            getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_thu_chi()).commit();
        }

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment fragments = null;
//                Class fragmentClass = null;
                switch (item.getItemId()){
                    case R.id.khoanthu:
//                        fragmentClass = Fragment_thu.class;
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_thu_chi()).commit();
                        break;
                    case R.id.khoanchi:
                       // fragmentClass = Fragment_chi.class;
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_phanloai()).commit();
                        break;
                    case R.id.thongke:
                        //fragmentClass = Fragment_thongke.class;
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_thongke()).commit();
                        break;
                    case R.id.gioithieu:
                        Toast.makeText(MainActivity.this, "Đây là : Giới thiệu", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.thoat:
                        Toast.makeText(MainActivity.this, "Đây là : Thoát", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_thu_chi()).commit();
                }
//                try {
//                    fragments = (Fragment) fragmentClass.newInstance();
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.fr_ly, fragments).commit();

                item.setChecked(true);
                setTitle(item.getTitle());
                dr_ly.closeDrawers();
                return true;
            }
        });

    }

    private ActionBarDrawerToggle setupDrawerToogle(){
        return new ActionBarDrawerToggle(MainActivity.this, dr_ly,tb, R.string.Open,R.string.Close);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}