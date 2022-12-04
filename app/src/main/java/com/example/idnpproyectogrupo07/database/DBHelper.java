package com.example.idnpproyectogrupo07.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "smart_city.db";
    private static final String TABLE_USER = "t_user";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USER + "("+
            "id_user INTEGER PRIMARY KEY AUTOINCREMENT," +
            "fullname TEXT NOT NULL," +
            "email TEXT NOT NULL," +
            "password TEXT NOT NULL," +
            "gender TEXT NOT NULL," +
            "date_of_birth TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_USER);
        //onCreater(sqliteDatabase)
    }

}
