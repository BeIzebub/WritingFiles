package com.kg.files;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },
                    1000);
        } else {
            // Permission has already been granted
        }


        File file = new File(this.getFilesDir(), "test.txt ");
        if(file.exists()){
            file.delete();
        }

        if(isExternalStorageWritable()){
            file = new File(
                    Environment.getExternalStorageDirectory(),
                    "test.txt");
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            String a = "barev";
            outputStream.write(a.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isExternalStorageWritable(){
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }
        return false;
    }
}
