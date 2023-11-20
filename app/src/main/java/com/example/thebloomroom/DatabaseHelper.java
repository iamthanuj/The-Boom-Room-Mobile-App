package com.example.thebloomroom;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "TheBloomRoomDB";
    private static final int DB_VERSION = 2;


    private static final String CREATE_USER_TABLE = "CREATE TABLE users (" +
            "user_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "first_name TEXT," +
            "last_name TEXT," +
            "email TEXT," +
            "phone_number TEXT," +
            "address TEXT," +
            "password TEXT" +
            ");";


    private static final String CREATE_FLOWERS_TABLE = "CREATE TABLE flowers (" +
            "flower_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "flower_name TEXT," +
            "flower_color TEXT," +
            "description TEXT," +
            "price TEXT" +
            ");";




    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_FLOWERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS flowers");
        onCreate(db);
    }
}
