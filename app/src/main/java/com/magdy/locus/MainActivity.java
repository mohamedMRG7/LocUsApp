package com.magdy.locus;

import android.graphics.Bitmap;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.magdy.locus.adapters.GridAdapter;
import com.magdy.locus.sqlite.databas.Dpoepnhelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> noOfPeople = new ArrayList<String>();                                                  // No of People
    List<String> locationsList = new ArrayList<String>();

    List <Bitmap>imgs;
    // Locations

    SpinnerCustomAdapter locationSpinnerAdapter;
    SpinnerCustomAdapter peopleNumberAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themain1);
        // imgs= (List<Bitmap>) getIntent().getExtras().get("list");
        imgs= (List<Bitmap>) getIntent().getExtras().get("list");
        Log.e("size",imgs.size()+"");
        //imgs.add(R.drawable.ic_room_black_24dp);
        setTitle("الرئيسية");
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        DrawerLayout drawerLayout=(DrawerLayout)findViewById(R.id.drawable);
        new NavDrawerFragment().setup(drawerLayout,this,toolbar);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id)

        NavDrawerFragment nav= new NavDrawerFragment();
        nav.setup((DrawerLayout) findViewById(R.id.themain1),this,toolbar);
*/
        GridView view=(GridView)findViewById(R.id.grid1);
        String [] place_name={"zone","a","b","c","D"};
        Dpoepnhelper placedata=new Dpoepnhelper(this);
        int [] place_img ={R.drawable.ic_room_black_24dp,R.drawable.ic_room_black_24dp,R.drawable.ic_room_black_24dp,R.drawable.ic_room_black_24dp};
        GridAdapter adapter=new GridAdapter(placedata.getallplaces(),imgs,this);
        view.setAdapter(adapter);

        Spinner locationSpinner = (Spinner) findViewById(R.id.location_spinner);
        Spinner peopleNumberSpinner = (Spinner) findViewById(R.id.people_number_spinner);

        // Add hint to noOfPeople Spinner
        // noOfPeople.add("Number of People ");

        locationsList.add("القاهرة الجديدة");
        locationsList.add("وسط البلد");
        locationsList.add("شارع شبرا");
        locationsList.add("الدقي");
        locationsList.add("المهندسين");
        locationsList.add("عين شمس");
        // Fill noOfPeople ArrayList
        for(int i = 10; i <= 1000; i*=1){
            noOfPeople.add(Integer.toString(i));
            if(i < 70)
                i += 20;
            else if(i < 100)
                i += 30;
            else if(i < 300)
                i += 50;
            else if(i < 500)
                i += 100;
            else
                i += 250;
        }
        // Create Spinners Adapters
        locationSpinnerAdapter = new SpinnerCustomAdapter(
                this
                , R.layout.spinner_item_locations
                , R.id.spinner_item_locations_textview
                , locationsList );
        peopleNumberAdapter = new SpinnerCustomAdapter(
                this
                , R.layout.spinner_item_number
                , R.id.spinner_item_number_textview
                , noOfPeople );
        locationSpinnerAdapter.add("Location");                                     // Add hint to Location Spinner
        peopleNumberAdapter.add("Number of People ");


        // Set Spinners' Adapters
        // Set Location Adapter
        locationSpinnerAdapter.setDropDownViewResource(R.layout.spinner_item_locations);
        locationSpinner.setAdapter(locationSpinnerAdapter);
        peopleNumberAdapter.setDropDownViewResource(R.layout.spinner_item_number);
        peopleNumberSpinner.setAdapter(peopleNumberAdapter);
        // No of People Spinner Click Listener
        peopleNumberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0)
                    Toast.makeText(getBaseContext(), (String)peopleNumberAdapter.getItem(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
