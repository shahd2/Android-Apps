package com.example.user.hci;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText user,pass;
    Button log,newu;
    static int c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(c==0)
        {
            createShortcut();
            c=1;}
        user= (EditText) findViewById(R.id.uname);
        pass= (EditText) findViewById(R.id.pass);
        log = (Button) findViewById(R.id.login);
        newu = (Button) findViewById(R.id.btnShow);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = user.getText().toString();
                String p=pass.getText().toString();
                SharedPreferences sharedPreferences = getSharedPreferences("pref",MODE_PRIVATE);
                String u = sharedPreferences.getString("UNAME","No data found");
                String pa=sharedPreferences.getString("PASS","No data found");
                if(u.equals(uname) && pa.equals(p))
                {
                    startActivity(new Intent(MainActivity.this,Log_in.class));
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Username and Password do not match.", Toast.LENGTH_SHORT).show();
                }

            }
        });
        newu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Register.class));
            }
        });
    }
    public void createShortcut(){
        Intent intentShortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        intentShortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, getString(R.string.app_name));
        Parcelable appicon = Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.drawable.mainicon);
        intentShortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, appicon);
        intentShortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(getApplicationContext(), SplashScreen.class));
        intentShortcut.putExtra("duplicate", false);
        sendBroadcast(intentShortcut);
    }

}

