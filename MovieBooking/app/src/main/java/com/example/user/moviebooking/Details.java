package com.example.user.moviebooking;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    TextView ci,th,mv,ti,se;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        ci= (TextView) findViewById(R.id.city);
        th= (TextView) findViewById(R.id.hall);
        mv= (TextView) findViewById(R.id.movie);
        ti= (TextView) findViewById(R.id.tim);
        se= (TextView) findViewById(R.id.seats);
        String f=bundle.getString("CITY");
        String l=bundle.getString("THEATER");
        String m=bundle.getString("MOVIE");
        String s=bundle.getString("TIME");
        String sea=bundle.getString("SEAT");
        ci.setText(f);
        th.setText(l);
        mv.setText(m);
        ti.setText(s);
        se.setText(sea);
    }

}
