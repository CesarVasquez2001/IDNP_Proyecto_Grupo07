package com.example.idnpproyectogrupo07.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.math.BigInteger;
import java.nio.ByteBuffer;

public class DBHelper extends SQLiteOpenHelper implements Serializable {


    protected Context context;
    protected SQLiteDatabase db;


    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "smart_city.db";

    private static final String TABLE_USER = "t_user";
    private static final String TABLE_PLASTIC = "t_plastic";
    private static final String TABLE_CODE = "t_code";
    private static final String TABLE_TYPE = "t_type";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CODE + "(" +
                "id_code INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name_code TEXT NOT NULL," +
                "description_code TEXT NOT NULL," +
                "image_code BLOB NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_TYPE + "(" +
                "id_type INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name_type TEXT NOT NULL," +
                "description_type TEXT NOT NULL," +
                "image_type BLOB NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USER + "(" +
                "id_user INTEGER PRIMARY KEY AUTOINCREMENT," +
                "fullname TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "password TEXT NOT NULL," +
                "gender TEXT NOT NULL," +
                "date_of_birth TEXT NOT NULL," +
                "profile_picture BLOB NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PLASTIC + "(" +
                "id_plastic INTEGER PRIMARY KEY AUTOINCREMENT," +
                "date_plastic TEXT NOT NULL," +
                "amount_plastic INTEGER NOT NULL," +
                "id_code_column INTEGER NOT NULL references " + TABLE_CODE + "( id_code )," +
                "id_type_column INTEGER NOT NULL references " + TABLE_TYPE + "( id_type )," +
                "id_user_column INTEGER NOT NULL references " + TABLE_USER + "( id_user )" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CODE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TYPE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PLASTIC);
        onCreate(sqLiteDatabase);
    }


    public byte[] bigIntToByteArray(int i) {
        BigInteger bigInt = BigInteger.valueOf(i);
        return bigInt.toByteArray();
    }

    public static int convertByteArrayToInt(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getInt();
    }

}
