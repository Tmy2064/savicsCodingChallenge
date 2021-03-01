package com.tmylg.savicscodingchallenge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "records.sqlite";
    public static final String TABLE_NAME = "records_table";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAMES";
    public static final String COL_3 = "SEX";
    public static final String COL_4 = "AGE";
    public static final String COL_5 = "COUNTRY";
    public static final String COL_6 = "DIABETSTATUS";

    public DataBaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAMES TEXT,SEX TEXT,AGE INTEGER,COUNTRY TEXT,DIABETSTATUS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    public boolean insertData(String names, String sex, String age, String country, String diabetstatus){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, names);
        contentValues.put(COL_3, sex);
        contentValues.put(COL_4, age);
        contentValues.put(COL_5, country);
        contentValues.put(COL_6, diabetstatus);

        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();

        // Checking inserted data in database
        if (result==-1){
            return false;
        } else {
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from " + TABLE_NAME, null);

        return res;
    }

    public Cursor getFilteredData(String inputText, String filtercolum) throws SQLException {
        Cursor row = null;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM "+DataBaseHelper.TABLE_NAME;
        if (inputText == null || inputText.length() == 0){
            row = db.rawQuery(query, null);
        } else {
            query = "SELECT * FROM "+DataBaseHelper.TABLE_NAME+" WHERE "+filtercolum+" like '%"+inputText+"%'";
            row = db.rawQuery(query, null);
        }
        if (row != null){
            row.moveToFirst();
        }
        return row;
    }

}
