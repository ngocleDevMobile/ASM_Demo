package com.example.asm_demo.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_demo.Adapter.Phanloai_Adapter;
import com.example.asm_demo.DAO.Phanloai_DAO;
import com.example.asm_demo.Dialog.Bottom_sheet;
import com.example.asm_demo.Modal.Phanloai;
import com.example.asm_demo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Fragment_phanloai extends Fragment {
    FloatingActionButton fl_phanloai;
    public static Phanloai_Adapter phanloai_adapters;
    Phanloai_DAO phanloai_dao;
    public static RecyclerView rv_phanloai;
    ArrayList<Phanloai> ds_phanloai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phanloai, container, false);
        fl_phanloai = view.findViewById(R.id.fl_pl);
        rv_phanloai = view.findViewById(R.id.rv_pl);
        rv_phanloai.setLayoutManager(new LinearLayoutManager(getContext()));
        ds_phanloai = new ArrayList<>();
        phanloai_dao = new Phanloai_DAO(getContext());

        fl_phanloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bottom_sheet bottom_sheet = new Bottom_sheet();
                bottom_sheet.show(getFragmentManager(),"TAG");
            }
        });
        ds_phanloai = phanloai_dao.getAll();
        phanloai_adapters = new Phanloai_Adapter(ds_phanloai, getContext());
        rv_phanloai.setAdapter(phanloai_adapters);


        return view;
    }

}
