package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDataException;

public class DataBaseManager {
    private DatabaseHelper dbheleper;
    private Context context;
    private SQLiteDatabase database;



    public DataBaseManager(Context ctx){context = ctx;}


    public DataBaseManager open() throws SQLDataException{
        dbheleper=new DatabaseHelper(context);
        database=dbheleper.getWritableDatabase();
        return this;
    }

    public void close(){dbheleper.close();}


    public void insert(String username, String password){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.USER_NAME,username);
        contentValues.put(DatabaseHelper.USER_PASSWORD,password);
        database.insert(DatabaseHelper.DATABASE_TABLE,null,contentValues);
    }

    public Cursor fetch() {
        String[] columns = new String[]{DatabaseHelper.USER_ID,
                DatabaseHelper.USER_NAME, DatabaseHelper.USER_PASSWORD};
        Cursor cursor = database.query(DatabaseHelper.DATABASE_TABLE,columns,
                null,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;

    }

    public int update(long _id,String username,String password) {}
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.USER_NAME,username);
        contentValues.put(DatabaseHelper.USER_PASSWORD,password);
}