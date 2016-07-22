package com.cgy.myapplication;

import android.app.Application;
import android.util.Log;

//import com.tencent.bugly.crashreport.CrashReport;

import com.tencent.bugly.crashreport.CrashReport;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by RB-cgy on 2016/4/27.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "900027679", false);

    }


}
