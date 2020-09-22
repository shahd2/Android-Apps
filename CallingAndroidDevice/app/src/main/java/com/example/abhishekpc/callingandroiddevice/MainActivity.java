package com.example.abhishekpc.callingandroiddevice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnChrome,btnYoutube,btnWhatsapp,btntemple;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnChrome = (Button) findViewById(R.id.buttonChrome);
        btnWhatsapp = (Button) findViewById(R.id.buttonWhatsapp);
        btntemple = (Button) findViewById(R.id.buttonTempleRun);
        btnYoutube = (Button) findViewById(R.id.buttonYoutube);
        btnChrome.setOnClickListener(this);
        btnYoutube.setOnClickListener(this);
        btntemple.setOnClickListener(this);
        btnWhatsapp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonChrome:
                Intent chrome = getPackageManager().getLaunchIntentForPackage("com.android.chrome");
                startActivity(chrome);
                break;
            case R.id.buttonTempleRun:
                Intent Temple = getPackageManager().getLaunchIntentForPackage("com.imangi.templerun2");
                startActivity(Temple);
                break;
            case R.id.buttonWhatsapp:
                Intent WhatsApp = getPackageManager().getLaunchIntentForPackage("com.whatsapp");
                startActivity(WhatsApp);
                break;
            case R.id.buttonYoutube:
                Intent youtube = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
                startActivity(youtube);
                break;


        }
    }
}
