package com.example.user.hci;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class Reminder extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
     Switch sw1,sw2,sw3,sw4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        sw1=(Switch) findViewById(R.id.s1);
        sw2=(Switch) findViewById(R.id.s2);
        sw3=(Switch) findViewById(R.id.s3);
        sw4=(Switch) findViewById(R.id.s4);
        sw1.setChecked(false);
        sw2.setChecked(false);
        sw3.setChecked(false);
        sw4.setChecked(false);
        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(Reminder.this);
                    builder.setSmallIcon(R.drawable.bf);
                    builder.setContentTitle("Breakfast Time");
                    builder.setContentText("It's time for breakfast.");
                    Intent intent = new Intent(Reminder.this, Log_in.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(Reminder.this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(pendingIntent);
                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.notify(1, builder.build());

                }
                else {
                        }
            }});

        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(Reminder.this);
                    builder.setSmallIcon(R.drawable.ld);
                    builder.setContentTitle("Lunch Time");
                    builder.setContentText("It's time for lunch.");
                    Intent intent = new Intent(Reminder.this, Log_in.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(Reminder.this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(pendingIntent);
                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.notify(2, builder.build());

                } else {

                }
            }});
        sw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(Reminder.this);
                    builder.setSmallIcon(R.drawable.ld);
                    builder.setContentTitle("Dinner Time");
                    builder.setContentText("It's time for dinner.");
                    Intent intent = new Intent(Reminder.this, Log_in.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(Reminder.this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(pendingIntent);
                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.notify(3, builder.build());

                } else {

                }
            }});
        sw4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(Reminder.this);
                    builder.setSmallIcon(R.drawable.bf);
                    builder.setContentTitle("Snacks Time");
                    builder.setContentText("It's time for snacks.");
                    Intent intent = new Intent(Reminder.this, Log_in.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(Reminder.this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(pendingIntent);
                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.notify(4, builder.build());

                } else {

                }
            }});
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Set Reminder to never miss a meal.", Snackbar.LENGTH_LONG)
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
        //getMenuInflater().inflate(R.menu.reminder, menu);
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
            startActivity(new Intent(Reminder.this,Log_in.class));
        } else if (id == R.id.plan) {
            startActivity(new Intent(Reminder.this,Addplan.class));
        } else if (id == R.id.reminder) {
            startActivity(new Intent(Reminder.this,Reminder.class));
        } else if (id == R.id.progess) {
            startActivity(new Intent(Reminder.this,Progress.class));
        } else if (id == R.id.stat) {
            startActivity(new Intent(Reminder.this,Stats.class));
        } else if (id == R.id.exercise) {
            startActivity(new Intent(Reminder.this,Exercise.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
