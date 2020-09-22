package com.example.user.passwordmanagement;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LogIn extends AppCompatActivity {
    TextView textView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        textView= (TextView) findViewById(R.id.text);
        SharedPreferences sharedPreferences = getSharedPreferences("pref",MODE_PRIVATE);
        String u = sharedPreferences.getString("UNAME","No data found");
        textView.setText("Hello "+u);
    }
}
