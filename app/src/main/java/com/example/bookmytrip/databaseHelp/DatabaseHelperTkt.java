package com.example.bookmytrip.databaseHelp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperTkt extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "tkttest.db";
    public static final String TABLE_NAME = "tkttkttest";
    public static final String COL_0 = "tktno";
    public static final String COL_1 = "src";
    public static final String COL_2 = "dest";
    public static final String COL_3 = "spinner";
    public static final String COL_4 = "radio";
    public static final String COL_5 = "tktamt";
    public static final String COL_6 = "datetime";
    public static final String COL_7 = "email";

    public DatabaseHelperTkt(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (tktno TEXT, src TEXT, dest TEXT, spinner TEXT, radio TEXT, tktamt TEXT, datetime TEXT, email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String tktno,String src,String dest,String spinner,String radio,String tktamt,String datetime, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_0,tktno);
        contentValues.put(COL_1,src);
        contentValues.put(COL_2,dest);
        contentValues.put(COL_3,spinner);
        contentValues.put(COL_4,radio);
        contentValues.put(COL_5,tktamt);
        contentValues.put(COL_6,datetime);
        contentValues.put(COL_7,email);

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

//    public Cursor getAllData() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
//        return res;
//    }


}
