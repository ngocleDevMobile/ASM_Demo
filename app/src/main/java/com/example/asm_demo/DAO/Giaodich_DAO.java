package com.example.asm_demo.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_demo.Modal.Giaodich;
import com.example.asm_demo.Modal.Phanloai;
import com.example.asm_demo.SQL.Database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Giaodich_DAO {
    SQLiteDatabase db1;
    Database db;


    public Giaodich_DAO(Context context) {
        db = new Database(context);
    }

    public void them(Giaodich gd) {
        db1 = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Tieude", gd.getTieude());
        values.put("Ngay", gd.getNgay());
        values.put("Tien", gd.getTien());
        values.put("Mota", gd.getMota());
        values.put("Maloai", gd.getMaloai());

        db1.insert("GIAO_DICH",null,values);
    }

    public ArrayList<Phanloai> getThu(){
        ArrayList<Phanloai> ds_pl = new ArrayList<>();
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT * FROM LOAI_TC WHERE Trangthai LIKE 'Thu'", null);
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String tenloai = cursor.getString(1);
                String trangthai = cursor.getString(2);
                ds_pl.add(new Phanloai(id,tenloai,trangthai));
            } while (cursor.moveToNext());
        }
        return ds_pl;
    }

    public ArrayList<Phanloai> getChi(){
        ArrayList<Phanloai> ds_pl = new ArrayList<>();
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT * FROM LOAI_TC WHERE Trangthai = '"+"Chi"+"'", null);
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String tenloai = cursor.getString(1);
                String trangthai = cursor.getString(2);
                ds_pl.add(new Phanloai(id,tenloai,trangthai));
            } while (cursor.moveToNext());
        }
        return ds_pl;
    }

    public String getTen(String id){
        String ten = "";
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT Tenloai FROM LOAI_TC WHERE Maloai = '"+id+"'",null);
        if (cursor.moveToFirst()){
            do {
                ten = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        return ten;
    }
    public String getBetweenDay(String date1, String date2){
           String total= "";
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT SUM(tien) FROM GIAO_DICH WHERE ngay BETWEEN '2020-07-20' AND '2020-07-21'", null);
        if (cursor.moveToFirst()){
            do {
                total = cursor.getString(0);
            } while (cursor.moveToNext());
        }

           return total;
    }

    public ArrayList<Giaodich> getKhoanThu(){
        ArrayList<Giaodich> ds_giaodich = new ArrayList<>();
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT * FROM GIAO_DICH INNER JOIN LOAI_TC ON GIAO_DICH.Maloai = LOAI_TC.Maloai AND LOAI_TC.trangthai LIKE 'Thu'",null);
        if (cursor.moveToFirst()){
            do {
              Date date1 = null;
                    int Magiaodich = cursor.getInt(0);
                    String Tieude = cursor.getString(1);
                    String Ngay = cursor.getString(2);
                    double Tien = Double.parseDouble(cursor.getString(3));
                    String Mota = cursor.getString(4);
                    int Maloai = cursor.getInt(5);
                    ds_giaodich.add(new Giaodich(Magiaodich,Tieude,Ngay,Tien,Mota,Maloai));
            } while (cursor.moveToNext());
        }
        return ds_giaodich;
    }

    public String getTrangthai(int id){
        String ten = "";
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT trangthai FROM LOAI_TC INNER JOIN GIAO_DICH ON LOAI_TC.Maloai = id",null);
        if (cursor.moveToFirst()){
            do {
                ten = cursor.getString(0);

            }while (cursor.moveToNext());
        }
        return ten;
    }

    public ArrayList<Giaodich> getKhoanChi(){
        ArrayList<Giaodich> ds_giaodich = new ArrayList<>();
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT * FROM GIAO_DICH INNER JOIN LOAI_TC ON GIAO_DICH.Maloai = LOAI_TC.Maloai AND LOAI_TC.trangthai LIKE 'Chi'",null);
        if (cursor.moveToFirst()){
            do {
                Date date1 = null;
                int Magiaodich = cursor.getInt(0);
                String Tieude = cursor.getString(1);
                String Ngay = cursor.getString(2);
                double Tien = Double.parseDouble(cursor.getString(3));
                String Mota = cursor.getString(4);
                int Maloai = cursor.getInt(5);
                ds_giaodich.add(new Giaodich(Magiaodich,Tieude,Ngay,Tien,Mota,Maloai));
            } while (cursor.moveToNext());
        }
        return ds_giaodich;
    }

    public void delete(int id){
        db1 = db.getWritableDatabase();
        db1.delete("GIAO_DICH","MaGD=?", new String[]{id+""});
    }
    public void update(Giaodich gd){
        db1 = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Tieude", gd.getTieude());
        values.put("Ngay", gd.getNgay());
        values.put("Tien", gd.getTien());
        values.put("Mota", gd.getMota());
        values.put("Maloai", gd.getMaloai());
        db1.update("GIAO_DICH",values, "MaGD=?", new String[]{gd.getMaGD()+""});
    }
}
