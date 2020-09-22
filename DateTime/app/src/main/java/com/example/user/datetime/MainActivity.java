package com.example.user.datetime;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.DatePickerDialog;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
public class MainActivity extends AppCompatActivity {
    TextView date, timePicker, textView, print;
    WebView wv;
    Calendar calendar = Calendar.getInstance();
    int uDate = calendar.get(Calendar.DAY_OF_MONTH);
    int uMonth = calendar.get(Calendar.MONTH);
    int uYear = calendar.get(Calendar.YEAR);
    int uhour = calendar.get(Calendar.HOUR_OF_DAY);
    int umin = calendar.get(Calendar.MINUTE);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date = (TextView) findViewById(R.id.date);
        print = (TextView) findViewById(R.id.print);
        Intent intent = new Intent(MainActivity.this, NameActivity.class);
        startActivityForResult(intent, 1);
        textView = (TextView) findViewById(R.id.textView);
        wv = (WebView) findViewById(R.id.web);
        wv.loadUrl("https://developer.android.com/index.html");
        Switch s = (Switch) findViewById(R.id.sw);
        final NumberPicker np = (NumberPicker) findViewById(R.id.number);
        np.setMinValue(1);
        np.setMaxValue(50);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                int num = np.getValue();
                Toast.makeText(MainActivity.this, "Your no. is " + num, Toast.LENGTH_SHORT).show();
            }
        });
        timePicker = (TextView) findViewById(R.id.timePicker);
        //textView.setText(uDate+"/"+(uMonth + 1)+"/"+uYear);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, onDateSetListener, uYear, uMonth, uDate).show();
            }
        });
        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(MainActivity.this, 0, onTimeSetListener, uhour, umin, true).show();
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setTitle("Enter your information");
                dialog.setContentView(R.layout.custom);
                final EditText fname = (EditText) dialog.findViewById(R.id.fname);
                final EditText lname = (EditText) dialog.findViewById(R.id.lname);
                Button buttonSubmit = (Button) dialog.findViewById(R.id.submit);
                Button buttonCancel = (Button) dialog.findViewById(R.id.cancel);
                buttonSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, fname.getText() + " " + lname.getText(), Toast.LENGTH_SHORT).show();
                        print.setText(fname.getText()+ ";" + lname.getText());
                    }
                });
                buttonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton s, boolean isChecked) {
                if (s.isChecked()) {
                    s.setText("ON");
                } else {
                    s.setText("OFF");
                }
            }
        });
    }

    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            date.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
        }
    };
    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            timePicker.setText(hourOfDay + ":" + minute);
        }
    };

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String names = data.getStringExtra("NAMES");
                textView.setText(names);
            }

        }
    }
}
