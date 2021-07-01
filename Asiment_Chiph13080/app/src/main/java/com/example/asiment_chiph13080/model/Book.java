package com.example.asiment_chiph13080.model;

public class Book {
    public int maSach;
    public String tenSach;
    public int giaThue;
    public int maLoai;
    public int giamGia;
    public String tacGia;

    public Book(int maSach, String tenSach, int giaThue, int maLoai, int giamGia) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaThue = giaThue;
        this.maLoai = maLoai;
        this.giamGia = giamGia;
        this.tacGia = tacGia;
    }

    public Book() {
    }
}
