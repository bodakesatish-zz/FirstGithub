package com.hvdesai.register;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by SATISH BODAKE on 9/17/2016.
 */
public class MyDatabase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MyDB.db";

    //List Of Tables
    public String TABLE_STUDENT = "student";

    //List Of Columns
    public String COL_ID = "id",
                  COL_NAME = "name",
                  COL_COUNTRY = "country",
                  COL_EMAIL = "email",
                  COL_PHONE = "phone",
                  COL_USERNAME = "username",
                  COL_PASSWORD = "password";
    String LOG_TAG = "MyDatabase";



    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i(LOG_TAG,"In MyDatabase Constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase db){
     Log.i(LOG_TAG,"In MyDatabase onCreate");
     // Create Tables
     //Query->CREATE TABLE IF NOT EXISTS student(id INTEGER ,name TEXT,country TEXT,email TEXT,phone TEXT,username TEXT,password TEXT)
     String CREATE_QUERY_TABLE_STUDENT = "CREATE TABLE IF NOT" +
             " EXISTS " + TABLE_STUDENT +"("+COL_ID+" INTEGER ,"+
             COL_NAME +" TEXT,"+COL_COUNTRY+" TEXT,"+
             COL_EMAIL +" TEXT,"+COL_PHONE+" TEXT,"+
             COL_USERNAME +" TEXT,"+COL_PASSWORD+" TEXT);";
     Log.i(LOG_TAG,"CREATE_QUERY_TABLE_STUDENT->"
             +CREATE_QUERY_TABLE_STUDENT);
     db.execSQL(CREATE_QUERY_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,
                          int newVersion) {
        Log.i(LOG_TAG,"In MyDatabase onUpgrade");
        Log.i(LOG_TAG,"oldVersion->"+oldVersion);
        Log.i(LOG_TAG,"newVersion->"+newVersion);
        //Your code here
    }

    //insert student data in student table
    public int insert_student_data(
            String str_name,String str_email,
            String str_country,String str_phone,
            String str_password
    )
    {
        Log.i(LOG_TAG,"In insert_student_data");
        int result = -1;//fail on -1 else success
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME,str_name);
        cv.put(COL_EMAIL,str_email);
        cv.put(COL_COUNTRY,str_country);
        cv.put(COL_PHONE,str_phone);
        cv.put(COL_PASSWORD,str_password);
        Log.i(LOG_TAG,"ContentValues->"+cv);
        SQLiteDatabase db = this.getWritableDatabase();
        long rowID = db.insert(TABLE_STUDENT,null,cv);
        Log.i(LOG_TAG,"ContentValues->"+cv);
        result = (int) rowID;
        Log.i(LOG_TAG,"Result->"+result);
        return result;
    }










}
