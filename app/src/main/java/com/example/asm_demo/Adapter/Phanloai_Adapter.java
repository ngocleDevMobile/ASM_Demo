package com.example.asm_demo.Adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_demo.DAO.Phanloai_DAO;
import com.example.asm_demo.Dialog.Bottom_sheet;
import com.example.asm_demo.Dialog.Bottom_sheet_edit_pl;
import com.example.asm_demo.Modal.Phanloai;
import com.example.asm_demo.R;

import java.util.ArrayList;

import static com.example.asm_demo.Fragment.Fragment_phanloai.phanloai_adapters;
import static com.example.asm_demo.Fragment.Fragment_phanloai.rv_phanloai;

public class Phanloai_Adapter extends RecyclerView.Adapter<Phanloai_Adapter.MyViewHolder> {
    private ArrayList<Phanloai> ds_phanloai;
    private Context context;
    Phanloai_DAO phanloai_dao;

    public Phanloai_Adapter(ArrayList<Phanloai> ds_phanloai, Context context) {
        this.ds_phanloai = ds_phanloai;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_ten;
        public TextView tv_trangthai;
        private ImageView img_xoa_pl;
        private ImageView img_edit_pl;
        public MyViewHolder(View v) {
            super(v);
            tv_ten = v.findViewById(R.id.tv_tenloai);
            tv_trangthai = v.findViewById(R.id.tv_trangthai);
            img_xoa_pl = v.findViewById(R.id.img_xoa_pl);
            img_edit_pl = v.findViewById(R.id.img_edit_pl);
        }
    }


    @Override
    public Phanloai_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phanloai, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_ten.setText(ds_phanloai.get(position).getTenloai());
        holder.tv_trangthai.setText(ds_phanloai.get(position).getTrangthai());
        holder.img_xoa_pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Bạn có chắc muốn xóa "+ds_phanloai.get(position).getTenloai());
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                phanloai_dao = new Phanloai_DAO(context);
                                phanloai_dao.delete(ds_phanloai.get(position).getId_pl());
                                Toast.makeText(context, "Xóa thành công "+ds_phanloai.get(position).getTenloai(), Toast.LENGTH_SHORT).show();
                                capnhat();
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        holder.img_edit_pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("id", ds_phanloai.get(position).getId_pl()+"");
                args.putString("tenloai", ds_phanloai.get(position).getTenloai()+"");
                args.putString("trangthai", ds_phanloai.get(position).getTrangthai()+"");

                Bottom_sheet_edit_pl bottom_sheet = new Bottom_sheet_edit_pl();
                //bottom_sheet.show(((AppCompatActivity)context).getSupportFragmentManager(),"TAG");
                bottom_sheet.setArguments(args);
                bottom_sheet.show(((AppCompatActivity) context).getSupportFragmentManager(),bottom_sheet.getTag());
            }
        });

    }
    @Override
    public int getItemCount() {
        return ds_phanloai.size();
    }
    public void capnhat(){
        ds_phanloai = phanloai_dao.getAll();
        phanloai_adapters = new Phanloai_Adapter(ds_phanloai, context);
        rv_phanloai.setAdapter(phanloai_adapters);
    }
}

