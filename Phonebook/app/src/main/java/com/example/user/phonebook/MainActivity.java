package com.example.user.phonebook;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.R.attr.data;
import static com.example.user.phonebook.R.id.insert;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener  {
    DBAdapter dbAdapter;
    ListView listView;
    Cursor cursor;
    ImageView imageView;
    ContentResolver resolver;
    SearchView search;
    int INSERTRECORD = 1;
    int DELETEALLRECORD = 2;
    int UPDATERECORD = 3;
    int DELETERECORD = 4;
    int CONTACTIMAGE=5;
    int position;
    String NAME;
    String NUM;
    byte[] IMAG;
    //Bitmap icon;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbAdapter = new DBAdapter(this);
        dbAdapter.openDatabase();
        listView = (ListView) findViewById(R.id.list_view);
        updateListView();
        registerForContextMenu(listView);
        listView.setOnItemLongClickListener(this);
        final EditText editText = (EditText) findViewById(R.id.srchHint);
        search = (SearchView) findViewById(R.id.searchView);
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                // MainActivity.this.customAdapter.getFilter().filter(cs);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }
        });
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                               return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //customAdapter.filter(newText);
                return false;
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.optionsmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.insertRecord:
                showDialog(INSERTRECORD);
                break;
            case R.id.deleteAllRecord:
                showDialog(DELETEALLRECORD);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.contextmenu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.call:
            {  cursor.moveToPosition(position);
                NUM=cursor.getString(2);
                int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

             if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},123);
                } else {
                    startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+NUM)));
                }
                updateListView();
                }

        break;
            case R.id.msg:
            {  cursor.moveToPosition(position);
                NUM=cursor.getString(2);
                int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},123);
                } else {
                    startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("sms:"+NUM)));
                }
                updateListView();
            }

            break;
            case R.id.updateRecord:
                showDialog(UPDATERECORD);
                break;
            case R.id.deleteRecord:
                showDialog(DELETERECORD);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == INSERTRECORD) {
            final Dialog dialog = new Dialog(MainActivity.this);
            dialog.setTitle("Enter your information..");
            dialog.setContentView(R.layout.insertdialog);

            final EditText etFname = (EditText) dialog.findViewById(R.id.editTextFname);
            final EditText etNum = (EditText) dialog.findViewById(R.id.editTextNum);
            ImageView etimage= (ImageView) dialog.findViewById(R.id.circleImageView1);
            Button insert = (Button) dialog.findViewById(R.id.insert);
            Button cancel = (Button) dialog.findViewById(R.id.cancel);
            etimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    showDialog(CONTACTIMAGE);


                }
            });

            insert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NAME = etFname.getText().toString();
                    NUM = etNum.getText().toString();
                    if ((TextUtils.isEmpty(etFname.getText().toString()))||(TextUtils.isEmpty(etNum.getText().toString())))
                    {
                        Toast.makeText(MainActivity.this, "Feilds are empty.", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        dbAdapter.insertRecord(NAME,NUM,IMAG);
                        updateListView();
                        Toast.makeText(MainActivity.this, "Successfully inserted...", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }

                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        } else if (id == DELETEALLRECORD) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Delete All Records");
            builder.setMessage("Are you sure?");
            builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dbAdapter.deleteAllRecords();
                    updateListView();
                    Toast.makeText(MainActivity.this, "All records deleted..", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
        } else if (id == UPDATERECORD) {
            final Dialog dialog1 = new Dialog(MainActivity.this);
            dialog1.setTitle("Update your record");
            dialog1.setContentView(R.layout.updatedialog);
            final EditText uName = (EditText) dialog1.findViewById(R.id.editTextUPdateFname);
            final EditText uNum = (EditText) dialog1.findViewById(R.id.editTextUPdateNum);

            Button update = (Button) dialog1.findViewById(R.id.update);
            Button uCancel = (Button) dialog1.findViewById(R.id.UPdatecancel);
            cursor.moveToPosition(position);
            final String _id = cursor.getString(0);
            final String _name = cursor.getString(1);
            String _num = cursor.getString(2);

            uName.setText(_name);
            uNum.setText(_num);


            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String fName = uName.getText().toString();
                    String Num = uNum.getText().toString();

                    dbAdapter.updateRecord(_id,fName,Num,IMAG);
                    Toast.makeText(MainActivity.this, "Record Updated...", Toast.LENGTH_SHORT).show();
                    updateListView();
                    dialog1.dismiss();
                }
            });
            uCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog1.dismiss();
                }
            });
            dialog1.show();

        } else if (id == DELETERECORD) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
            builder1.setTitle("DELETE");
            builder1.setMessage("Are you sure?");
            builder1.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    cursor.moveToPosition(position);
                    String dID = cursor.getString(0);
                    dbAdapter.deleteRecord(dID);
                    updateListView();
                    Toast.makeText(MainActivity.this, "Record Deleted....", Toast.LENGTH_SHORT).show();
                }
            });
            builder1.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder1.show();
        }
        if (id == CONTACTIMAGE) {
            final Dialog dialog2 = new Dialog(MainActivity.this);
            dialog2.setTitle("Select Option");
            dialog2.setContentView(R.layout.imagedialog);
            Button pick = (Button) dialog2.findViewById(R.id.button1);
            Button take = (Button) dialog2.findViewById(R.id.button2);
            Button cancel = (Button) dialog2.findViewById(R.id.button3);
            pick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 0);
                    dialog2.dismiss();
                    updateListView();
                }
            });
            take.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 1);
                    dialog2.dismiss();
                    updateListView();
                }});
             cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog2.dismiss();
                    }
                });
            dialog2.show();
        }
        return super.onCreateDialog(id);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        this.position = position;
        return false;
    }

    public class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return cursor.getCount();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = getLayoutInflater();
            convertView = layoutInflater.inflate(R.layout.customlistview, null);
            imageView= (ImageView)convertView.findViewById(R.id.image);

            TextView FNAME = (TextView) convertView.findViewById(R.id.textViewFname);
            TextView NUM = (TextView) convertView.findViewById(R.id.textViewLname);
           cursor.moveToPosition(position);
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            final String num = cursor.getString(2);

            FNAME.setText(name);
            NUM.setText(num);

            return convertView;
        }

    }
    public void updateListView(){
        cursor = dbAdapter.getAllValues();
        customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

    }
    public void onBackPressed()
    {
        android.support.v7.app.AlertDialog.Builder builder=new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    imageView.setImageURI(selectedImage);
                }
                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    imageView.setImageURI(selectedImage);
                }
                break;
        }
    }

}
                /*
imageView.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View arg0) {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, 0);
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto , 1);
        }
        });
protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
        case 0:
        if(resultCode == RESULT_OK){
        Uri selectedImage = imageReturnedIntent.getData();
        imageView.setImageURI(selectedImage);
        }

        break;
        case 1:
        if(resultCode == RESULT_OK){
        Uri selectedImage = imageReturnedIntent.getData();
        imageView.setImageURI(selectedImage);
        }
        break;
        }
        }
*/