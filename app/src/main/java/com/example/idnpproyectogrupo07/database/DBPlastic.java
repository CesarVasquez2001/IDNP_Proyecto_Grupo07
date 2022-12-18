package com.example.idnpproyectogrupo07.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;



public class DBPlastic extends DBHelper{


    public DBPlastic(@Nullable Context context) {
        super(context);
    }

    public DBPlastic OpenDb() {
        DBHelper dbCon = new DBHelper(context);
        db = dbCon.getWritableDatabase();

        return this;
    }
}