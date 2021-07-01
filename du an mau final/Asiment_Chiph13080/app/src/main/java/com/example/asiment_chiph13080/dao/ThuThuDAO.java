package com.example.asiment_chiph13080.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.asiment_chiph13080.database.Sqldatabase;
import com.example.asiment_chiph13080.model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDAO {
    SQLiteDatabase db;
    public ThuThuDAO(Context context){
        Sqldatabase sqldatabase=new Sqldatabase(context);
        db=sqldatabase.getWritableDatabase();
    }
    public long insert(ThuThu thuThu){
        ContentValues contentValues=new ContentValues();
        contentValues.put("maTT",thuThu.maTT);
        contentValues.put("hoTen",thuThu.hoTen);
        contentValues.put("matKhau",thuThu.matKhau);
        return db.insert("ThuThu",null,contentValues);
    }
    public int update(ThuThu thuThu){
        ContentValues contentValues=new ContentValues();
        contentValues.put("maTT",thuThu.maTT);
        contentValues.put("hoTen",thuThu.hoTen);
        contentValues.put("matKhau",thuThu.matKhau);
        return db.update("ThuThu",contentValues,"maTT=?",new String[]{String.valueOf(thuThu.maTT)});
    }
    public  int delete(String id){
        return  db.delete("ThuThu","maTT=?",new String[]{id});
    }
    public List<ThuThu>getAll(){
        String sql="Select *from ThuThu";
        return getData(sql);
    }
    public  ThuThu getID (String id){
        String sql="select * from ThuThu where maTT=?";
        List<ThuThu> list=getData(sql,id);
        return list.get(0);
    }
    private List<ThuThu>getData(String sql,String ...selectionArgs){
        List<ThuThu>list=new ArrayList<>();
        Cursor cursor=db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            ThuThu thuThu=new ThuThu();
            thuThu.maTT=cursor.getString(cursor.getColumnIndex("maTT"));
            thuThu.hoTen=cursor.getString(cursor.getColumnIndex("hoTen"));
            thuThu.matKhau=cursor.getString(cursor.getColumnIndex("matKhau"));
            list.add(thuThu);
        }
        return list;
    }
    public  int checkLogin(String id,String password){
        String sql="select * from ThuThu where maTT=? and matKhau=?";
        List<ThuThu>list=getData(sql,id,password);
        if (list.size()==0){
            return -1;
        }
        return 1;
    }
}
