package com.example.asm_demo.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asm_demo.Modal.Phanloai;
import com.example.asm_demo.R;

import java.util.ArrayList;

public class Adapter_sp_thu extends BaseAdapter {

    ArrayList<Phanloai> ds_pl_thu;
    Context context;

    public Adapter_sp_thu(ArrayList<Phanloai> ds_pl_thu, Context context) {
        this.ds_pl_thu = ds_pl_thu;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ds_pl_thu.size();
    }

    @Override
    public Object getItem(int i) {
        return ds_pl_thu.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = ((Activity)context).getLayoutInflater().inflate(R.layout.item_sp_thu,viewGroup,false);
        TextView tv_sp_pl = view.findViewById(R.id.tv_sp_thu);
        tv_sp_pl.setText(ds_pl_thu.get(i).getTenloai());
        return view;
    }
}
