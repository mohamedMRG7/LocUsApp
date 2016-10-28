package com.magdy.locus;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.magdy.locus.mysql.database.Place_data;
import com.magdy.locus.sqlite.databas.Dpoepnhelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        // Log.e("places", dpoepnhelper.getCount()+"");

        final List<String> bitmapList=new ArrayList<String>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                new Place_data().getarrays(getApplicationContext());
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //img.setImageBitmap(bitmapList.get(1));
                        Intent intent=new Intent(Splash_screen.this,MainActivity.class);
                       // intent.putExtra("list", (Serializable) bitmapList);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }).start();

    }

}
