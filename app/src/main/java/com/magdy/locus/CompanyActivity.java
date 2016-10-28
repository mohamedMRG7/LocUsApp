package com.magdy.locus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.magdy.locus.adapters.ComanyListAdapter;
import com.magdy.locus.interfacs.Onclick;

public class CompanyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        String placename=Splash_screen.companyUserinfo.getPlacename();

        ListView listView=(ListView)findViewById(R.id.lst_companytable);
        ComanyListAdapter adapter=new ComanyListAdapter(Splash_screen.companyUserinfo.getRoomnum(), this, new Onclick() {
            @Override
            public void onclickelis(int position) {
                //Her put ur code
                Toast.makeText(getApplicationContext(),position+ "",Toast.LENGTH_LONG).show();
            }
        });
        listView.setAdapter(adapter);


    }
}
