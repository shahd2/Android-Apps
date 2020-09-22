package com.example.user.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.bmi:
                Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(this,Second.class);
                this.startActivity(intent1);

                break;
            case R.id.gen:
                Intent intent2 = new Intent(this,Third.class);
                this.startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
