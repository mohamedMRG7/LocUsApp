package com.magdy.locus;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TableActivity extends AppCompatActivity {

    private CustomAdapter timeAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        Intent intent = this.getIntent();
        setTitle(intent.getStringExtra("room").toString());

        ListView listView = (ListView)findViewById(R.id.time_list);
        List <String> hours = new ArrayList<String>();

                hours.add("11");
                hours.add("12");
                hours.add("1");
                hours.add("2");
                hours.add("3");
                hours.add("4");
                hours.add("5");
                hours.add("6");
        List<Boolean> available = new ArrayList<Boolean>();

        available.add(true);
        available.add(false);
        available.add(false);
        available.add(false);
        available.add(false);
        available.add(true);
        available.add(true);
        available.add(true);


        timeAdapter= new CustomAdapter(this,hours,available);


        listView.setAdapter(timeAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = timeAdapter.getItem(position).toString();
                Toast.makeText(getBaseContext(),s,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(),SigninActivity.class);
                startActivity(intent);

            }
        });
    }
}
 class  CustomAdapter extends BaseAdapter {


     private Context mContext;
     List<String> hour;
     List<Boolean> available ;

 public CustomAdapter(Context c , List<String> hour, List<Boolean> avail)
 {
     mContext = c ;
     this.hour = hour ;
     available = avail ;

 }
    @Override
    public int getCount() {
        return hour.size();
    }

    @Override
    public Object getItem(int position) {
        return hour.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View  convertView1 = inflater.inflate(R.layout.time_item,parent,false) ;

        TextView text1 = (TextView)convertView1.findViewById(R.id.time_hour);
        TextView text2 = (TextView)convertView1.findViewById(R.id.time_state);
        text1.setText(hour.get(position));
        ImageView imageView =(ImageView) convertView1.findViewById(R.id.time_state_icon) ;
        if(available.get(position))
        {
            text2.setText("متاح ");
            text2.setTextColor(Color.parseColor("#FF00B12E") );
            imageView.setImageResource(R.drawable.ic_check_circle_green_24dp);



        }
        else
        {
            text2.setText("غير متاح ");
            text2.setTextColor(Color.parseColor("#ed0000") );
            imageView.setImageResource(R.drawable.ic_cancel_red_24dp);
        }

        return convertView1;
    }
}
