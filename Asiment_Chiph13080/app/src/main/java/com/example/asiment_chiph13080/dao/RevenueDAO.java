package com.example.asiment_chiph13080.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asiment_chiph13080.database.Sqldatabase;
import com.example.asiment_chiph13080.model.Book;
import com.example.asiment_chiph13080.model.Top;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RevenueDAO {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat spd=new SimpleDateFormat("yyyy-MM-dd");

    public RevenueDAO(Context context) {
        this.context = context;
        Sqldatabase sqldatabase=new Sqldatabase(context);
        db=sqldatabase.getWritableDatabase();
    }

    public int getDoanhthu(String tuNgay,String denNgay){
        String sqlDoanhthu ="Select sum(tienThue) as doanhThu From CallSlip Where ngay BetWeen ? and ?";
        List<Integer>list=new ArrayList<Integer>();
        Cursor cursor=db.rawQuery(sqlDoanhthu,new String[]{tuNgay,denNgay});
        while (cursor.moveToNext()){
            try {
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThu"))));

            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }
    public List<Top> getTop(){
        String sqlTop ="Select maSach,count(maSach) as soLuong from CallSlip Group by maSach Order by soLuong desc limit 10";
        List<Top>list=new ArrayList<Top>();
        BookDAO bookDAO =new BookDAO(context);
        Cursor cursor=db.rawQuery(sqlTop,null);
        while (cursor.moveToNext()){
            Top top=new Top();
            Book book = bookDAO.getID(cursor.getString(cursor.getColumnIndex("maSach")));
            top.tenSach= book.tenSach;
            top.soLuong=Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuong")));
            list.add(top);
        }
        return list;
    }
}
