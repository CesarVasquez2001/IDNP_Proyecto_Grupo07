package com.example.idnpproyectogrupo07.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.idnpproyectogrupo07.classes.User;

import java.io.ByteArrayOutputStream;

public class DBUser extends DBHelper {


    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInBytes;

    public DBUser(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public DBUser OpenDb() {
        DBHelper dbCon = new DBHelper(context);
        db = dbCon.getWritableDatabase();
        return this;
    }


    public boolean updateUser(User user) {
        boolean result = false;
        try {
            ContentValues values = new ContentValues();

            Bitmap imageToStoreBitmap = user.getProfile_picture();
            imageInBytes = compressImage(getBytes(imageToStoreBitmap));

            values.put("fullname", user.getFullname());
            values.put("email", user.getEmail());
            values.put("gender", user.getGender());
            values.put("date_of_birth", user.getDate_of_birth());
            values.put("profile_picture", imageInBytes);

            db.update("t_user",values,"id_user = " + user.getId_user(),null);
            result=true;
        } catch (Exception exception) {
            Toast.makeText(context, "UPDATE PROFILE"+ exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    public boolean updateUserPassword(User user) {
        boolean result = false;
        try {
            ContentValues values = new ContentValues();

            values.put("password", user.getPassword());

            db.update("t_user",values,"id_user = " + user.getId_user(),null);
            result=true;
        } catch (Exception exception) {
            Toast.makeText(context, exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return result;
    }
    public long insertUser(User user) {
        long id_user = 0;

        try {

            ContentValues values = new ContentValues();

            Bitmap imageToStoreBitmap = user.getProfile_picture();
            imageInBytes = compressImage(getBytes(imageToStoreBitmap));

            values.put("fullname", user.getFullname());
            values.put("email", user.getEmail());
            values.put("password", user.getPassword());
            values.put("gender", user.getGender());
            values.put("date_of_birth", user.getDate_of_birth());
            // BLOB
            values.put("profile_picture", imageInBytes);

            id_user = db.insert("t_user", null, values);

        } catch (Exception exception) {
            Toast.makeText(context, exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return id_user;
    }

    public User getUser(String email, String password) {
        User user = new User();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM t_user WHERE email=? AND password=?", new String[]{email, password});
            if (cursor.moveToFirst()) {
                do {
                    user.setId_user(cursor.getInt(0));
                    user.setFullname(cursor.getString(1));
                    user.setEmail(cursor.getString(2));
                    user.setPassword(cursor.getString(3));
                    user.setGender(cursor.getString(4));
                    user.setDate_of_birth(cursor.getString(5));

                    byte[] imageByte = cursor.getBlob(6);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                    user.setProfile_picture(bitmap);

                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return user;
    }

    public User getUser(int id_user) {
        User user = new User();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM t_user WHERE id_user=? ", new String[]{String.valueOf(id_user)});
            if (cursor.moveToFirst()) {
                do {
                    user.setId_user(cursor.getInt(0));
                    user.setFullname(cursor.getString(1));
                    user.setEmail(cursor.getString(2));
                    user.setPassword(cursor.getString(3));
                    user.setGender(cursor.getString(4));
                    user.setDate_of_birth(cursor.getString(5));

                    byte[] imageByte = cursor.getBlob(6);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                    user.setProfile_picture(bitmap);

                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return user;
    }
    public boolean check(String email,String emailuser) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT email FROM t_user WHERE email=?", new String[]{email});
        if (cursor.getCount() ==1 && email.equals(emailuser)) {
            return false;
        }else if (cursor.getCount() ==0)
            return false;
        else
            return true;
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

    public void savePreference(User user) {

        SharedPreferences sharedPreferences = this.context.getSharedPreferences("MySharedPref", this.context.MODE_PRIVATE);

        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        myEdit.putInt("id_user", (int) user.getId_user());
        myEdit.putString("email", user.getEmail());
        myEdit.putString("password", user.getPassword());

        myEdit.commit();
    }

    public User getPreference() {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences("MySharedPref", this.context.MODE_PRIVATE);
        int id_user = sharedPreferences.getInt("id_user", 0);
        User user = getUser(id_user);
        return user;
    }
}
