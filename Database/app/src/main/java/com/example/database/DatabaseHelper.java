package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "My_Company_Lab";

    static final String USER_ID = "ID";
    static final String USER_NAME = "user_name";
    static final String USER_PASSWORD = "password";

    static final int DATABASE_VERSION = 1;
    static final String DATABASE_TABLE = "USERS";

    private static final String CREATE_DB_QUERY = "CREATE TABLE " + "If NOT EXISTS "+DATABASE_TABLE+"("+USER_NAME+"TEXT NOT NULL,"+USER_PASSWORD+"TEXT NOT NULL)";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(CREATE_DB_QUERY);
            Log.i("Database table created: ", DATABASE_TABLE);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
        onCreate(db);

    }
}