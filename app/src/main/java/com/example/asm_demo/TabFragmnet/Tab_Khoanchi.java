package com.example.asm_demo.TabFragmnet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_demo.Adapter.Khoanchi_Adapter;
import com.example.asm_demo.DAO.Giaodich_DAO;
import com.example.asm_demo.Dialog.Bottom_sheet_them_chi;
import com.example.asm_demo.Dialog.Bottom_sheet_them_thu;
import com.example.asm_demo.Modal.Giaodich;
import com.example.asm_demo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Tab_Khoanchi extends Fragment {
    FloatingActionButton fl_khoanchi;
    public static RecyclerView rv_chi;
    public static Khoanchi_Adapter khoanchi_adapter;
    public static ArrayList<Giaodich> ds_khoanchi;
    Giaodich_DAO giaodich_dao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_khoan_chi, container, false);
        rv_chi = view.findViewById(R.id.rv_khoan_chi);
        rv_chi.setLayoutManager(new LinearLayoutManager(getContext()));
        fl_khoanchi = view.findViewById(R.id.fl_khoan_chi);
        fl_khoanchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("trangthai", "Loai chi");

                Bottom_sheet_them_chi bottom_sheet = new Bottom_sheet_them_chi();
                //bottom_sheet.show(((AppCompatActivity)context).getSupportFragmentManager(),"TAG");
                bottom_sheet.setArguments(args);
                bottom_sheet.show(getFragmentManager(),bottom_sheet.getTag());
            }
        });

        ds_khoanchi = new ArrayList<>();
        giaodich_dao = new Giaodich_DAO(getContext());

        ds_khoanchi = giaodich_dao.getKhoanThu_Chi("Chi");
        khoanchi_adapter = new Khoanchi_Adapter(ds_khoanchi, getContext());
        rv_chi.setAdapter(khoanchi_adapter);

        return view;
    }
}
