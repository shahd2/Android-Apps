package com.example.user.hci;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Questions extends AppCompatActivity {

    TextView textView;
    Button button;
    EditText cwt,gwt, ht,age;
    String goal,activity,uni,gender;
    float bmr,bmrfinal;
    int ag;
    float weight,height;
    RadioGroup radioGroup1,radioGroup2,radioGroup3,radioGroup4;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        textView= (TextView) findViewById(R.id.name);
        SharedPreferences sharedPreferences = getSharedPreferences("pref",MODE_PRIVATE);
        String u = sharedPreferences.getString("UNAME","No data found");
        textView.setText("Hello "+u);
        cwt = (EditText) findViewById(R.id.cwt);
        gwt = (EditText) findViewById(R.id.gwt);
        ht = (EditText) findViewById(R.id.ht);
        age=(EditText) findViewById(R.id.age);

                button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                radioGroup1 = (RadioGroup) findViewById(R.id.qa);
                radioGroup2 = (RadioGroup) findViewById(R.id.qb);
                radioGroup3 = (RadioGroup) findViewById(R.id.qc);
                radioGroup4 = (RadioGroup) findViewById(R.id.unit1);

                if(radioGroup1.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    // get selected radio button from radioGroup
                    int selectedId1 = radioGroup1.getCheckedRadioButtonId();
                    RadioButton radioButton1 = (RadioButton) findViewById(selectedId1);
                    goal=radioButton1.getText().toString();
                }
                if(radioGroup2.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    // get selected radio button from radioGroup
                    int selectedId2 = radioGroup2.getCheckedRadioButtonId();
                    RadioButton radioButton2 = (RadioButton) findViewById(selectedId2);
                    activity=radioButton2.getText().toString();
                }
                if(radioGroup3.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    // get selected radio button from radioGroup
                    int selectedId3 = radioGroup3.getCheckedRadioButtonId();
                    RadioButton radioButton3 = (RadioButton) findViewById(selectedId3);
                    gender=radioButton3.getText().toString();
                }
                if(radioGroup4.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    // get selected radio button from radioGroup
                    int selectedId4 = radioGroup4.getCheckedRadioButtonId();
                    RadioButton radioButton4 = (RadioButton) findViewById(selectedId4);
                    uni=radioButton4.getText().toString();
                }

                if(TextUtils.isEmpty(goal)||TextUtils.isEmpty(activity)||TextUtils.isEmpty(gender)||TextUtils.isEmpty(uni)||TextUtils.isEmpty(gwt.getText().toString())||TextUtils.isEmpty(cwt.getText().toString())||TextUtils.isEmpty(ht.getText().toString()))
                {
                        Toast.makeText(Questions.this, "Please fill all the details", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                   ag=Integer.parseInt(age.getText().toString());
                     weight = Float.parseFloat(cwt.getText().toString());
                     height = Float.parseFloat(ht.getText().toString());
                    if("Imperial Unit(lb,in)".equals(uni))
                    {
                       weight*=(0.45359237);
                       height*=2.54;
                    }

                    if(gender=="Male")
                    {
                        bmr= (float) (66.4730+(13.7516 *weight)+(5.0033 *height)-(6.7550*ag));
                    }
                    else
                    {
                        bmr= (float) (655.0955 + (9.5634 *weight) + (1.8496 *height) - (4.6756 *ag));
                    }

                   if(activity.equals("Not very active"))
                    {bmrfinal= (float) (bmr*1.2);}
                    else if(activity.equals("Lightly Active"))
                    {
                        bmrfinal= (float) (bmr*1.375);
                    }
                    else if(activity.equals("Active")){
                        bmrfinal= (float) (bmr*1.55);
                   }
                    else if(activity.equals("Very Active")){
                        bmrfinal= (float) (bmr*1.725);
                   }
                        SharedPreferences sharedPreferences = getSharedPreferences("Details", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Goal", goal);
                    //editor.putString("Activity", activity);
                    //editor.putString("Gender", gender);
                    //editor.putString("Unit", uni);
                    editor.putString("Height", ht.getText().toString());
                    editor.putString("CWT", cwt.getText().toString());
                    editor.putString("GWT", gwt.getText().toString());
                    editor.putString("BMR", String.valueOf(bmrfinal));
                    editor.commit();
                    startActivity(new Intent(Questions.this, MainActivity.class));
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
