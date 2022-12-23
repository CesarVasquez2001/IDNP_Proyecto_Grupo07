package com.example.idnpproyectogrupo07.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.idnpproyectogrupo07.R;
import com.example.idnpproyectogrupo07.classes.EducationItems;


import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class DBEducation extends DBHelper {

    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInBytes;

    public DBEducation(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public DBEducation OpenDb() {
        DBHelper dbCon = new DBHelper(context);
        db = dbCon.getWritableDatabase();
        initData();

        return this;
    }

    public void initData() {
        if (getSize() == 0) {
            insertEducation(new EducationItems("PET Plastic", "e.g. Clear bottles, pots, trays and punnets", R.drawable.code1));
            insertEducation(new EducationItems("HDPE Plastic", "e.g. Milk jugs, shampoo, chemical and detergent bottles ", R.drawable.code2));
            insertEducation(new EducationItems("PVC Plastic", "e.g. Cosmetic containers and household fittings ", R.drawable.code3));
            insertEducation(new EducationItems("LDPE Plastic", "e.g. Flexible lids, plastic drycleaner covers, clining film ", R.drawable.code4));
            insertEducation(new EducationItems("PP Plastic", "e.g. Single pots, tubs, ready-meal trays and rigid caps ", R.drawable.code5));
            insertEducation(new EducationItems("PS Plastic", "e.g. Multipack yogurt snap pots and video games cases ", R.drawable.code6));
            insertEducation(new EducationItems("Other Plastic", "e.g. Nets, PLA, clear CD cases and acrylic ", R.drawable.code7));
        }
    }

    public int getSize() {
        int size = -1;
        try {
            Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM t_education", null);
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


    public long insertEducation(EducationItems education) {
        long id_education = 0;

        try {

            ContentValues values = new ContentValues();

            byte[] imageByte = bigIntToByteArray(education.getImagenId());

            values.put("name_education", education.getNombre());
            values.put("description_education", education.getPlaceholder());
            // BLOB
            values.put("image_education", imageByte);

            id_education = db.insert("t_education", null, values);

        } catch (Exception exception) {
            Toast.makeText(context, exception.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return id_education;
    }

    public EducationItems getCodeEducation(int id_education) {
        EducationItems education = new EducationItems();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM t_education WHERE id_education=?", new String[]{String.valueOf(id_education)});
            if (cursor.moveToFirst()) {
                do {
                    education.setId_code(cursor.getInt(0));
                    education.setNombre(cursor.getString(1));
                    education.setPlaceholder(cursor.getString(2));
                    byte[] imageByte = cursor.getBlob(3);
                    education.setImagenId(convertByteArrayToInt(imageByte));

                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return education;
    }

    public ArrayList<EducationItems> getAllCode() {
        ArrayList<EducationItems> codeEducation = new ArrayList<EducationItems>();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM t_education", null);
            if (cursor.moveToFirst()) {
                do {
                    EducationItems education = new EducationItems();
                    education.setId_code(cursor.getInt(0));
                    education.setNombre(cursor.getString(1));
                    education.setPlaceholder(cursor.getString(2));
                    byte[] imageByte = cursor.getBlob(3);
                    education.setImagenId(convertByteArrayToInt(imageByte));

                    codeEducation.add(education);
                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return codeEducation;
    }

}