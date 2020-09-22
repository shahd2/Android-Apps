package com.example.user.hci;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Addplan extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String[] slots = {"Breakfast","Lunch","Dinner","Snacks"};
  
    TextView t1,t2,t3,t4;
    float cal;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplan);
        t1= (TextView) findViewById(R.id.type1);
        t2= (TextView) findViewById(R.id.type2);
        t3= (TextView) findViewById(R.id.type3);
        t4= (TextView) findViewById(R.id.type4);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView listView = (ListView) findViewById(R.id.listview);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Addplan.this,android.R.layout.simple_list_item_1,slots);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                String places = (String) listView.getAdapter().getItem(position);
                if(position==0) {
                    listView.getContext().startActivity(new Intent(Addplan.this,Breakfast.class));
                }
                else if(position==1) {
                    listView.getContext().startActivity(new Intent(Addplan.this,Lunch.class));

                }
                else if(position==2) {
                    listView.getContext().startActivity(new Intent(Addplan.this,Dinner.class));
                }
                else if(position==3) {
                    listView.getContext().startActivity(new Intent(Addplan.this,Snack.class));
                }
            }});

        SharedPreferences breakfast = getSharedPreferences("M1", MODE_PRIVATE);
        String b = breakfast.getString("breakfast", "0");
        if(b.equals("0"))
        {}
        else {
            t1.setText(b+" cal.");
            t1.setVisibility(View.VISIBLE);
            cal += (Float.parseFloat(b));
        }
        SharedPreferences lunch = getSharedPreferences("M2", MODE_PRIVATE);
        String l = lunch.getString("lunch", "0");
        if(l.equals("0"))
        {}
        else{
        t2.setText(l+" cal.");
        t2.setVisibility(View.VISIBLE);
        cal+=(Float.parseFloat(l));}
        SharedPreferences dinner = getSharedPreferences("M3", MODE_PRIVATE);
        String d = dinner.getString("dinner", "0");
        if(d.equals("0"))
        {}
        else {
            t3.setText(d+" cal.");
            t3.setVisibility(View.VISIBLE);
            cal +=(Float.parseFloat(d));
        }
        SharedPreferences snacks = getSharedPreferences("M4", MODE_PRIVATE);
        String s = snacks.getString("snack", "0");
        if(s.equals("0"))
        {}
        else {
            t4.setText(s+" cal.");
            t4.setVisibility(View.VISIBLE);
            cal += (Float.parseFloat(s));
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "You can add meals here.1 cup =~250 milliliters, 1 tablespoon=14.2 gram", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = getLayoutInflater();
            convertView = layoutInflater.inflate(R.layout.custom,null);
            TextView Name = (TextView) convertView.findViewById(R.id.slot);
            //ImageView IMG = (ImageView) convertView.findViewById(R.id.imageView);
            Name.setText(slots[position]);
            return convertView;
        }

    @Override
    public void onBackPressed() {


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            SharedPreferences sharedPreferences = getSharedPreferences("Cal", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Total", String.valueOf(cal));
            editor.commit();
            Intent intent=new Intent(getApplicationContext(),Log_in.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.addplan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            startActivity(new Intent(Addplan.this,Log_in.class));
            // Handle the camera action
        } else if (id == R.id.plan) {
            startActivity(new Intent(Addplan.this,Addplan.class));
        } else if (id == R.id.reminder) {
            startActivity(new Intent(Addplan.this,Reminder.class));
        } else if (id == R.id.progess) {
            startActivity(new Intent(Addplan.this,Progress.class));
        } else if (id == R.id.stat) {
            startActivity(new Intent(Addplan.this,Stats.class));
        } else if (id == R.id.exercise) {
            startActivity(new Intent(Addplan.this,Exercise.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
