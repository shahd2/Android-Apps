package com.example.user.registration;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Calendar;



public class Second extends AppCompatActivity {
    TextView name,num,date,gend,course,fee,time;
    Calendar calendar = Calendar.getInstance();
    int uDate = calendar.get(Calendar.DAY_OF_MONTH);
    int uMonth = calendar.get(Calendar.MONTH);
    int uYear = calendar.get(Calendar.YEAR);
    int uhour=calendar.get(Calendar.HOUR_OF_DAY);
    int umin=calendar.get(Calendar.MINUTE);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        name = (TextView) findViewById(R.id.name);
        num= (TextView) findViewById(R.id.num);
        date= (TextView) findViewById(R.id.date);
        gend= (TextView) findViewById(R.id.gender);
        course= (TextView) findViewById(R.id.course);
        fee= (TextView) findViewById(R.id.fee);
        time= (TextView) findViewById(R.id.time);
        time.setText(uDate+"/"+(uMonth+1)+"/"+uYear+"  "+uhour+":"+umin);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String f=bundle.getString("FNAME");
        String l=bundle.getString("LNAME");
        String n=bundle.getString("NUM");
        String d=bundle.getString("DATE");
        String g=bundle.getString("GEND");
        String c=bundle.getString("COUR");
        String fe=bundle.getString("FEE");
        Toast.makeText(this, fe, Toast.LENGTH_SHORT).show();
        name.setText(f+" "+l);
        num.setText(n);
        date.setText(d);
        gend.setText(g);
        course.setText(c);
        fee.setText(fe);
}

}