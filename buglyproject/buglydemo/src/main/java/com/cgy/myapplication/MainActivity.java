package com.cgy.myapplication;

import android.app.Activity;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
                dbHelper.getListError();
                System.out.println("Thread 1 close");

            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 2 start");
                dbHelper.getWritableDatabase();
            }
        }.start();

    }

}
