package com.example.user.multipurpose;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Recorder extends AppCompatActivity implements View.OnClickListener {

    Button b1, b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder);
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);

        /*
        b1.setOnClickListener((View.OnClickListener) this);
        b2.setOnClickListener((View.OnClickListener) this);
        b3.setOnClickListener((View.OnClickListener) this);
        b4.setOnClickListener((View.OnClickListener) this);
        */
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);


    }


    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            Intent intent = new Intent(Recorder.this, AudioRecoder.class);
            startActivity(intent);

        } else if (v.getId() == R.id.button2) {
            Intent intent = new Intent(Recorder.this, VideoCaptureActivity.class);
            startActivity(intent);


        }
    }
}
