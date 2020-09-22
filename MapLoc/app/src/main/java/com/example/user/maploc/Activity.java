package com.example.user.maploc;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_);
        int secondsDelayed = 1;

        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(Activity.this, MapsActivity.class));
                finish();
            }
        }, secondsDelayed * 1000);
    }
}
