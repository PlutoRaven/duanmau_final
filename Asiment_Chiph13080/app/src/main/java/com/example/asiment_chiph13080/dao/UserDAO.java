package com.example.asiment_chiph13080.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.asiment_chiph13080.database.Sqldatabase;
import com.example.asiment_chiph13080.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    SQLiteDatabase db;
    public UserDAO(Context context){
        Sqldatabase sqldatabase=new Sqldatabase(context);
        db=sqldatabase.getWritableDatabase();
    }
    public long insert(User user){
        ContentValues contentValues=new ContentValues();
        contentValues.put("maTT",user.maTT);
        contentValues.put("hoTen",user.hoTen);
        contentValues.put("matKhau",user.matKhau);
        return db.insert("User",null,contentValues);
    }
    public int update(User user){
        ContentValues contentValues=new ContentValues();
        contentValues.put("maTT",user.maTT);
        contentValues.put("hoTen",user.hoTen);
        contentValues.put("matKhau",user.matKhau);
        return db.update("User",contentValues,"maTT=?",new String[]{String.valueOf(user.maTT)});
    }
    public  int delete(String id){
        return
                db.delete("User","maTT=?",new String[]{id});
    }
    public List<User>getAll(){
        String sql="Select *from User";
        return getData(sql);
    }
    public  User getID (String id){
        String sql="select * from User where maTT=?";
        List<User> list=getData(sql,id);
        return list.get(0);
    }
    private List<User>getData(String sql,String ...selectionArgs){
        List<User>list=new ArrayList<>();
        Cursor cursor=db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            User user=new User();
            user.maTT=cursor.getString(cursor.getColumnIndex("maTT"));
            user.hoTen=cursor.getString(cursor.getColumnIndex("hoTen"));
            user.matKhau=cursor.getString(cursor.getColumnIndex("matKhau"));
            list.add(user);
        }
        return list;
    }
    public  int checkLogin(String id,String password){
        String sql="select * from User where maTT=? and matKhau=?";
        List<User>list=getData(sql,id,password);
        if (list.size()==0){
            return -1;
        }
        return 1;
    }
}
