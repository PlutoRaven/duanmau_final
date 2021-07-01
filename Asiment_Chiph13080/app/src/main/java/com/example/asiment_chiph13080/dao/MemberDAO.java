package com.example.asiment_chiph13080.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asiment_chiph13080.database.Sqldatabase;
import com.example.asiment_chiph13080.model.Member;


import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    private SQLiteDatabase db;
    public MemberDAO(Context context){
        Sqldatabase sqldatabase=new Sqldatabase(context);
        db=sqldatabase.getWritableDatabase();

    }
    public long insert (Member member){
        ContentValues values=new ContentValues();
        values.put("hoTen", member.hoTen);
        values.put("namSinh", member.namSinh);
        return db.insert("Member",null,values);

    }
    public int update(Member member){
        ContentValues contentValues=new ContentValues();
        contentValues.put("hoTen", member.hoTen);
        contentValues.put("namSinh", member.namSinh);
        return  db.update("Member",contentValues,"maTV=?",new String[]{String.valueOf(member.maTV)});

    }
    public int delete(String id){
        return db.delete("Member","maTV=?",new String[]{id});

    }
    public List<Member>getAll(){
        String sql="Select * from Member";
        return getData(sql);
    }
    public Member getID(String id){
        String sql="Select * from Member where maTV=?";
        List<Member> list=getData(sql,id);
        return list.get(0);
    }
    private List<Member>getData(String sql, String...selectionArgs){
        List<Member>list =new ArrayList<>();
        Cursor cursor=db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            Member member =new Member();
            member.maTV=Integer.parseInt(cursor.getString(cursor.getColumnIndex("maTV")));
            member.hoTen=cursor.getString(cursor.getColumnIndex("hoTen"));
            member.namSinh=cursor.getString(cursor.getColumnIndex("namSinh"));
            list.add(member);
        }
        return list;
    }
}
