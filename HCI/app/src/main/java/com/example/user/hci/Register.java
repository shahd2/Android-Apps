package com.example.user.hci;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText user, password, confirm;
    Button button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        user = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.password);
        confirm = (EditText) findViewById(R.id.editText3);
        button = (Button) findViewById(R.id.but);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String uname = user.getText().toString();
                String p = password.getText().toString();
                String cp = confirm.getText().toString();
                if (p.equals(cp)) {
                    SharedPreferences sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("UNAME", uname);
                    editor.putString("PASS", p);
                    editor.commit();
                    startActivity(new Intent(Register.this,Questions.class));
                } else {
                    Toast.makeText(Register.this, "Password do not match.", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    public void onBackPressed() {

            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

}