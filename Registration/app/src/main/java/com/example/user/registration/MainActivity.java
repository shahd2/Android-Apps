package com.example.user.registration;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText fname,lname,num;
    TextView textView1,textView2,date;
    RadioGroup radioGroup;
    CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5;
    Calendar calendar = Calendar.getInstance();
    int uDate = calendar.get(Calendar.DAY_OF_MONTH);
    int uMonth = calendar.get(Calendar.MONTH);
    int uYear = calendar.get(Calendar.YEAR);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        num= (EditText) findViewById(R.id.num);
        date= (TextView) findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, onDateSetListener, uYear, uMonth, uDate).show();
            }
        });
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2= (TextView) findViewById(R.id.textView2);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f = fname.getText().toString();
                String l = lname.getText().toString();
                String n =num.getText().toString();
                String gender;
                int check = 0;
                Intent intent=new Intent(MainActivity.this,Second.class);
                Bundle bundle=new Bundle();
                radioGroup = (RadioGroup) findViewById(R.id.gender);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                gender=radioButton.getText().toString();
               checkBox1= (CheckBox) findViewById(R.id.checkBox1);
                checkBox2= (CheckBox)findViewById(R.id.checkBox2);
                checkBox3= (CheckBox)findViewById(R.id.checkBox3);
                checkBox4= (CheckBox)findViewById(R.id.checkBox4);
                checkBox5= (CheckBox)findViewById(R.id.checkBox5);
                StringBuilder result=new StringBuilder();
                if(checkBox1.isChecked())
                { 
                    check+=12000;
                    result.append("Android\n");
                }
                if(checkBox2.isChecked())
                {
                    check+=10000;
                    result.append("Core Java\n");

                }
                if(checkBox3.isChecked())
                {
                    check+=12000;
                    result.append("Advance Java\n");

                }
                if(checkBox4.isChecked()) {
                    check += 10000;
                    result.append("C/C++\n");
                }
                if(checkBox5.isChecked()) {
                    check += 14000;
                    result.append("Big Data\n");
                }
                int i=result.length();
                result.setLength(i-1);
                //Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();

                if(TextUtils.isEmpty(fname.getText().toString())||TextUtils.isEmpty(lname.getText().toString())||TextUtils.isEmpty(num.getText().toString()))
                {
                    Toast.makeText(MainActivity.this, "Please enter the details", Toast.LENGTH_LONG).show();
                    return;
                }
                else{

                    String D=date.getText().toString();
                    bundle.putString("FNAME",f);
                    bundle.putString("LNAME",l);
                    bundle.putString("NUM",n);
                    bundle.putString("DATE",D);
                    bundle.putString("GEND",gender);
                    StringBuilder course=result;
                    bundle.putString("COUR", String.valueOf(course));
                    //Toast.makeText(MainActivity.this, String.valueOf(check), Toast.LENGTH_SHORT).show();
                    bundle.putString("FEE", String.valueOf(check));
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
            }
        });
    }


            DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
        {
            date.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
        }
    };
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.optionmenu,menu);
        return super.onCreateOptionsMenu(menu);
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

