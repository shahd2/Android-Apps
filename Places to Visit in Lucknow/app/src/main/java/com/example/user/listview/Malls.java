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

public class Malls extends AppCompatActivity {
    String[] NAME = {"City Mall","Fun Republic Mall","Sahara Ganj Mall","Wave Mall","Riverside Mall"};
    int[] Images = {R.drawable.citymall,R.drawable.funrepublicmall,R.drawable.saharaganjmall,R.drawable.wavemall,R.drawable.riversidemall};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_malls);
        ListView listView = (ListView) findViewById(R.id.listview);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                String places = (String) listView.getAdapter().getItem(position);
                if(position==0) {
                    listView.getContext().startActivity(new Intent(Malls.this, BaraImambara.class));
                }
                else if(position==1) {
                    listView.getContext().startActivity(new Intent(Malls.this,BritishResidency.class));
                }
                else if(position==2) {
                    listView.getContext().startActivity(new Intent(Malls.this, ChattarManzil.class));
                }
                else if(position==3) {
                    listView.getContext().startActivity(new Intent(Malls.this, ChotoImambara.class));
                }
                else if(position==4) {
                    listView.getContext().startActivity(new Intent(Malls.this, Constatine.class));
                }
                else if(position==5) {
                    listView.getContext().startActivity(new Intent(Malls.this, RumiDarwaza.class));
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
            if(position == 0){
                startActivity(new Intent(Malls.this,Description1.class));
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
