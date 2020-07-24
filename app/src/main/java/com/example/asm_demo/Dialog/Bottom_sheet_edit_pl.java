package com.example.asm_demo.Dialog;

import android.content.pm.PackageManager;
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

public class Bottom_sheet_edit_pl extends BottomSheetDialogFragment {
    EditText edt_tenloai_edit;
    Spinner sp_loai_edit;
    Button btn_update_pl;
    Phanloai_DAO phanloai_dao;
    ArrayList<Phanloai> ds_phanloai;
    int id;
    public Bottom_sheet_edit_pl(){
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_edit_phanloai, container, false);
        edt_tenloai_edit = view.findViewById(R.id.edt_tenloai_edit);
        sp_loai_edit = view.findViewById(R.id.sp_phanloai_edit);
        btn_update_pl = view.findViewById(R.id.btn_update_pl);
        phanloai_dao = new Phanloai_DAO(getContext());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_loai_edit.setAdapter(adapter);


        Bundle mArgs = getArguments();
        id = Integer.parseInt(mArgs.getString("id"));
        String ten_loai = mArgs.getString("tenloai");
        String trang_thai = mArgs.getString("trangthai");
        selectSpinnerValue(sp_loai_edit, trang_thai);

        edt_tenloai_edit.setText(ten_loai);


        btn_update_pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ten_loais = edt_tenloai_edit.getText().toString();
                String phanloais = sp_loai_edit.getSelectedItem().toString();
                Phanloai pl = new Phanloai(id,ten_loais,phanloais);
                phanloai_dao.update(pl);
                capnhat();
                Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });


        return view;
    }

    private void selectSpinnerValue(Spinner spinner, String myString)
    {
        int index = 0;
        for(int i = 0; i < spinner.getCount(); i++){
            if(spinner.getItemAtPosition(i).toString().equals(myString)){
                spinner.setSelection(i);
                break;
            }
        }
    }


    public void capnhat(){
        ds_phanloai = new ArrayList<>();
        ds_phanloai = phanloai_dao.getAll();
        phanloai_adapters = new Phanloai_Adapter(ds_phanloai, getContext());
        rv_phanloai.setAdapter(phanloai_adapters);
    }


}
