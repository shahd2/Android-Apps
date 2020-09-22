package com.example.user.frame2acivity;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements FragColor.OnClickColorChangedListener {
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative);
        FragColor fragColor = new FragColor();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frag,fragColor);
        fragmentTransaction.commit();
    }

    @Override
    public void onColorChange(String color) {
        if(color.equals("RED")){
            relativeLayout.setBackgroundColor(Color.RED);
        }else if(color.equals("CYAN")){
            relativeLayout.setBackgroundColor(Color.CYAN);
        }else if(color.equals("MAGENTA")){
            relativeLayout.setBackgroundColor(Color.MAGENTA);
        }else if(color.equals("BLUE")){
            relativeLayout.setBackgroundColor(Color.BLUE);
        }
    }
}
