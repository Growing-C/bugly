package com.cgy.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by RB-cgy on 2016/7/22.
 */
public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "db1", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String a = "CREATE TABLE if not exists [table1] ([name] TEXT NOT NULL, [pwd] TEXT);";
        db.execSQL(a);
        Log.i("a", "db created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 插入一条数据
     *
     * @param name
     * @param pwd
     */
    public void insertData(String name, String pwd) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("pwd", pwd);

        SQLiteDatabase db = getWritableDatabase();
        long row = db.insert("table1", null, values);
        db.close();
        System.out.println("insert row:" + row);
    }

    /**
     * 获得表内所有内容
     */
    public void getList() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from table1", null);
        if (c != null && c.getCount() != 0)
            while (c.moveToNext()) {
                String name = c.getString(0);
                String pwd = c.getString(1);
                System.out.println("name:" + name + "  pwd:" + pwd);
            }
        c.close();
        db.close();
    }

    public void getListError() {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        Cursor c = db.rawQuery("select * from table1", null);
        if (c != null && c.getCount() != 0)
            while (c.moveToNext()) {
                String name = c.getString(0);
                String pwd = c.getString(1);
                System.out.println("name:" + name + "  pwd:" + pwd);
            }
        db.close();
    }
}
