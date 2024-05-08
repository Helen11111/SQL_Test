package com.example.sql_test.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;

    private static  final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_STUDENT = "_student";

    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_MOBILE = "mobile";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Query_Table = " CREATE TABLE " + TABLE_STUDENT + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT, " + KEY_EMAIL + " TEXT, "
                + KEY_MOBILE + " TEXT);";
        db.execSQL(Query_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);
    }

    public long insertStudent(String name, String email, String phone){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_EMAIL, email);
        values.put(KEY_MOBILE, phone);
        return db.insert(TABLE_STUDENT, null, values);
    }

    public String getData(){
        db = this.getReadableDatabase();
        String[] columns = new String[] {KEY_ID, KEY_NAME, KEY_EMAIL, KEY_MOBILE};
        Cursor cursor = db.query(TABLE_STUDENT, columns, null, null, null, null, null);

        int iD = cursor.getColumnIndex(KEY_ID);
        int iName = cursor.getColumnIndex(KEY_NAME);
        int iEmail = cursor.getColumnIndex(KEY_EMAIL);
        int iPhone = cursor.getColumnIndex(KEY_MOBILE);
        String result = "";

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            result = result + "ID: " + cursor.getString(iD) + "\n" +
                    "Name: " + cursor.getString(iName) + "\n" +
                    "Email: " + cursor.getString(iEmail) + "\n" +
                    "Phone: " + cursor.getString(iPhone) + "\n\n";
        }
        db.close();
        return result;
    }

    public void deleteStudent(long l){
        db = this.getWritableDatabase();
        db.delete(TABLE_STUDENT, KEY_ID + "=" + l, null);
    }

    public void updateStudent(long l, String name, String email, String phone){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_EMAIL, email);
        values.put(KEY_MOBILE, phone);
        db.update(TABLE_STUDENT, values, KEY_ID + "=" + l, null);
        db.close();
    }

    public String getName(long l) {
        db = this.getReadableDatabase();
        String[] columns = new String[] {KEY_ID, KEY_NAME, KEY_EMAIL, KEY_MOBILE};
        Cursor cursor = db.query(TABLE_STUDENT, columns, KEY_ID + "=" + l, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
            String name = cursor.getString(1);
            return name;
        }
        return null;
    }

    public String getEmail(long l) {
        db = this.getReadableDatabase();
        String[] columns = new String[] {KEY_ID, KEY_NAME, KEY_EMAIL, KEY_MOBILE};
        Cursor cursor = db.query(TABLE_STUDENT, columns, KEY_ID + "=" + l, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
            String email = cursor.getString(2);
            return email;
        }
        return null;
    }

    public String getMobile(long l) {
        db = this.getReadableDatabase();
        String[] columns = new String[] {KEY_ID, KEY_NAME, KEY_EMAIL, KEY_MOBILE};
        Cursor cursor = db.query(TABLE_STUDENT, columns, KEY_ID + "=" + l, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
            String phone = cursor.getString(3);
            return phone;
        }
        return null;
    }


}
