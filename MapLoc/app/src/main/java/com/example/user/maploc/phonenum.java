package com.example.user.maploc;

import android.app.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class phonenum extends AppCompatActivity {
    EditText num1,num2,num3;
    Button button,button1,button2,button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonenum);
        num1 = (EditText) findViewById(R.id.pnum1);
        num2 = (EditText) findViewById(R.id.pnum2);
        num3= (EditText) findViewById(R.id.pnum3);
        button = (Button) findViewById(R.id.save);
        button1=(Button) findViewById(R.id.button2);
        button2=(Button) findViewById(R.id.button3);
        button3=(Button) findViewById(R.id.button4);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent,1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent,2);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent,3);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String n1 = num1.getText().toString();
                String n2 = num2.getText().toString();
                String n3 = num3.getText().toString();
                if(TextUtils.isEmpty(num1.getText().toString())||TextUtils.isEmpty(num2.getText().toString())||TextUtils.isEmpty(num3.getText().toString())) {
                    Toast.makeText(phonenum.this, "Please enter all numbers", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    SharedPreferences sharedPreferences1 = getSharedPreferences("NUMBER", MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                    editor1.putString("NUM1", n1);
                    editor1.putString("NUM2", n2);
                    editor1.putString("NUM3", n3);
                    editor1.commit();
                    Toast.makeText(phonenum.this, "Numbers Saved", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(phonenum.this, MapsActivity.class));
                }
            }
        });
    }
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        switch (reqCode) {
            case 1:
                if (resultCode == android.app.Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c = getContentResolver().query(contactData, null, null, null, null);
                    if (c.moveToFirst()) {
                        String contactId = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                        String hasNumber = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                        String num = "";
                        if (Integer.valueOf(hasNumber) == 1) {
                            Cursor numbers = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                            while (numbers.moveToNext()) {
                                num = numbers.getString(numbers.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                //Toast.makeText(MapsActivity.this, "Number="+num, Toast.LENGTH_LONG).show();
                                num1.setText(num);
                            }
                        }
                    }
                    break;
                }
            case 2:
                if (resultCode == android.app.Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c = getContentResolver().query(contactData, null, null, null, null);
                    if (c.moveToFirst()) {
                        String contactId = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                        String hasNumber = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                        String num = "";
                        if (Integer.valueOf(hasNumber) == 1) {
                            Cursor numbers = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                            while (numbers.moveToNext()) {
                                num = numbers.getString(numbers.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                //Toast.makeText(MapsActivity.this, "Number="+num, Toast.LENGTH_LONG).show();
                                num2.setText(num);
                            }
                        }
                    }
                    break;}
            case 3:
                if (resultCode == android.app.Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c = getContentResolver().query(contactData, null, null, null, null);
                    if (c.moveToFirst()) {
                        String contactId = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                        String hasNumber = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                        String num = "";
                        if (Integer.valueOf(hasNumber) == 1) {
                            Cursor numbers = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                            while (numbers.moveToNext()) {
                                num = numbers.getString(numbers.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                //Toast.makeText(MapsActivity.this, "Number="+num, Toast.LENGTH_LONG).show();
                                num3.setText(num);
                            }
                        }
                    }
                    break;}
        }
    }
}