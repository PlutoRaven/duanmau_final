package com.example.asiment_chiph13080.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asiment_chiph13080.database.Sqldatabase;
import com.example.asiment_chiph13080.model.PhieuMuon;


import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {
    private SQLiteDatabase db;
    public PhieuMuonDAO(Context context){
        Sqldatabase sqLiteDatabase=new Sqldatabase(context);
        db=sqLiteDatabase.getWritableDatabase();
    }
    public long insert(PhieuMuon phieuMuon){
        ContentValues contentValues=new ContentValues();
        contentValues.put("maTV",phieuMuon.maTV);
        contentValues.put("maSach",phieuMuon.maSach);
        contentValues.put("ngay",phieuMuon.ngay);
        contentValues.put("tienThue",phieuMuon.tienThue);
        contentValues.put("traSach",phieuMuon.traSach);

        return db.insert("PhieuMuon",null,contentValues);

    }
    public int update(PhieuMuon phieuMuon){
        ContentValues contentValues=new ContentValues();
        contentValues.put("ngay",phieuMuon.ngay);
        contentValues.put("tienThue",phieuMuon.tienThue);
        contentValues.put("traSach",phieuMuon.traSach);
        return db.update("PhieuMuon",contentValues,"maPH=?",new String[]{String.valueOf(phieuMuon.maPH)});
    }
    public int delete(String id){
        return db.delete("PhieuMuon","maPH=?",new String[]{id});
    }
    public List<PhieuMuon> getAll(){
        String sql="select *from PhieuMuon";
        return getData(sql);
    }
    public PhieuMuon getID(String id){
        String sql="select*from PhieuMuon Where maPH=?";
        List<PhieuMuon>list=getData(sql,id);
        return list.get(0);
    }
    private List<PhieuMuon>getData(String sql,String...selectionArgs){
        List<PhieuMuon>list=new ArrayList<>();
        Cursor cursor=db.rawQuery(sql,selectionArgs);
//        Cursor cursor=db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            PhieuMuon phieuMuon=new PhieuMuon();
            phieuMuon.maPH=Integer.parseInt(cursor.getString(cursor.getColumnIndex("maPH")));
//            phieuMuon.maTT = cursor.getString(cursor.getColumnIndex("maTT"));
            phieuMuon.maTV = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maTV")));
            phieuMuon.maSach = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSach")));
            phieuMuon.ngay = cursor.getString(cursor.getColumnIndex("ngay"));
            phieuMuon.tienThue=Integer.parseInt(cursor.getString(cursor.getColumnIndex("tienThue")));
            phieuMuon.traSach=Integer.parseInt(cursor.getString(cursor.getColumnIndex("traSach")));

            list.add(phieuMuon);
        }
        return list;
    }
}
