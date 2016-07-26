package com.cgy.myapplication;

import android.content.Context;
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

    public void getList() {


    }

}
