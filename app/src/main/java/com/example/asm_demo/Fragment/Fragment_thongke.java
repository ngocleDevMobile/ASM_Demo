package com.example.asm_demo.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.asm_demo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Fragment_thongke extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke, container, false);

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navigation);
        if (savedInstanceState == null){
            loadFragment(new Fragment_tk_thu());
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.bot_thu:
                       // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fr_, new Fragment_thongke()).commit();
                        fragment = new Fragment_tk_thu();
                        loadFragment(fragment);
                        break;
                    case R.id.bot_chi:
                        //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fr_, new Fragment_tk_chi()).commit();
                        fragment = new Fragment_tk_chi();
                        loadFragment(fragment);
                        break;
                }
                return false;
            }
        });

        return view;
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fr_, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
