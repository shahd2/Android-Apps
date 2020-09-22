package com.example.user.frag2frag;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Frag1.OnTextSendListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Frag1 frag1 = new Frag1();
        Frag2 frag2 = new Frag2();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frag1,frag1);
        fragmentTransaction.add(R.id.frag2,frag2);
        fragmentTransaction.commit();

    }

    @Override
    public void onTextSend(String msg) {
        Frag2 frag2 = (Frag2) getSupportFragmentManager().findFragmentById(R.id.frag2);
        frag2.textUpdate(msg);
    }
}
