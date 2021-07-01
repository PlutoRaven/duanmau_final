package com.example.asiment_chiph13080.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asiment_chiph13080.database.Sqldatabase;
import com.example.asiment_chiph13080.model.LoaiSach;


import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {
    private SQLiteDatabase db;
    public LoaiSachDAO(Context context){
        Sqldatabase sqldatabase=new Sqldatabase(context);
        db=sqldatabase.getWritableDatabase();
    }
    public long insert(LoaiSach loaiSach){
        ContentValues contentValues=new ContentValues();
        contentValues.put("tenLoai",loaiSach.tenLoai);
        return db.insert("LoaiSach",null,contentValues);

    }
    public int update(LoaiSach loaiSach){
        ContentValues contentValues=new ContentValues();
        contentValues.put("tenLoai",loaiSach.tenLoai);
        return db.update("LoaiSach",contentValues,"maLoai=?",new String[]{String.valueOf(loaiSach.maLoai)});
    }
    public int delete(String id){
       return db.delete("LoaiSach","maLoai=?",new String[]{id});
    }
    public List<LoaiSach>getAll(){
        String sql="Select * from LoaiSach";
        return getData(sql);
    }
    public LoaiSach getID(String id){

        String sql="select * from LoaiSach where maLoai=?";
        List<LoaiSach>list=getData(sql,id);
   return list.get(0);
    }
    private List<LoaiSach> getData(String sql,String...selectionArgs){
        List<LoaiSach>list =new ArrayList<>();
        Cursor cursor=db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            LoaiSach loaiSach=new LoaiSach();
            loaiSach.maLoai=Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai")));
            loaiSach.tenLoai=cursor.getString(cursor.getColumnIndex("tenLoai"));
            list.add(loaiSach);

        }
        return list;

    }


}
