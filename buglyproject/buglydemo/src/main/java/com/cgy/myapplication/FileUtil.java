package com.cgy.myapplication;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by RB-cgy on 2016/7/26.
 */
public class FileUtil {
    public static void CopyAssertApkToFile(Context context, String filename,
                                           String des) {
        try {

            File file = new File(Environment.getExternalStorageDirectory()
                    .toString() + "/cgytest" + File.separator + des);
            if (file.exists()) {
                return;
            }
            System.out.println("root:" + file.getAbsolutePath());
            InputStream inputStream = context.getAssets().open(filename);

            if (!new File(file.getParent()).exists()) {
                new File(file.getParent()).mkdirs();
                file.createNewFile();
                System.out.println("file parent dont exist:");
            }
            if (!file.exists()) {
                System.out.println("create again?");
                file.createNewFile();
            }
            System.out.println("exists?:" + file.exists());
            FileOutputStream fileOutputStream = new FileOutputStream(file.getPath());
            byte buffer[] = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, len);
            }
            fileOutputStream.close();
            System.out.println("copy ok!"  );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
