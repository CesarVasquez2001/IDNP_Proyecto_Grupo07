package com.example.idnpproyectogrupo07.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.nio.ByteBuffer;

public class DBHelper extends SQLiteOpenHelper implements Serializable {


    protected Context context;
    protected SQLiteDatabase db;


    private static final int DATABASE_VERSION = 3;
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
                "plastic_picture BLOB NOT NULL," +
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
    public static Bitmap byteToBitmap(byte[] b) {
        return (b == null || b.length == 0) ? null : BitmapFactory
                .decodeByteArray(b, 0, b.length);
    }

    public byte[] compressImage(byte[] imageToCompress){
        byte[] compressImage = imageToCompress;
        while (compressImage.length>500000){
            Bitmap bitmap = BitmapFactory.decodeByteArray(compressImage,0,compressImage.length);
            Bitmap resize = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * 0.8), (int) (bitmap.getHeight() * 0.8), true);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            resize.compress(Bitmap.CompressFormat.JPEG,100,stream);
            compressImage = stream.toByteArray();
        }
        return compressImage;

    }
    public byte[] getBytes(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

}
