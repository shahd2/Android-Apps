package com.example.user.moviebooking;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] city = {"", "Banglore", "Chennai", "Delhi", "Kolkata", "Mumbai", "Pune"};
    String[] banglore = {"", "Pushpanjali Theater", "PVR Phoenix Marketcity", "Gopalan Cinemas", "INOX", "Q Cinemas"};
    String[] chennai = {"", "Escape Cinemas", "Devi Cineplex", "PVR Skywalk", "INOX"};
    String[] delhi = {"", "PVR Naraina", "PVR Pacific Mall", "Regal Cinemas", "Delite Cinemas", "PVR Directors Cut", "WAVE Cinemas", "DT Cinemas"};
    String[] kolkata = {"", "Paradise Cinema", "New Empire Cinema", "Hind INOX", "INOX Quest"};
    String[] mumbai = {"", "Metro INOX Cinema", "Eros Cinema", "Carnival Imax", "Liberty Cinema", "Carnival Cinemas", "Chitra Cinema"};
    String[] pune = {"", "City Pride Mangala Cinema", "Fun Time Multiplex"};

    String[] movies={"","Jolly LLB 2","Hindi Medium","Cars 3","Despicable Me 3"};
    String[]jl2={"","09:00a.m.","12:15p.m.","04:25p.m."};
    String[]hm={"","11:15a.m.","02:10p.m.","05:00p.m.","7:15p.m."};
    String[]c3={"","10:15a.m.","03:10p.m.","06:00p.m."};
    String[]dm={"","01:15a.m.","05:30p.m.","8:10p.m."};
    String c,t,m,s;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button;
        Spinner spinner = (Spinner) findViewById(R.id.cities);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, city);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                c = parent.getItemAtPosition(position).toString();
                Spinner theater = (Spinner) findViewById(R.id.area);
                ArrayAdapter<String> arrayAdapter = null;
                if (position == 1) {
                    arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, banglore);
                } else if (position == 2) {
                    arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, chennai);

                } else if (position == 3) {
                    arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, delhi);
                } else if (position == 4) {
                    arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, kolkata);
                } else if (position == 5) {
                    arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, mumbai);
                } else if (position == 6) {
                    arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, pune);
                }
                theater.setAdapter(arrayAdapter);
                theater.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        t = parent.getItemAtPosition(position).toString();
                    }
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        Spinner spin = (Spinner) findViewById(R.id.movie);
        ArrayAdapter<String> arrayAdapt = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,movies);
        spin.setAdapter(arrayAdapt);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                m = parent.getItemAtPosition(position).toString();
                Spinner t = (Spinner) findViewById(R.id.time);
                ArrayAdapter<String> arrayAdapter = null;
                if (position == 1) {
                    arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,jl2);
                } else if (position == 2) {
                    arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,hm);

                } else if (position == 3) {
                    arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,c3);
                } else if (position == 4) {
                    arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, dm);
                }
                t.setAdapter(arrayAdapter);
                t.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        s = parent.getItemAtPosition(position).toString();
                    }
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,seats.class);
                Bundle bundle = new Bundle();
                bundle.putString("CITY",c);
                bundle.putString("THEATER",t);
                bundle.putString("MOVIE",m);
                bundle.putString("TIME",s);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }
    public void onBackPressed()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.finish();
            }
        });
        builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}