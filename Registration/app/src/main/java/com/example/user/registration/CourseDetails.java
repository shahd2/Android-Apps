package com.example.user.registration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class CourseDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.aboutus:

                Intent intent1 = new Intent(this,AboutUs.class);
                this.startActivity(intent1);

                break;
            case R.id.course:
                Intent intent2 = new Intent(this,CourseDetails.class);
                this.startActivity(intent2);
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}

