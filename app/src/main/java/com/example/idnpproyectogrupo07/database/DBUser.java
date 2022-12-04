package com.example.idnpproyectogrupo07.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBUser extends DBHelper {

    Context context;
    private SQLiteDatabase db;

    public DBUser(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public DBUser OpenDb() {
        DBHelper dbCon = new DBHelper(context);

        db = dbCon.getWritableDatabase();
        return this;
    }

    public long insertUser(User user) {
        long id_user = 0;

        try {

            ContentValues values = new ContentValues();
            values.put("fullname", user.getFullname());
            values.put("email", user.getFullname());
            values.put("password", user.getPassword());
            values.put("gender", user.getGender());
            values.put("date_of_birth", user.getDateOfBirth());

            id_user = db.insert("t_user", null, values);

        } catch (Exception exception) {
            Toast.makeText(context, exception.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return id_user;
    }

    public User loginUser(String email,String password){
        User user= new User();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM t_user WHERE email=? AND password=?", new String[]{email, password});
            if (cursor.moveToFirst()){
                do {
                    user.setId_user(cursor.getInt(0));
                    user.setFullname(cursor.getString(1));
                    user.setEmail(cursor.getString(2));
                    user.setPassword(cursor.getString(3));
                    user.setGender(cursor.getString(4));
                    user.setDateOfBirth(cursor.getString(5));

                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return user;
    }

    public boolean checkEmail(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM t_user WHERE email=?", new String[]{email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM t_user WHERE email=? AND password=?", new String[]{email, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

}
