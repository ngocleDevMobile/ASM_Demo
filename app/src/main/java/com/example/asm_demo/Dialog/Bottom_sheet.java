package com.example.asm_demo.Dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.asm_demo.Adapter.Phanloai_Adapter;
import com.example.asm_demo.DAO.Phanloai_DAO;
import com.example.asm_demo.Modal.Phanloai;
import com.example.asm_demo.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import static com.example.asm_demo.Fragment.Fragment_phanloai.phanloai_adapters;
import static com.example.asm_demo.Fragment.Fragment_phanloai.rv_phanloai;

public class Bottom_sheet extends BottomSheetDialogFragment {
    EditText edt_tenloai;
    Spinner sp_loai;
    Button btn_them;
    Phanloai_DAO phanloai_dao;
    ArrayList<Phanloai> ds_phanloai;
    public Bottom_sheet(){
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_phanloai, container, false);
        edt_tenloai = view.findViewById(R.id.edt_tenloai);
        sp_loai = view.findViewById(R.id.sp_phanloai);
        btn_them = view.findViewById(R.id.btn_thempl);
        phanloai_dao = new Phanloai_DAO(getContext());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_loai.setAdapter(adapter);

        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten_loai = edt_tenloai.getText().toString();
                String phanloai = sp_loai.getSelectedItem().toString();
                Phanloai pl = new Phanloai(ten_loai,phanloai);
                phanloai_dao.them(pl);
                capnhat();
                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });


        return view;
    }


    public void capnhat(){
        ds_phanloai = new ArrayList<>();
        ds_phanloai = phanloai_dao.getAll();
        phanloai_adapters = new Phanloai_Adapter(ds_phanloai, getContext());
        rv_phanloai.setAdapter(phanloai_adapters);
    }
}
