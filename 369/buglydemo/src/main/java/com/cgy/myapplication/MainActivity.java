package com.cgy.myapplication;

import android.app.Activity;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DbHelper(this);

        dbHelper.insertData("aaa", "111");
        dbHelper.insertData("bbb", "222");
        dbHelper.insertData("ccc", "333");
        dbHelper.getList();
    }

    public void onClick(View v) {
        System.out.println("bug start");

        new Thread() {
            @Override
            public void run() {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.beginTransaction();
                Cursor c = db.rawQuery("select * from table1", null);
                if (c != null && c.getCount() != 0)
                    while (c.moveToNext()) {
                        String name = c.getString(0);
                        String pwd = c.getString(1);
                        System.out.println("name:" + name + "  pwd:" + pwd);
                    }


                db.close();
                System.out.println("db close");
                dbHelper.getWritableDatabase();

            }
        }.start();

    }

}
