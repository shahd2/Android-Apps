package com.example.user.phonebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import static android.provider.MediaStore.Images.Media.getBitmap;


public class DBAdapter {
    public static final String DBNAME = "cetpa1";
    public static final String TABLENAME = "Contact1";
    public static final int DBVERSION = 1;

    public static final String COLID = "ID";
    public static final String COLFANME = "NAME";
    public static final String COLNUM = "NUM";
    public static final String COLIMG = "IMAGE";

    String CREATETABLE = "CREATE TABLE "+TABLENAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR,NUM VARCHAR,IMAGE BLOB)";
    String ORDER="SELECT * from "+TABLENAME+" ORDER BY "+COLFANME;

    SQLiteDatabase db;
    DBHelper dbHelper;
//    Cursor cursor = db.rawQuery(ORDER, null);
    DBAdapter(Context context){
        dbHelper = new DBHelper(context);
    }
    DBAdapter openDatabase(){
        db = dbHelper.getWritableDatabase();
        return this;

    }
    public void insertRecord(String fname, String num,byte[] img){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLFANME,fname);
        contentValues.put(COLNUM,num);
        //Bitmap bitmap = BitmapFactory.decodeResource();
        contentValues.put(COLIMG,img);
        db.insert(TABLENAME,null,contentValues);

    }
    public void deleteAllRecords(){
        db.delete(TABLENAME,null,null);
    }
    public void updateRecord(String id, String fname, String num, byte[] image){
       // String selectQuery = "SELECT  * FROM contacts ORDER BY name";
        ContentValues contentValues = new ContentValues();
       // contentValues.put(COLID,id);
        contentValues.put(COLFANME,fname);
        contentValues.put(COLNUM,num);
        db.update(TABLENAME,contentValues,id+"="+COLID,null);
        contentValues.put(COLIMG, String.valueOf(image));
    }
    public void deleteRecord(String id){
        db.delete(TABLENAME,id+"="+COLID,null);
    }
    Cursor getAllValues(){
        String[] COLUMNS = {COLID,COLFANME,COLNUM,COLIMG};
        return db.query(TABLENAME,COLUMNS,null,null,null,null,null);
    }
    public class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context) {
            super(context, DBNAME, null, DBVERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATETABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    }
/*    public String[] getAllName()
    {
        Cursor cursor = this.db.query(TABLENAME, new String[] {COLFANME}, null, null, null, null, null);

        if(cursor.getCount() >0)
        {
            String[] str = new String[cursor.getCount()];
            int i = 0;

            while (cursor.moveToNext())
            {
                str[i] = cursor.getString(cursor.getColumnIndex(COLFANME));
                i++;
            }
            return str;
        }
        else
        {
            return new String[] {};
        }
    }
*/
}
