package com.example.eddie.shapeshift;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Eddie on 4/21/18.
 */

public class DatabaseHelperActivity extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "loglist.db";
    public static final String TABLE_NAME = "loglist_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "ITEM1";
    //Add more columns here

    public DatabaseHelperActivity(Context context) {
        super(context, DATABASE_NAME, null, 2);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE" + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ITEM1 TEXT)";
        db.execSQL(createTable);
    }

 @Override
 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);


 }


    public boolean addData(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        }
        else {
            return true;

        }
    }

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;

    }

}
