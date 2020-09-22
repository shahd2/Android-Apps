package com.example.user.multipurpose;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class BmiCalculator extends AppCompatActivity {

    Button button;
    EditText wt, ht;
    TextView bmi, result,gen;
    String uni;
    float bm;
    RadioGroup radioGroup1,radioGroup2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);
        wt = (EditText) findViewById(R.id.wt);
        ht = (EditText) findViewById(R.id.ht);
        bmi = (TextView) findViewById(R.id.bmi);
        gen= (TextView) findViewById(R.id.gen);
        result = (TextView) findViewById(R.id.comment);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                radioGroup1 = (RadioGroup) findViewById(R.id.gender);
                radioGroup2 = (RadioGroup) findViewById(R.id.unit);
                int selectedId1 = radioGroup1.getCheckedRadioButtonId();
                RadioButton radioButton1 = (RadioButton) findViewById(selectedId1);
                String gender=radioButton1.getText().toString();
                gen.setText("Your gender is "+gender);
                int selectedId2 = radioGroup2.getCheckedRadioButtonId();
                RadioButton radioButton2 = (RadioButton) findViewById(selectedId2);
                uni=radioButton2.getText().toString();
                if(TextUtils.isEmpty(wt.getText().toString())||TextUtils.isEmpty(ht.getText().toString())) {
                    Toast.makeText(BmiCalculator.this, "Please enter the details", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    gen.setVisibility(View.VISIBLE);
                    bmi.setVisibility(View.VISIBLE);
                    result.setVisibility(View.VISIBLE);
                    float weight = Float.parseFloat(wt.getText().toString());
                    float height = Float.parseFloat(ht.getText().toString());

                    if("Metric Unit(kg,cm)".equals(uni))
                        bm = (float) ((weight * 10000) / (height * height));
                    else
                        bm = (float) ((weight * 703) / (height * height));
                    String mytext = Float.toString(bm);
                    bmi.setText("Your BMI is " + mytext);
                    if (bm < 18.5)
                        result.setText("You are Underweight.Eat Properly.");
                    else if (bm >= 18.5 && bm <= 24.9)
                        result.setText("You are Normal Weight.");
                    else if (bm >= 25 && bm <= 29.9)
                        result.setText("You are Overweight. Some exercise will be helpful");
                    else
                        result.setText("You are Obese. Consult a doctor.");

                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.optionmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.bmi:

                Intent intent1 = new Intent(BmiCalculator.this,Second.class);
                BmiCalculator.this.startActivity(intent1);

                break;
            case R.id.gen:
                Intent intent2 = new Intent(BmiCalculator.this,Third.class);
                BmiCalculator.this.startActivity(intent2);
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
