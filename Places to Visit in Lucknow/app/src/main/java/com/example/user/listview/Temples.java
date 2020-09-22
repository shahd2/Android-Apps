package com.example.user.listview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Temples extends AppCompatActivity {
    String[] NAME = {"Balaji Temple","Chandrika Devi Temple","Aliganj Hanuman Temple","Sankat Mochan Hanuman Temple","Sheetala Devi Temple","Hanuman Setu Temple","Surya Temple","Nageshwar Shiva Temple","Mankameshwar Temple","Bhootnath Temple"};
    int[] Images = {R.drawable.balajitemple,R.drawable.chandrika,R.drawable.aliganj,R.drawable.sankat,R.drawable.sheetladevi,R.drawable.setu,R.drawable.surya,R.drawable.shiva,R.drawable.mankameshwar,R.drawable.bhootnath};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temples);
        ListView listView = (ListView) findViewById(R.id.listview);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                String places = (String) listView.getAdapter().getItem(position);
                if(position==0) {
                    listView.getContext().startActivity(new Intent(Temples.this, BalajiTemple.class));
                }
                else if(position==1) {
                    listView.getContext().startActivity(new Intent(Temples.this,Chndrika.class));
                }
                else if(position==2) {
                    listView.getContext().startActivity(new Intent(Temples.this, AliganjHanuman.class));
                }
                else if(position==3) {
                    listView.getContext().startActivity(new Intent(Temples.this, SankatMochan.class));
                }
                else if(position==4) {
                    listView.getContext().startActivity(new Intent(Temples.this, SheetalaDevi.class));
                }
                else if(position==5) {
                    listView.getContext().startActivity(new Intent(Temples.this, Setu.class));
                }
                else if(position==6) {
                    listView.getContext().startActivity(new Intent(Temples.this, Surya.class));
                }
                else if(position==7) {
                    listView.getContext().startActivity(new Intent(Temples.this, Shiva.class));
                }
                else if(position==8) {
                    listView.getContext().startActivity(new Intent(Temples.this, Mann.class));
                }
                else if(position==9) {
                    listView.getContext().startActivity(new Intent(Temples.this, Bhoot.class));
                }
            }
        });
    }
    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return NAME.length;
        }

        @Override
        public Object getItem(int position) {
        return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = getLayoutInflater();
            convertView = layoutInflater.inflate(R.layout.custom,null);
            TextView Name = (TextView) convertView.findViewById(R.id.textViewName);
            ImageView IMG = (ImageView) convertView.findViewById(R.id.imageView);
            Name.setText(NAME[position]);
            IMG.setImageResource(Images[position]);
            return convertView;
        }
    }
}