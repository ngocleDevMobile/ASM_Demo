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
import com.example.asm_demo.Dialog.Bottom_sheet_edit_khoanchi;
import com.example.asm_demo.Dialog.Bottom_sheet_edit_khoanthu;
import com.example.asm_demo.Modal.Giaodich;
import com.example.asm_demo.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.asm_demo.TabFragmnet.Tab_Khoanchi.khoanchi_adapter;
import static com.example.asm_demo.TabFragmnet.Tab_Khoanchi.rv_chi;

public class Khoanchi_Adapter extends RecyclerView.Adapter<Khoanchi_Adapter.MyViewHolder> {
    private ArrayList<Giaodich> ds_chi;
    private Context context;
    Giaodich_DAO giaodich_dao;

    public Khoanchi_Adapter(ArrayList<Giaodich> ds_chi, Context context) {
        this.ds_chi = ds_chi;
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
    public Khoanchi_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khoan_chi, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_tieude.setText(ds_chi.get(position).getTieude());
        holder.tv_ngay.setText(ds_chi.get(position).getNgay()+"");
       //Dinh dang hien thi so tien
        DecimalFormat formatter = new DecimalFormat("#,###");
        String s = formatter.format(ds_chi.get(position).getTien());
        holder.tv_tien.setText(s);
        holder.img_xoa_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Bạn có chắc muốn xóa "+ds_chi.get(position).getTieude());
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                giaodich_dao = new Giaodich_DAO(context);
                                giaodich_dao.delete(ds_chi.get(position).getMaGD());
                                Toast.makeText(context, "Xóa thành công "+ds_chi.get(position).getTieude(), Toast.LENGTH_SHORT).show();
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

        holder.img_edit_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("MaGD", ds_chi.get(position).getMaGD()+"");
                args.putString("Tieude", ds_chi.get(position).getTieude()+"");
                args.putString("Ngay", ds_chi.get(position).getNgay()+"");
                args.putString("MoTa", ds_chi.get(position).getMota()+"");
                args.putDouble("Tien", ds_chi.get(position).getTien());
                args.putString("Maloai", ds_chi.get(position).getMaloai()+"");

                Bottom_sheet_edit_khoanchi bottom_sheet = new Bottom_sheet_edit_khoanchi();
                //bottom_sheet.show(((AppCompatActivity)context).getSupportFragmentManager(),"TAG");
                bottom_sheet.setArguments(args);
                bottom_sheet.show(((AppCompatActivity) context).getSupportFragmentManager(),bottom_sheet.getTag());
            }
        });

    }
    @Override
    public int getItemCount() {
        return ds_chi.size();
    }

    public void capnhat(){
//        if (giaodich_dao.getTrangthai(ds_thu.get(index).getMaloai()).equals("Thu")){
////            ds_thu = giaodich_dao.getKhoanThu();
////        } else if (giaodich_dao.getTrangthai(ds_thu.get(index).getMaloai()).equals("Chi")){
////            ds_thu = giaodich_dao.getKhoanChi();
////        }

        ds_chi = giaodich_dao.getKhoanThu_Chi("Chi");
        khoanchi_adapter = new Khoanchi_Adapter(ds_chi, context);
        rv_chi.setAdapter(khoanchi_adapter);
    }


}

