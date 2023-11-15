package com.example.thebloomroom;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "TheBloomRoomDB";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "users";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE users (user_id INTEGER PRIMARY KEY AUTOINCREMENT, first_name TEXT, last_name TEXT," +
                "email TEXT, phone_num INTEGER, address TEXT, password TEXT);";
        db.execSQL(createTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }
}
