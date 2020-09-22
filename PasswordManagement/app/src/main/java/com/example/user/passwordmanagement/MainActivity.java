package com.example.user.passwordmanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText user,pass;
    Button log,newu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                    startActivity(new Intent(MainActivity.this,LogIn.class));
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
        public void onBackPressed()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.finish();
            }
        });
        builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
