package com.example.user.hci;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Snack extends AppCompatActivity {
    private LinearLayout mLayout;
    private EditText fr,vege,prot,cms,bevr;
    TextView tfr,tvege,tprot,tcms,tbevr;
    private Button button1,button2,button3,button4,button5;
    float cal;
    int c;
    float ca1,ca2,ca3,ca4,ca5;
    String f,vr,p,cc,b;
    String[] fruits = {"", "Apple", "Banana", "Grapes", "Orange", "Pear", "Peach","Pineapple","Strawberry","Watermelon","Others"};
    float [] fcal={0,59,151,100,53,82,67,82,53,50};
    String[] cm = {"", "Bread, white", "Butter", "Caesar salad", "Cheeseburger", "Hamburger","Dark Chocolate","Corn","Pizza","Rice","Sandwich","Others"};
    float [] ccal={0,75,102, (float) 183.67,285,250,155,132,285,206,200};
    String[] pro = {"", "Beef, regular, cooked", "Chicken, cooked", "Tofu", "Egg","Fish, Catfish, cooked","Pork, cooked","Shrimp, cooked","Others"};
    float [] pcal={0,71,68, (float) 21.50,78,68, (float) 68.50,28};
    String[] veg = {"", "Asparagus", "Broccoli", "Carrots", "Cucumber", "Eggplant", "Lettuce", "Tomato","Potato","Others"};
    float[] vcal={0,27,45,50,17,35,5,22,213};
    String[] bev = {"", "Beer", "Coca-Cola Classic", "Diet Coke", "Milk (1%)","Milk (2%)","Milk (Whole)","Orange Juice","Apple cider","Yogurt (low-fat)","Yogurt (non-fat)","Others"};
    float[] bcal={0,154,150,0,102,122,146,111,117,154,110};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack);
        Spinner spinner1 = (Spinner) findViewById(R.id.fab);
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(Snack.this, android.R.layout.simple_list_item_1, fruits);
        spinner1.setAdapter(arrayAdapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                f = parent.getItemAtPosition(position).toString();
                tfr = (TextView) findViewById(R.id.type1);
                fr = (EditText) findViewById(R.id.fser);
                c = Integer.valueOf(fr.getText().toString().trim());
                if (position == 3 || position == 7 || position == 8 || position == 9)
                    tfr.setText("Cup");
                else
                    tfr.setText("Whole");
                if (position == 1) {
                    ca1 = c * fcal[1];
                    cal += (ca1);
                } else if (position == 2) {
                    ca1 = c * fcal[2];
                    cal += (ca1);
                } else if (position == 3) {
                    ca1 = c * fcal[3];
                    cal += (ca1);
                } else if (position == 4) {
                    ca1 = c * fcal[4];
                    cal += (ca1);
                } else if (position == 5) {
                    ca1 = c * fcal[5];
                    cal += (ca1);
                } else if (position == 6) {
                    ca1 = c * fcal[6];
                    cal += (ca1);
                } else if (position == 7) {
                    ca1 = c * fcal[7];
                    cal += (ca1);
                } else if (position == 8) {
                    ca1 = c * fcal[8];
                    cal += (ca1);
                } else if (position == 9) {
                    ca1 = c * fcal[9];
                    cal += (ca1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Spinner spinner2 = (Spinner) findViewById(R.id.veg);
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(Snack.this, android.R.layout.simple_list_item_1, veg);
        spinner2.setAdapter(arrayAdapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vr = parent.getItemAtPosition(position).toString();
                tvege = (TextView) findViewById(R.id.type2);
                vege = (EditText) findViewById(R.id.vser);
                c = Integer.valueOf(vege.getText().toString().trim());
                if (position == 4)
                    tvege.setText("Whole");
                else
                    tvege.setText("Cup");
                if (position == 1) {
                    ca2 = c * vcal[1];
                    cal += (ca2);
                } else if (position == 2) {
                    ca2 = c * vcal[2];
                    cal += (ca2);
                } else if (position == 3) {
                    ca2 = c * vcal[3];
                    cal += (ca2);
                } else if (position == 4) {
                    ca2 = c * vcal[4];
                    cal += (ca2);
                } else if (position == 5) {
                    ca2 = c * vcal[5];
                    cal += (ca2);
                } else if (position == 6) {
                    ca2 = c * vcal[6];
                    cal += (ca2);
                } else if (position == 7) {
                    ca2 = c * vcal[7];
                    cal += (ca2);
                } else if (position == 8) {
                    ca2 = c * vcal[8];
                    cal += (ca2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Spinner spinner3 = (Spinner) findViewById(R.id.pro);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Snack.this, android.R.layout.simple_list_item_1, pro);
        spinner3.setAdapter(arrayAdapter);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                p = parent.getItemAtPosition(position).toString();
                tprot = (TextView) findViewById(R.id.type3);
                prot = (EditText) findViewById(R.id.pser);
                c = Integer.valueOf(prot.getText().toString().trim());
                if (position == 4)
                    tprot.setText("Whole");
                else
                    tprot.setText("oz.");

                if (position == 1) {
                    ca3 = c * pcal[1];
                    cal += (ca3);
                } else if (position == 2) {
                    ca3 = c * pcal[2];
                    cal += (ca3);
                } else if (position == 3) {
                    ca3 = c * pcal[3];
                    cal += (ca3);
                } else if (position == 4) {
                    ca3 = c * pcal[4];
                    cal += (ca3);
                } else if (position == 5) {
                    ca3 = c * pcal[5];
                    cal += (ca3);
                } else if (position == 6) {
                    ca3 = c * pcal[6];
                    cal += (ca3);
                } else if (position == 7) {
                    ca3 = c * pcal[7];
                    cal += (ca3);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Spinner spinner4 = (Spinner) findViewById(R.id.cm);
        ArrayAdapter<String> arrayAdapter4 = new ArrayAdapter<String>(Snack.this, android.R.layout.simple_list_item_1, cm);
        spinner4.setAdapter(arrayAdapter4);
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cc = parent.getItemAtPosition(position).toString();
                tcms = (TextView) findViewById(R.id.type4);
                cms = (EditText) findViewById(R.id.cser);
                c = Integer.valueOf(cms.getText().toString().trim());
                if (position == 1 || position == 8)
                    tcms.setText("slice");
                else if (position == 3 || position == 7 || position == 9)
                    tcms.setText("cup");
                else if (position == 6)
                    tcms.setText("oz.");
                else if (position == 2)
                    tcms.setText("tablespoon");
                else if (position == 4 || position == 5 || position == 10)
                    tcms.setText("sandwhich");
                if (position == 1) {
                    ca4 = c * ccal[1];
                    cal += (ca4);
                } else if (position == 2) {
                    ca4 = c * ccal[2];
                    cal += (ca4);
                } else if (position == 3) {
                    ca4 = c * ccal[3];
                    cal += (ca4);
                } else if (position == 4) {
                    ca4 = c * ccal[4];
                    cal += (ca4);
                } else if (position == 5) {
                    ca4 = c * ccal[5];
                    cal += (ca4);
                } else if (position == 6) {
                    ca4 = c * ccal[6];
                    cal += (ca4);
                } else if (position == 7) {
                    ca4 = c * ccal[7];
                    cal += (ca4);
                } else if (position == 8) {
                    ca4 = c * ccal[8];
                    cal += (ca4);
                } else if (position == 9) {
                    ca4 = c * ccal[9];
                    cal += (ca4);
                } else if (position == 10) {
                    ca4 = c * ccal[10];
                    cal += (ca4);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Spinner spinner5 = (Spinner) findViewById(R.id.bev);
        ArrayAdapter<String> arrayAdapter5 = new ArrayAdapter<String>(Snack.this, android.R.layout.simple_list_item_1, bev);
        spinner5.setAdapter(arrayAdapter5);
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                b = parent.getItemAtPosition(position).toString();
                tbevr = (TextView) findViewById(R.id.type5);
                bevr = (EditText) findViewById(R.id.bser);
                c = Integer.valueOf(bevr.getText().toString().trim());
                if (position == 1 || position == 2)
                    tcms.setText("can");
                else
                    tcms.setText("cup");
                if (position == 1) {
                    ca5 = c * bcal[1];
                    cal += (ca5);
                } else if (position == 2) {
                    ca5 = c * bcal[2];
                    cal += (ca5);
                } else if (position == 3) {
                    ca5 = c * bcal[3];
                    cal += (ca5);
                } else if (position == 4) {
                    ca5 = c * bcal[4];
                    cal += (ca5);
                } else if (position == 5) {
                    ca5 = c * bcal[5];
                    cal += (ca5);
                } else if (position == 6) {
                    ca5 = c * bcal[6];
                    cal += (ca5);
                } else if (position == 7) {
                    ca5 = c * bcal[7];
                    cal += (ca5);
                } else if (position == 8) {
                    ca5 = c * bcal[8];
                    cal += (ca5);
                } else if (position == 9) {
                    ca5 = c * bcal[9];
                    cal += (ca5);
                } else if (position == 10) {
                    ca5 = c * bcal[10];
                    cal += (ca5);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mLayout = (LinearLayout) findViewById(R.id.linearLayout);
        //mEditText = (EditText) findViewById(R.id.editText);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(onClickListener);
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(onClickListener);
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(onClickListener);
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(onClickListener);
        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(onClickListener);
        TextView textView = new TextView(this);
        textView.setTextSize(15);
        textView.setText("");
        Button btt = new Button(this);
        btt.setText("Save");
        mLayout.addView(btt);
        btt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("M4", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("snack", String.valueOf(cal));
                editor.commit();
                startActivity(new Intent(Snack.this, Addplan.class));
            }
        });
    }
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.button1:
                    // fr=(EditText) findViewById(R.id.fser);
                    //c=Integer.valueOf(fr.getText().toString().trim());
                    mLayout.addView(createNewTextView(f+" "+ca1));
                    Toast.makeText(Snack.this, "Item Added",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button2:
                    Toast.makeText(Snack.this, "Item Added",
                            Toast.LENGTH_SHORT).show();
                    mLayout.addView(createNewTextView(vr+" "+ca2));
                    break;
                case R.id.button3:
                    Toast.makeText(Snack.this, "Item Added",
                            Toast.LENGTH_SHORT).show();
                    mLayout.addView(createNewTextView(p+" "+ca3));
                    //DO something
                    break;
                case R.id.button4:
                    Toast.makeText(Snack.this, "Item Added",
                            Toast.LENGTH_SHORT).show();
                    mLayout.addView(createNewTextView(cc+" "+ca4));
                    break;
                case R.id.button5:
                    Toast.makeText(Snack.this, "Item Added",
                            Toast.LENGTH_SHORT).show();
                    mLayout.addView(createNewTextView(b+" "+ca5));
                    break;
            }

        }
    };

    private TextView createNewTextView(String text) {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final TextView textView = new TextView(this);
        textView.setLayoutParams(lparams);
        textView.setTextSize(20);
        textView.setText(" " + text+" cal.");
        return textView;
    }


}