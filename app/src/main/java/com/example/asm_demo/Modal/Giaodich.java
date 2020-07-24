package com.example.asm_demo.Modal;

import java.util.Date;

public class Giaodich {
    private int MaGD;
    private String Tieude;
    private String Ngay;
    private double Tien;
    private String Mota;
    private int Maloai;

    public Giaodich(String tieude, String ngay, double tien, String mota, int maloai) {
        Tieude = tieude;
        Ngay = ngay;
        Tien = tien;
        Mota = mota;
        Maloai = maloai;
    }

    public Giaodich() {
    }

    public Giaodich(int maGD, String tieude, String ngay, double tien, String mota, int maloai) {
        MaGD = maGD;
        Tieude = tieude;
        Ngay = ngay;
        Tien = tien;
        Mota = mota;
        Maloai = maloai;
    }

    public int getMaGD() {
        return MaGD;
    }

    public void setMaGD(int maGD) {
        MaGD = maGD;
    }

    public String getTieude() {
        return Tieude;
    }

    public void setTieude(String tieude) {
        Tieude = tieude;
    }

    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String ngay) {
        Ngay = ngay;
    }

    public double getTien() {
        return Tien;
    }

    public void setTien(double tien) {
        Tien = tien;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }

    public int getMaloai() {
        return Maloai;
    }

    public void setMaloai(int maloai) {
        Maloai = maloai;
    }
}
