package com.cgy.myapplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    Bitmap drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawable = BitmapFactory.decodeResource(getResources(), R.drawable.see);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(new MAdpater());
    }

    public void onClick(View v) {
        Log.i("a", "bug begin");
        Toast.makeText(MainActivity.this, "向上滑动产生bug", Toast.LENGTH_SHORT).show();
        drawable.recycle();
//        drawable.setPremultiplied(false);
    }

    class MAdpater extends BaseAdapter {

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = View.inflate(MainActivity.this, R.layout.item, null);
            ImageView img = (ImageView) v.findViewById(R.id.img);
            TextView textView = (TextView) v.findViewById(R.id.text);
            textView.setText("position:" + position);
            img.setDrawingCacheEnabled(false);
                img.setImageBitmap(drawable);

            return v;
        }
    }
}
