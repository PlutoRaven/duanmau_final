package com.example.asiment_chiph13080.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asiment_chiph13080.database.Sqldatabase;
import com.example.asiment_chiph13080.model.ThanhVien;


import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO {
    private SQLiteDatabase db;
    public ThanhVienDAO(Context context){
        Sqldatabase sqldatabase=new Sqldatabase(context);
        db=sqldatabase.getWritableDatabase();

    }
    public long insert (ThanhVien thanhVien){
        ContentValues values=new ContentValues();
        values.put("hoTen",thanhVien.hoTen);
        values.put("namSinh",thanhVien.namSinh);

        return db.insert("ThanhVien",null,values);

    }
    public int update(ThanhVien thanhVien){
        ContentValues contentValues=new ContentValues();
        contentValues.put("hoTen",thanhVien.hoTen);
        contentValues.put("namSinh",thanhVien.namSinh);

        return  db.update("ThanhVien",contentValues,"maTV=?",new String[]{String.valueOf(thanhVien.maTV)});

    }
    public int delete(String id){
        return db.delete("ThanhVien","maTV=?",new String[]{id});

    }
    // get tất cả data
    public List<ThanhVien>getAll(){
        String sql="Select *from ThanhVien";
        return getData(sql);
    }
    // get data theo id
    public ThanhVien getID(String id){
        String sql="Select * from ThanhVien Where maTV=?";
        List<ThanhVien> list=getData(sql,id);
        return list.get(0);
    }
    private List<ThanhVien>getData(String sql,String...selectionArgs){
        List<ThanhVien>list =new ArrayList<>();
        Cursor cursor=db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            ThanhVien thanhVien=new ThanhVien();
            thanhVien.maTV=Integer.parseInt(cursor.getString(cursor.getColumnIndex("maTV")));
            thanhVien.hoTen=cursor.getString(cursor.getColumnIndex("hoTen"));
            thanhVien.namSinh=cursor.getString(cursor.getColumnIndex("namSinh"));
            list.add(thanhVien);
        }
        return list;
    }
}
