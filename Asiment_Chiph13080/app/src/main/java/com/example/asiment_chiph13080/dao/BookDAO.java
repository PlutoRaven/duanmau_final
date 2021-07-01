package com.example.asiment_chiph13080.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asiment_chiph13080.database.Sqldatabase;
import com.example.asiment_chiph13080.model.Book;


import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private SQLiteDatabase db;

    public BookDAO(Context context) {
        Sqldatabase sqldatabase = new Sqldatabase(context);
        db = sqldatabase.getWritableDatabase();
    }
    public long insert(Book book) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenSach", book.tenSach);
        contentValues.put("giaThue", book.giaThue);
        contentValues.put("maLoai", book.maLoai);
        contentValues.put("giamGia", book.giamGia);
        contentValues.put("tacGia", book.tacGia);
        return db.insert("Book", null, contentValues);
    }
    public int update(Book book) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenSach", book.tenSach);
        contentValues.put("giaThue", book.giaThue);
        contentValues.put("maLoai", book.maLoai);
        contentValues.put("giamGia", book.giamGia);
        contentValues.put("tacGia", book.tacGia);
        return db.update("Book", contentValues, "maSach=?",new String[]{String.valueOf(book.maSach)});
    }
    public int delete(String id) {
        return
                db.delete("Book", "maSach=?", new String[]{id});
    }
    public List<Book> getAll() {
        String sql = "select * from Book";
        return getData(sql);
    }
    public Book getID(String id) {
        String sql = "select * from Book where maSach=?";
        List<Book> list2 = new ArrayList<>();
        list2 = getData(sql, id);
        return list2.get(0);

    }
    private List<Book> getData(String sql, String... selectionArgs) {
        List<Book> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            Book book = new Book();
            book.maSach = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSach")));
            book.maLoai = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai")));
            book.tenSach = cursor.getString(cursor.getColumnIndex("tenSach"));
            book.giaThue = Integer.parseInt(cursor.getString(cursor.getColumnIndex("giaThue")));
            book.giamGia = Integer.parseInt(cursor.getString(cursor.getColumnIndex("giamGia")));
            book.tacGia = cursor.getString(cursor.getColumnIndex("tacGia"));
            list.add(book);
        }
        return list;
    }
}
