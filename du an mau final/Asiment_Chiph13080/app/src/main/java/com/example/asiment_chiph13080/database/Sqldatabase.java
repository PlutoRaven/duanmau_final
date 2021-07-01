package com.example.asiment_chiph13080.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Sqldatabase extends SQLiteOpenHelper {
    public static final String dbName="PNLIB";
    public static final int dbVersion=2;

    public Sqldatabase(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableThuThu="create table ThuThu("+
                "maTT TEXT not null  PRIMARY KEY,"+
                "hoTen TEXT NOT NULL,"+
                "matKhau TEXT NOT NULL)";
        db.execSQL(createTableThuThu);

    // DATA THÀNH VIÊN
        String createTableThanhVien="create table ThanhVien("+
                "maTV INTEGER not null PRIMARY KEY AUTOINCREMENT,"+
                "hoTen TEXT NOT NULL,"+
                "namSinh TEXT NOT NULL)";
        db.execSQL(createTableThanhVien);
        //data loại sách
        String createTableLoaiSach="create table LoaiSach("+
                "maLoai INTEGER not null PRIMARY KEY AUTOINCREMENT,"+
                "tenLoai TEXT NOT NULL)";
        db.execSQL(createTableLoaiSach);
        //data sách
        String createTableSach="create table Sach("+
                "maSach INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
                "tenSach TEXT NOT NULL,"+
                "giaThue INTEGER NOT NULL,"+
                "maLoai INTEGER not null REFERENCES LoaiSach(maLoai))";
        db.execSQL(createTableSach);
        // data phiếu mượn
        String createTablePhieuMuon="create table PhieuMuon("+
                "maPH  INTEGER not null PRIMARY KEY AUTOINCREMENT,"+
//                "maTT TEXT not null REFERENCES ThuThu(maTT),"+
                "maTV INTEGER not null REFERENCES ThanhVien(maTV),"+
                "maSach Integer not null REFERENCES Sach(maSach),"+
                "tienThue Integer NOT NULL,"+
                "ngay text NOT NULL,"+
                "traSach integer NOT NULL)";
        db.execSQL(createTablePhieuMuon);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableThuThu ="drop table if exists ThuThu";
        db.execSQL(dropTableThuThu);
        String dropTableThanhVien ="drop table if exists ThanhVien";
        db.execSQL(dropTableThanhVien);
        String dropTableLoaiSach ="drop table if exists LoaiSach";
        db.execSQL(dropTableLoaiSach);
        String dropTableSach ="drop table if exists Sach";
        db.execSQL(dropTableSach);
        String dropTablePhieuMuon ="drop table if exists PhieuMuon";
        db.execSQL(dropTablePhieuMuon);
        onCreate(db);
    }
}
