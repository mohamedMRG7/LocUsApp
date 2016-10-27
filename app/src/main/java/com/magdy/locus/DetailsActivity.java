package com.magdy.locus;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {
    private ArrayAdapter<String> roomsAdapter ;
    private String[] dots ;
    private TextView dotLayout ;
    private  int [] pics;
    private ViewPager viewPager ;
    private CustomSwipeAdapter viewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ListView listView = (ListView)findViewById(R.id.rooms_list);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        pics = new int[]{
                R.drawable.ic_room_black_24dp,
                R.drawable.ic_room_black_24dp,
                R.drawable.ic_room_black_24dp,
                R.drawable.ic_room_black_24dp
        };
        viewPagerAdapter = new CustomSwipeAdapter(this,pics);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewListener);
        dotLayout = (TextView) findViewById(R.id.layout_dots);
        dots = new String[pics.length];
        updateButtonDots(0);


       final String[] rooms = {
                "room 1",
                "room 2",
                "room 3",

        };
        roomsAdapter = new ArrayAdapter<String>(
                this,
                R.layout.room_item,
                R.id.rt,
               rooms);


        listView.setAdapter(roomsAdapter);
        int totalHeight = 0;
        for (int i = 0; i < roomsAdapter.getCount(); i++) {
            View listItem = roomsAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (roomsAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = roomsAdapter.getItem(position);
                Toast.makeText(getBaseContext(),s,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), TableActivity.class).putExtra("room", s);
                startActivity(intent);
            }
        });


    }
    private void updateButtonDots (int pos)
    {


        dotLayout.setText("");

        int colorActive = Color.parseColor("#01579B") ;
        //int colorInActive = Color.parseColor("#81D4FA") ;

        for (int i = 0 ; i <  dots.length;i++)
        {

            //dots[i].setText("•");
            //•••••
            if(i != pos )
            { dots[i]="○"; }//˳
            else { dots[i]="●";}
           // dots[i].setTextSize(35);
            dotLayout.setText(dotLayout.getText()+dots[i]);
            dotLayout.setTextColor(colorActive);
            dotLayout.setTextSize(15);


        }
    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            updateButtonDots(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    } ;



}
