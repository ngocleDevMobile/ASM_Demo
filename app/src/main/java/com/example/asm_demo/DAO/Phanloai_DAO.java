package com.example.asm_demo.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_demo.Modal.Phanloai;
import com.example.asm_demo.SQL.Database;

import java.util.ArrayList;

public class Phanloai_DAO {
    SQLiteDatabase db1;
    Database db;
    public Phanloai_DAO(Context context) {
        db = new Database(context);
    }

    public void them(Phanloai pl){
        db1 = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Tenloai", pl.getTenloai());
        values.put("Trangthai", pl.getTrangthai());
        db1.insert("LOAI_TC",null,values);
    }

    public ArrayList<Phanloai> getAll(){
        ArrayList<Phanloai> ds_pl = new ArrayList<>();
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.query("LOAI_TC", null,null,null,null,null,null);
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

    public void delete(int id){
        db1 = db.getWritableDatabase();
        db1.delete("LOAI_TC","Maloai=?", new String[]{id+""});
    }
    public void update(Phanloai pl){
        db1 = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Tenloai", pl.getTenloai());
        values.put("Trangthai", pl.getTrangthai());
        db1.update("LOAI_TC",values, "Maloai=?", new String[]{pl.getId_pl()+""});
    }
}
