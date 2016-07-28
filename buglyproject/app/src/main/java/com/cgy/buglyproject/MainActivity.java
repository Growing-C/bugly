package com.cgy.buglyproject;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import dalvik.system.DexClassLoader;

public class MainActivity extends Activity {
    MBroadCastReceiver receiver;
    DexClassLoader classLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        FileUtil.CopyAssertApkToFile(MainActivity.this, "app-release.apk", "app-release.apk");

    }

    public void onClick(View v) {
        System.out.println("bug start");

        ArrayList list = new ArrayList();
        list.add(getApkObject());

        Intent it = new Intent();
        it.setAction("com.cgy.action_aa");
        it.putExtra("se", list);
        sendBroadcast(it);
        System.out.println("sendBroadcast");
    }

    private Object getApkObject() {
        File file = new File(Environment.getExternalStorageDirectory()
                .toString() + "/cgytest" + File.separator + "app-release.apk");
        final File optimizedDexOutputPath = getDir("temp", Context.MODE_PRIVATE);
          classLoader = new DexClassLoader(file.getAbsolutePath(),
                optimizedDexOutputPath.getAbsolutePath(), null,
                getClassLoader());
        Object obj = null;
        try {
            Class iclass = classLoader.loadClass("com.cgy.buglyproject.OtherSer");
            obj = iclass.newInstance();

            Field[] fields = iclass.getDeclaredFields();

            for (Field field : fields) {
                // 对于每个属性，获取属性名
                String varName = field.getName();
                boolean access = field.isAccessible();
                if (!access) field.setAccessible(true);

                //从obj中获取field变量
                Object o = field.get(obj);
                System.out.println("变量： " + varName + " = " + o);

                if (!access) field.setAccessible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    protected void onResume() {
        super.onResume();
        receiver = new MBroadCastReceiver();
        registerReceiver(receiver, new IntentFilter("com.cgy.action_aa"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }


    class MBroadCastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println( "action:" + intent.getAction());

            List a= (List) intent.getSerializableExtra("se");
            System.out.println(a.size());
//            intent.getIntExtra("se",3);
        }
    }

}
