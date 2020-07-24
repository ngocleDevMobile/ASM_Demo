package com.example.asm_demo.Modal;

public class Phanloai {
    private int id_pl;
    private String tenloai;
    private String trangthai;

    public Phanloai() {
    }

    public Phanloai(int id_pl, String tenloai, String trangthai) {
        this.id_pl = id_pl;
        this.tenloai = tenloai;
        this.trangthai = trangthai;
    }

    public Phanloai(int id_pl, String tenloai) {
        this.id_pl = id_pl;
        this.tenloai = tenloai;
    }

    public Phanloai(String tenloai, String trangthai) {
        this.tenloai = tenloai;
        this.trangthai = trangthai;
    }

    public int getId_pl() {
        return id_pl;
    }

    public void setId_pl(int id_pl) {
        this.id_pl = id_pl;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}
