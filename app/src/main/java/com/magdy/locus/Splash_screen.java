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


        final Dpoepnhelper dpoepnhelper=new Dpoepnhelper(this);
        // Log.e("places", dpoepnhelper.getCount()+"");

        final List<Bitmap> bitmapList=new ArrayList<Bitmap>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                new Place_data().getarrays(getApplicationContext());
              /*  for(int i=0;i<dpoepnhelper.getCount();i++)
                {

                    try {
                        bitmapList.add(new Place_data().getimg(dpoepnhelper.getallplaces().get(i).getPLACE_IMG_URL()));


                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }*/
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //img.setImageBitmap(bitmapList.get(1));
                        Intent intent=new Intent(Splash_screen.this,Firebase.class);
                     //   intent.putExtra("list", (Serializable) bitmapList);
                        startActivity(intent);
                    }
                });
            }
        }).start();

    }
}
