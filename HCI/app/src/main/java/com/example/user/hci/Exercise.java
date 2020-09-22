package com.example.user.hci;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Exercise extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String[] Places = {"Burpees","Jumping Jacks","High Knees","Squats","Lunges","Push-Ups","Plank","Crunches","Pull-ups","Dips"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView listView = (ListView) findViewById(R.id.listview);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Exercise.this,android.R.layout.simple_list_item_1,Places);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    startActivity(new Intent(Exercise.this,ex1.class));
                }
                if(position == 1){
                    startActivity(new Intent(Exercise.this,ex2.class));
                }
                if(position == 2){
                    startActivity(new Intent(Exercise.this,ex3.class));
                }
                if(position == 3){
                    startActivity(new Intent(Exercise.this,ex4.class));
                }
                if(position == 4){
                    startActivity(new Intent(Exercise.this,ex5.class));
                }
                if(position == 5){
                    startActivity(new Intent(Exercise.this,ex6.class));
                }
                if(position == 6){
                    startActivity(new Intent(Exercise.this,ex7.class));
                }
                if(position == 7){
                    startActivity(new Intent(Exercise.this,ex8.class));
                }
                if(position == 8){
                    startActivity(new Intent(Exercise.this,ex9.class));
                }
                if(position == 9){
                    startActivity(new Intent(Exercise.this,ex10.class));
                }
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Few exercises are provided here.", Snackbar.LENGTH_LONG)
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent=new Intent(getApplicationContext(),Log_in.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.exercise, menu);
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
            startActivity(new Intent(Exercise.this,Log_in.class));
            // Handle the camera action
        } else if (id == R.id.plan) {
            startActivity(new Intent(Exercise.this,Addplan.class));
        } else if (id == R.id.reminder) {
            startActivity(new Intent(Exercise.this,Reminder.class));
        } else if (id == R.id.progess) {
            startActivity(new Intent(Exercise.this,Progress.class));
        } else if (id == R.id.stat) {
            startActivity(new Intent(Exercise.this,Stats.class));
        } else if (id == R.id.exercise) {
            startActivity(new Intent(Exercise.this,Exercise.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
