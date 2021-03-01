package com.example.aidlclient;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Initialise constants
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "AIDLController";
    private static final String TABLE_NAME = "Time";
    private static final String[] COLUMN_NAMES = {"Logno","Src", "Msg", "Timestamp"};

    // Construct CREATE query string
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NAMES[0] + " TEXT, " +
                    COLUMN_NAMES[1] + " TEXT, " +
                    COLUMN_NAMES[2] + " TEXT, " +
                    COLUMN_NAMES[3] + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creates the database if it doesn't exist and adds Messages table
        // Execute SQL query.
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addInfo(String l,String s,String m,String t) {
        // Put Sms data into ContentValues object to insert into database
        ContentValues row = new ContentValues();
        row.put(this.COLUMN_NAMES[0], l);
        row.put(this.COLUMN_NAMES[1], s);
        row.put(this.COLUMN_NAMES[2], m);
        row.put(this.COLUMN_NAMES[3], t);

        // Get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        // insert sms details into a new row, return the id of the new row.
        db.insert(TABLE_NAME, null, row);
        db.close();
    }

}

