package com.example.asiment_chiph13080.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Sqldatabase extends SQLiteOpenHelper {
    public static final String dbName="PNLIB";
    public static final int dbVersion=12;

    public Sqldatabase(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //data thủ thư
        String createTableUser="create table User("+
                "maTT TEXT not null  PRIMARY KEY,"+
                "hoTen TEXT NOT NULL,"+
                "matKhau TEXT NOT NULL)";
        db.execSQL(createTableUser);
        // DATA THÀNH VIÊN
        String createTableMember="create table Member("+
                "maTV INTEGER not null PRIMARY KEY AUTOINCREMENT,"+
                "hoTen TEXT NOT NULL,"+
                "namSinh TEXT NOT NULL)";
        db.execSQL(createTableMember);
        //data loại sách
        String createTableTypeOfBook="create table TypeOfBook("+
                "maLoai INTEGER not null PRIMARY KEY AUTOINCREMENT,"+
                "tenLoai TEXT NOT NULL," +
                "nCC TEXT NOT NULL)";
        db.execSQL(createTableTypeOfBook);
        //data sách
        String createTableBook="create table Book("+
                "maSach INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
                "tenSach TEXT NOT NULL,"+
                "giaThue INTEGER NOT NULL,"+
                "maLoai INTEGER not null REFERENCES TypeOfBook(maLoai),"+
                "giamGia INTEGER NOT NULL,"+
                "tacGia TEXT NOT NULL)";;
        db.execSQL(createTableBook);
        // data phiếu mượn
        String createTableCallSlip="create table CallSlip("+
                "maPH  INTEGER not null PRIMARY KEY AUTOINCREMENT,"+
//                "maTT TEXT not null REFERENCES User(maTT),"+
                "maTV INTEGER not null REFERENCES Member(maTV),"+
                "maSach Integer not null REFERENCES Book(maSach),"+
                "tienThue Integer NOT NULL,"+
                "ngay text NOT NULL,"+
                "traSach integer NOT NULL)";
        db.execSQL(createTableCallSlip);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableUser ="drop table if exists User";
        db.execSQL(dropTableUser);
        String dropTableMember ="drop table if exists Member";
        db.execSQL(dropTableMember);
        String dropTableTypeOfBook ="drop table if exists TypeOfBook";
        db.execSQL(dropTableTypeOfBook);
        String dropTableBook ="drop table if exists Book";
        db.execSQL(dropTableBook);
        String dropTableCallSlip ="drop table if exists CallSlip";
        db.execSQL(dropTableCallSlip);
        onCreate(db);
    }
}
