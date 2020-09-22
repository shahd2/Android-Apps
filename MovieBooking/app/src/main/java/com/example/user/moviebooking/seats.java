package com.example.user.moviebooking;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class seats extends AppCompatActivity {

    String seats="";
    int c=0,chair;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seats);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        final String f=bundle.getString("CITY");
        final String l=bundle.getString("THEATER");
        final String mo=bundle.getString("MOVIE");
        final String s=bundle.getString("TIME");
        final Dialog dialog = new Dialog(seats.this);
                dialog.setTitle("Enter no. of seats to book");
                dialog.setContentView(R.layout.customdialog);
                final EditText seat = (EditText) dialog.findViewById(R.id.seats);
                Button buttonSubmit = (Button) dialog.findViewById(R.id.submit);
                buttonSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chair= Integer.parseInt(seat.getText().toString());
                        dialog.dismiss();
                    }
                });
                dialog.show();

        ImageView a1,a2,a3,a4,a5,a6,a7;
        a1= (ImageView) findViewById(R.id.a1);
        a2= (ImageView) findViewById(R.id.a2);
        a3= (ImageView) findViewById(R.id.a3);
        a4= (ImageView) findViewById(R.id.a4);
        a5= (ImageView) findViewById(R.id.a5);
        a6= (ImageView) findViewById(R.id.a6);
        a7= (ImageView) findViewById(R.id.a7);
        final int flag[] = {0,0,0,0,0,0,0,0};
        Button button= (Button) findViewById(R.id.book);

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag[1]==0)
                {Toast.makeText(seats.this, "A1", Toast.LENGTH_SHORT).show();
                seats+="A1 ";
                    flag[1]=1;
                c++;}
                else
                    Toast.makeText(seats.this, "Already Selected", Toast.LENGTH_SHORT).show();
            }
        });
        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag[2]==0) {
                    Toast.makeText(seats.this, "A2", Toast.LENGTH_SHORT).show();
                    seats += "A2 ";
                    flag[2]=1;
                    c++;
                }
                else
                    Toast.makeText(seats.this, "Already Selected", Toast.LENGTH_SHORT).show();
            }
        });
        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag[3]==0) {
                    Toast.makeText(seats.this, "A3", Toast.LENGTH_SHORT).show();
                    seats += "A3 ";
                    flag[3]=1;
                    c++;
                }
                else
                    Toast.makeText(seats.this, "Already Selected", Toast.LENGTH_SHORT).show();
            }
        });
        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag[4] == 0)
                {    Toast.makeText(seats.this, "A4", Toast.LENGTH_SHORT).show();
                seats += "A4 ";
                    flag[4]=1;
                    c++;
                }
                else
                    Toast.makeText(seats.this, "Already Selected", Toast.LENGTH_SHORT).show();
            }

        });
        a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag[5]==0)
                {Toast.makeText(seats.this, "A5", Toast.LENGTH_SHORT).show();
                    seats+="A5 ";
                    flag[5]=1;
                    c++;}
                else
                    Toast.makeText(seats.this, "Already Selected", Toast.LENGTH_SHORT).show();
            }
        });
        a6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag[6]==0)
                {Toast.makeText(seats.this, "A6", Toast.LENGTH_SHORT).show();
                    seats+="A6 ";
                    flag[6]=1;
                    c++;}
                else
                    Toast.makeText(seats.this, "Already Selected", Toast.LENGTH_SHORT).show();
            }
        });
        a7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag[7]==0)
                {Toast.makeText(seats.this, "A7", Toast.LENGTH_SHORT).show();
                    seats+="A7 ";
                    flag[7]=1;
                    c++;}
                else
                    Toast.makeText(seats.this, "Already Selected", Toast.LENGTH_SHORT).show();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c==chair)
                {
                Intent intent1 = new Intent(seats.this,Details.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("CITY",f);
                bundle1.putString("THEATER",l);
                bundle1.putString("MOVIE",mo);
                bundle1.putString("TIME",s);
                bundle1.putString("SEAT",seats);
                intent1.putExtras(bundle1);
                startActivity(intent1);
            }
                else if(c>chair)
                    Toast.makeText(seats.this, "You have selected many seats.", Toast.LENGTH_SHORT).show();
                else if(c<chair)
                    Toast.makeText(seats.this, "You have not selected the chairs.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    }

