package com.example.user.datetime;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

public class NameActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        radioGroup = (RadioGroup) findViewById(R.id.rgNames);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int id = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(id);
                String names = radioButton.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("NAMES",names);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        final ToggleButton t = (ToggleButton) findViewById(R.id.toggleButton);
        t.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton s, boolean isChecked) {
                if (t.isChecked()) {
                    t.setText("ON");
                } else {
                    t.setText("OFF");
                }
            }
        });
    }
}
