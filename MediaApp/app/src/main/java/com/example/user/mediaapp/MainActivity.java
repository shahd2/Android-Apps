package com.example.user.mediaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b1,b2,b3,b4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
        /*
        b1.setOnClickListener((View.OnClickListener) this);
        b2.setOnClickListener((View.OnClickListener) this);
        b3.setOnClickListener((View.OnClickListener) this);
        b4.setOnClickListener((View.OnClickListener) this);
        */
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button){
            Intent intent = new Intent(MainActivity.this,PlayListActivity.class);
            startActivity(intent);

        }else if(v.getId() == R.id.button2){
                Intent intent = new Intent(MainActivity.this,VideoStoredInSDCard.class);
                startActivity(intent);


            }
        else if(v.getId() == R.id.button3)
            {
                Intent intent=new Intent(MainActivity.this,AudioRecoder.class);
                startActivity(intent);

        }
        else if(v.getId() == R.id.button4) {
            Intent intent=new Intent(MainActivity.this,VideoCaptureActivity.class);
            startActivity(intent);
            //Intent launchYouTube1 = getPackageManager().getLaunchIntentForPackage("com.google.android.camera");
            //startActivity(launchYouTube1);


        }
    }
}
