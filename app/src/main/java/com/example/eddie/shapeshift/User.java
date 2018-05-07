package com.example.eddie.shapeshift;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity (tableName = "log")
class User {
    @PrimaryKey
    public int id;

    @ColumnInfo (name = "Date")
    public Long Date;

    @ColumnInfo (name = "BMI")
    public int bmi;

    @ColumnInfo (name = "BMR")
    public int bmr;

    @ColumnInfo (name = "whtr")
    public int whtr;
    @ColumnInfo (name = "tdee")
    public int tdee;


    public int getID() {
        return id;

    }

    public void setID(long date){

        this.id = id;

    }
    public long getDate() {
        return Date;

    }

    public void setDate(long date){

        this.Date = date;
    }

    public int getBMI() {

        return bmi;
    }

    public void setBMI (int bmi ) {

        this.bmi = bmi;
    }

    public int getBMR() {

        return bmr;
    }

    public void setBMR (int bmr ) {

        this.bmr = bmr;
    }
    public int getwhtr() {

        return whtr;
    }

    public void setWhtr (int whtr ) {

        this.whtr = whtr;
    }
    public int getTdee() {

        return tdee;
    }

    public void setTdee (int tdee ) {

        this.tdee = tdee;
    }
}
