package com.example.idnpproyectogrupo07.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.idnpproyectogrupo07.classes.Plastic;
import com.example.idnpproyectogrupo07.classes.ScanCodePlastic;
import com.example.idnpproyectogrupo07.classes.User;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class DBPlastic extends DBHelper{

    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInBytes;

    public DBPlastic(@Nullable Context context) {
        super(context);
        this.context = context;

    }

    public DBPlastic OpenDb() {
        DBHelper dbCon = new DBHelper(context);
        db = dbCon.getWritableDatabase();
        return this;
    }

    public long insertPlastic(Plastic plastic) {
        long id_plastic = 0;

        try {

            ContentValues values = new ContentValues();

            Bitmap imageToStoreBitmap = plastic.getPlastic_picture();
            imageInBytes = compressImage(getBytes(imageToStoreBitmap));

            values.put("date_plastic", plastic.getDate_plastic());
            values.put("amount_plastic", plastic.getAmount_plastic());
            values.put("plastic_picture", imageInBytes);
            values.put("id_code_column", plastic.getId_code_column());
            values.put("id_type_column", plastic.getId_type_column());
            values.put("id_user_column", plastic.getId_user_column());

            id_plastic = db.insert("t_plastic", null, values);

        } catch (Exception exception) {
            Toast.makeText(context, exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return id_plastic;
    }

    public ArrayList<Plastic> getAllPlastic(int id_user) {
        ArrayList<Plastic> plastics = new ArrayList<Plastic>();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM t_plastic WHERE id_user_column=?", new String[]{String.valueOf(id_user)});
            if (cursor.moveToFirst()) {
                do {
                    Plastic plastic = new Plastic();

                    plastic.setId_plastic(cursor.getInt(0));
                    plastic.setDate_plastic(cursor.getString(1));
                    plastic.setAmount_plastic(cursor.getInt(2));

                    byte[] imageByte = cursor.getBlob(3);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                    plastic.setPlastic_picture(bitmap);

                    plastic.setId_code_column(cursor.getInt(4));
                    plastic.setId_type_column(cursor.getInt(5));
                    plastic.setId_user_column(cursor.getInt(6));

                    plastics.add(plastic);
                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return plastics;
    }

    public Plastic getPlastic(int id_plastic) {
        Plastic plastic = new Plastic();
         try {
            Cursor cursor = db.rawQuery("SELECT * FROM t_plastic WHERE id_plastic=?", new String[]{String.valueOf(id_plastic)});
            if (cursor.moveToFirst()) {
                do {

                    plastic.setId_plastic(cursor.getInt(0));
                    plastic.setDate_plastic(cursor.getString(1));
                    plastic.setAmount_plastic(cursor.getInt(2));

                    byte[] imageByte = cursor.getBlob(3);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                    plastic.setPlastic_picture(bitmap);

                    plastic.setId_code_column(cursor.getInt(4));
                    plastic.setId_type_column(cursor.getInt(5));
                    plastic.setId_user_column(cursor.getInt(6));

                 } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return plastic;
    }

    public int getSize(int id_user) {
        int size = -1;
        try {
            Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM t_plastic WHERE id_user_column=?", new String[]{String.valueOf(id_user)});
            if (cursor.moveToFirst()) {
                do {
                    size = cursor.getInt(0);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return size;
    }
}