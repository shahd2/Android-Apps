package com.example.user.listview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Markets extends AppCompatActivity {
    String[] NAME = {"Bara Imambara","British Residency","Chattar Manzil","Chota Imambara","Constantia House","Rumi Darwaza"};
    int[] Images = {R.drawable.baraimambara,R.drawable.britishresidency,R.drawable.chattarmanzil,R.drawable.chotaimambara,R.drawable.constantiahouse,R.drawable.rumidarwaza};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markets);
        ListView listView = (ListView) findViewById(R.id.listview);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

    }
    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return NAME.length;
        }

        @Override
        public Object getItem(int position) {
            if(position == 0){
                startActivity(new Intent(Markets.this,Description1.class));
            }
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