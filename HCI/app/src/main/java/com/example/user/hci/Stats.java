package com.example.user.hci;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Stats extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Button button;
    EditText wt, ht;
    TextView bmi, result,gen;
    String uni;
    float bm;
    RadioGroup radioGroup1,radioGroup2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        wt = (EditText) findViewById(R.id.wt);
        ht = (EditText) findViewById(R.id.ht);
        bmi = (TextView) findViewById(R.id.bmi);
        gen= (TextView) findViewById(R.id.gen);
        result = (TextView) findViewById(R.id.comment);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                radioGroup1 = (RadioGroup) findViewById(R.id.gender);
                radioGroup2 = (RadioGroup) findViewById(R.id.unit);
                int selectedId1 = radioGroup1.getCheckedRadioButtonId();
                RadioButton radioButton1 = (RadioButton) findViewById(selectedId1);
                String gender=radioButton1.getText().toString();
                gen.setText("Your gender is "+gender);
                int selectedId2 = radioGroup2.getCheckedRadioButtonId();
                RadioButton radioButton2 = (RadioButton) findViewById(selectedId2);
                uni=radioButton2.getText().toString();
                if(TextUtils.isEmpty(wt.getText().toString())||TextUtils.isEmpty(ht.getText().toString())) {
                    Toast.makeText(Stats.this, "Please enter the details", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    gen.setVisibility(View.VISIBLE);
                    bmi.setVisibility(View.VISIBLE);
                    result.setVisibility(View.VISIBLE);
                    float weight = Float.parseFloat(wt.getText().toString());
                    float height = Float.parseFloat(ht.getText().toString());

                    if("Metric Unit(kg,cm)".equals(uni))
                        bm = (float) ((weight * 10000) / (height * height));
                    else
                        bm = (float) ((weight * 703) / (height * height));
                    String mytext = Float.toString(bm);
                    bmi.setText("Your BMI is " + mytext);
                    if (bm < 18.5)
                        result.setText("You are Underweight.Eat Properly.");
                    else if (bm >= 18.5 && bm <= 24.9)
                        result.setText("You are Normal Weight.");
                    else if (bm >= 25 && bm <= 29.9)
                        result.setText("You are Overweight. Some exercise will be helpful");
                    else
                        result.setText("You are Obese. Consult a doctor.");

                }

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Calculate BMI anytime.", Snackbar.LENGTH_LONG)
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
        //getMenuInflater().inflate(R.menu.stats, menu);
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
                    startActivity(new Intent(Stats.this,Log_in.class));
            // Handle the camera action
        } else if (id == R.id.plan) {
            startActivity(new Intent(Stats.this,Addplan.class));
        } else if (id == R.id.reminder) {
            startActivity(new Intent(Stats.this,Reminder.class));
        } else if (id == R.id.progess) {
            startActivity(new Intent(Stats.this,Progress.class));
        } else if (id == R.id.stat) {
            startActivity(new Intent(Stats.this,Stats.class));
        } else if (id == R.id.exercise) {
            startActivity(new Intent(Stats.this,Exercise.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
