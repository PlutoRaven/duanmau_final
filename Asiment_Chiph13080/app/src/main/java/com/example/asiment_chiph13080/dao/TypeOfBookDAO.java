package com.example.asiment_chiph13080.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asiment_chiph13080.database.Sqldatabase;
import com.example.asiment_chiph13080.model.TypeOfBook;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TypeOfBookDAO {
    private SQLiteDatabase db;
    public TypeOfBookDAO(Context context){
        Sqldatabase sqldatabase=new Sqldatabase(context);
        db=sqldatabase.getWritableDatabase();
    }
    public long insert(TypeOfBook TypeOfBook){
        ContentValues contentValues=new ContentValues();
        contentValues.put("tenLoai", TypeOfBook.tenLoai);
        contentValues.put("nCC", TypeOfBook.nCC);
        return db.insert("TypeOfBook",null,contentValues);

    }
    public int update(TypeOfBook TypeOfBook){
        ContentValues contentValues=new ContentValues();
        contentValues.put("tenLoai", TypeOfBook.tenLoai);
        contentValues.put("nCC", TypeOfBook.nCC);
        return db.update("TypeOfBook",contentValues,"maLoai=?",new String[]{String.valueOf(TypeOfBook.maLoai)});
    }
    public int delete(String id){
       return
               db.delete("TypeOfBook","maLoai=?",new String[]{id});
    }
    public List<TypeOfBook>getAll(){
        String sql="Select * from TypeOfBook";
        return getData(sql);
    }
    public TypeOfBook getID(String id){
        String sql="select * from TypeOfBook where maLoai=?";
        List<TypeOfBook>list=getData(sql,id);
   return list.get(0);

    }
    private List<TypeOfBook> getData(String sql, String...selectionArgs){
        List<TypeOfBook>list =new ArrayList<>();
        Cursor cursor=db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            TypeOfBook TypeOfBook =new TypeOfBook();
            TypeOfBook.maLoai=Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai")));
            TypeOfBook.tenLoai=cursor.getString(cursor.getColumnIndex("tenLoai"));
            TypeOfBook.nCC = cursor.getString(cursor.getColumnIndex("nCC"));
            list.add(TypeOfBook);
        }
        return list;

    }


}
