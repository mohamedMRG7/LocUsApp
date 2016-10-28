package com.magdy.locus.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.magdy.locus.R;
import com.magdy.locus.interfacs.Onclick;

/**
 * Created by moham on 28/10/2016.
 */

public class ComanyListAdapter extends BaseAdapter {

    Context ctx;
    int roomnumbers;
    String[] spinner_adapter={"1","2","3","4","5","6","7","8","9","10"};
    Onclick onclick;

    public ComanyListAdapter(int roomnumbers, Context ctx,Onclick onclick) {
        this.roomnumbers = roomnumbers;
        this.ctx = ctx;
        this.onclick=onclick;
    }

    @Override
    public int getCount() {
        return roomnumbers;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.inf_listcompanytable,parent,false);
        TextView roomnum=(TextView)v.findViewById(R.id.txt_roomnum);
        Spinner sp_from=(Spinner) v.findViewById(R.id.sp_from);
        Spinner sp_to=(Spinner) v.findViewById(R.id.sp_to);
        Button bt=(Button)v.findViewById(R.id.btn_subbmit);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.onclickelis(position);
            }
        });

        ArrayAdapter adapter=new ArrayAdapter(ctx,R.layout.support_simple_spinner_dropdown_item,spinner_adapter);
        sp_from.setAdapter(adapter);
        sp_to.setAdapter(adapter);
        int newpos=position+1;
        roomnum.setText("Room "+ newpos);

        return v;
    }
}
