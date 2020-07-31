package com.example.asm_demo.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_demo.DAO.Giaodich_DAO;
import com.example.asm_demo.Dialog.Bottom_sheet_edit_khoanthu;
import com.example.asm_demo.Modal.Giaodich;
import com.example.asm_demo.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.asm_demo.TabFragmnet.Tab_Khoanthu.khoanthu_adapter;
import static com.example.asm_demo.TabFragmnet.Tab_Khoanthu.rv_thu;

public class Tk_thu_Adapter extends RecyclerView.Adapter<Tk_thu_Adapter.MyViewHolder> {
    private ArrayList<Giaodich> ds_thu;
    private Context context;
    Giaodich_DAO giaodich_dao;

    public Tk_thu_Adapter(ArrayList<Giaodich> ds_thu, Context context) {
        this.ds_thu = ds_thu;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_tieude;
        public TextView tv_ngay;
        public TextView tv_tien;
        private ImageView img_xoa_thu;
        private ImageView img_edit_thu;
        public MyViewHolder(View v) {
            super(v);
            tv_tieude = v.findViewById(R.id.tv_tieude);
            tv_ngay = v.findViewById(R.id.tv_ngay);
            tv_tien = v.findViewById(R.id.tv_tien);
            img_xoa_thu = v.findViewById(R.id.img_xoa_thu);
            img_edit_thu = v.findViewById(R.id.img_edit_thu);
        }
    }

    @Override
    public Tk_thu_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khoan_thu, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_tieude.setText(ds_thu.get(position).getTieude());
        holder.tv_ngay.setText(ds_thu.get(position).getNgay()+"");
       //Dinh dang hien thi so tien
        DecimalFormat formatter = new DecimalFormat("#,###");
        String s = formatter.format(ds_thu.get(position).getTien());
        holder.tv_tien.setText(s);

    }
    @Override
    public int getItemCount() {
        return ds_thu.size();
    }

}

