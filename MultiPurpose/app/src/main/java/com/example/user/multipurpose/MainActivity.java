package com.example.user.multipurpose;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b1,b2,b3,b4,b5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
        b5=(Button)findViewById(R.id.button5);
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
        b5.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button){
            Intent intent = new Intent(MainActivity.this,BmiCalculator.class);
            startActivity(intent);

        }else if(v.getId() == R.id.button2){
            Intent intent = new Intent(MainActivity.this,Narrator.class);
            startActivity(intent);


        }
        else if(v.getId() == R.id.button3)
        {
            Intent intent=new Intent(MainActivity.this,FlashLight.class);
            startActivity(intent);

        }
        else if(v.getId() == R.id.button4) {
            Intent intent=new Intent(MainActivity.this,MediaPlayer.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.button5) {
            Intent intent=new Intent(MainActivity.this,Recorder.class);
            startActivity(intent);


        }
    }
}
