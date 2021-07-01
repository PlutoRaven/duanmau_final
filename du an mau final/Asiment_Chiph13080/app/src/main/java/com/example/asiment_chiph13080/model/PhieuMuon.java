package com.example.asiment_chiph13080.model;

public class PhieuMuon {
    public int maPH;
    public String maTT;
    public int maTV;
    public int maSach;
    public String ngay;
    public int tienThue;
    public int traSach;

    public PhieuMuon(int maPH, String maTT, int maTV, int maSach, String ngay, int tienThue, int traSach) {
        this.maPH = maPH;
        this.maTT = maTT;
        this.maTV = maTV;
        this.maSach = maSach;
        this.ngay = ngay;
        this.tienThue = tienThue;
        this.traSach = traSach;
    }

    public PhieuMuon() {
    }
}
