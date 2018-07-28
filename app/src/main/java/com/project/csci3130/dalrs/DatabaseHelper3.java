package com.project.csci3130.dalrs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTransactionListener;

public class DatabaseHelper3 extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "summerschedule.db";
    public static final String TABLE_NAME = "users_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "Time";
    public static final String COL3 = "Monday";
    public static final String COL4 = "Tuesday";
    public static final String COL5 = "Wednesday";
    public static final String COL6 = "Thursday";
    public static final String COL7 = "Friday";
    private static final int DATABASE_VERSION = 2;
    int which_table;


    public DatabaseHelper3(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " TIME TEXT, MONDAY TEXT, TUESDAY TEXT,WEDNESDAY TEXT, THURSDAY TEXT, FRIDAY TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean addData(String Time, String Monday, String Tuesday, String Wednesday, String Thursday, String Friday) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, Time);
        contentValues.put(COL3, Monday);
        contentValues.put(COL4, Tuesday);
        contentValues.put(COL5, Wednesday);
        contentValues.put(COL6, Thursday);
        contentValues.put(COL7, Friday);

        long result = db.insert(TABLE_NAME, null, contentValues);
        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }


    }

    public void changes(String whichday, String coursename, String sh) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        db.execSQL("Update users_data SET " + whichday + " =\'" + coursename + "\' WHERE Time=\'" + sh + "\'");
    }

    public boolean updater(String id, String time, String Monday, String Tuesday, String Wednesday, String Thursday, String Friday) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(COL2, time);
        contentValue.put(COL3, Monday);
        contentValue.put(COL4, Tuesday);
        contentValue.put(COL5, Wednesday);
        contentValue.put(COL6, Thursday);
        contentValue.put(COL7, Friday);
        db.update(TABLE_NAME, contentValue, "ID=?", new String[]{id});
        return true;
    }

    //query for 1 week repeats
    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

}