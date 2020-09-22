package com.example.user.maploc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class message extends AppCompatActivity {
EditText mssg;
    Button ms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        mssg= (EditText) findViewById(R.id.getmsg);
        ms= (Button) findViewById(R.id.mg);
        ms.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String m=mssg.getText().toString();
                SharedPreferences sharedPreferences2 = getSharedPreferences("MESSAGE", MODE_PRIVATE);
                SharedPreferences.Editor editor2 = sharedPreferences2.edit();
               // SharedPreferences sharedPreferences1 = getSharedPreferences("NUMBER", MODE_PRIVATE);
                //SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                editor2.putString("MSG", m);
                editor2.commit();
                Toast.makeText(message.this, "Message Saved", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(message.this, MapsActivity.class));
            }

        });
    }
}
