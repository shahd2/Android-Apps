package com.example.user.hci;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Log_in extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView textView;
    TextView goal,b,cal,sub;
    float n;
    private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        textView = (TextView) findViewById(R.id.name);
        //goal = (TextView) findViewById(R.id.goal);
        b = (TextView) findViewById(R.id.bmr);
        cal = (TextView) findViewById(R.id.comment);
        sub = (TextView) findViewById(R.id.snap);

        progressbar = (ProgressBar) findViewById(R.id.progress_view);
        progressbar.getProgressDrawable().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        if(getIntent().getBooleanExtra("Exit",false))
        {
            finish();
        }
        SharedPreferences sharedPreferences1 = getSharedPreferences("pref", MODE_PRIVATE);
        String u = sharedPreferences1.getString("UNAME", "No data found");
        textView.setText("Hello " + u);
        SharedPreferences sharedPreferences2 = getSharedPreferences("Details", MODE_PRIVATE);
        String g = sharedPreferences2.getString("Goal", "none");
        //goal.setText("Your goal is to " + g);
        String bmr = sharedPreferences2.getString("BMR", "none");
        b.setText(bmr+" cal/day");
        SharedPreferences sharedPreferences3 = getSharedPreferences("Cal", MODE_PRIVATE);
        String t = sharedPreferences3.getString("Total", "0");
        float a= (Float.parseFloat(bmr));
        float b= (Float.parseFloat(t));
        if(t.equals("0"))
        {}
        else {
            cal.setVisibility(View.VISIBLE);
            cal.setText((b)+" cal.");
        }
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Log_in.this,Addplan.class));
            }
        });

        progressbar.setMax(((int) a));
        progressbar.setProgress(((int)b));

        if(a>b)
        {
            n=a-b;

            sub.setText((n)+" cal.");
        }
        else if(a<b)
        { n=b-a;

            sub.setText((n)+" cal.");}
        else if(a==b)
        {n=a-b;
        sub.setText((n)+" cal.");
        }

        SharedPreferences sharedPreferences = getSharedPreferences("Progress", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("A", String.valueOf(a));
        editor.putString("B", String.valueOf(b));
        editor.commit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "See all the details on this page.", Snackbar.LENGTH_LONG)
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
        AlertDialog.Builder builder=new AlertDialog.Builder(Log_in.this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log_in.this.finish();


            }
        });
        builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        builder.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.log_in, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            startActivity(new Intent(Log_in.this,Log_in.class));
            // Handle the camera action
        } else if (id == R.id.plan) {
            startActivity(new Intent(Log_in.this,Addplan.class));
        } else if (id == R.id.reminder) {
            startActivity(new Intent(Log_in.this,Reminder.class));
        } else if (id == R.id.progess) {
            startActivity(new Intent(Log_in.this,Progress.class));
        } else if (id == R.id.stat) {
            startActivity(new Intent(Log_in.this,Stats.class));
        } else if (id == R.id.exercise) {
            startActivity(new Intent(Log_in.this,Exercise.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
