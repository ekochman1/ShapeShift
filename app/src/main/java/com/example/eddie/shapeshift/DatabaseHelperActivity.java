package com.example.eddie.shapeshift;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Eddie on 4/21/18.
 */

public class DatabaseHelperActivity extends SQLiteOpenHelper {

    public static final String LOG = "DatabaseHelper";
    public static final String DATABASE_NAME = "loglist.db";
    public static final String TABLE_NAME = "loglist_data";
    public static final String COL1 = "DATE";
    public static final String COL2 = "BMI";
    //public static final String COL3 = "BMR";
    //public static final String COL4 = "WHTR";
    //public static final String COL5 = "TDEE";

    //Add more columns here

    public DatabaseHelperActivity(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = ("CREATE TABLE" + TABLE_NAME + " (DATE INTEGER PRIMARY KEY," + COL2 + "TEXT)");
        db.execSQL(createTable);
    }

 @Override
 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

 }

    public boolean addData(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(COL1, date);
        contentValues.put(COL2, item);
        //contentValues.put(COL3, bmr);
        //contentValues.put(COL4, whtr);
        //contentValues.put(COL5, tdee);
        Log.d(LOG, "addData: Adding" + item + "to" + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        }
        else {
            return true;

        }
    }

    //returns all the data from the database
    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemDate (String bmi) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + "FROM" + TABLE_NAME +
                " WHERE " + COL2 + " = '" + bmi + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public void updateBMI(String newBMI, int date, String oldBMI) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newBMI + "' WHERE " + COL1 + " = '" + date + "'" +
                " AND " + COL2 + " = '" + oldBMI + "'";
        Log.d(LOG, "updateBMI: query: " + query);
        Log.d(LOG, "updateBMI: Setting BMI to " + newBMI);
        db.execSQL(query);
    }
    public void deleteBMI(int date, String bmi){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + date + "'" +
                " AND " + COL2 + " = '" + bmi + "'";
        Log.d(LOG, "deleteBMI: query: " + query);
        Log.d(LOG, "deleteBMI: Deleting " + bmi + " from database.");
        db.execSQL(query);


    }

}
