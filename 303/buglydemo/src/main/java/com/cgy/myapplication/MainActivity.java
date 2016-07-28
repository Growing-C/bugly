package com.cgy.myapplication;

import android.app.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

public class MainActivity extends Activity {
    MBroad mBroad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBroad = new MBroad();
    }

    public void onClick(View v) {
        System.out.println("bug start");
        sendBroadcast(new Intent("action"));

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mBroad, new IntentFilter("action"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mBroad);
    }

    class MBroad extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("action:" + intent.getAction());
            Message msg = new Message();
            msg.what = 1;
//            msg.recycle();
            Handler handler = new Handler();
            handler.sendMessage(msg);
            handler.sendMessage(msg);
        }
    }
}
