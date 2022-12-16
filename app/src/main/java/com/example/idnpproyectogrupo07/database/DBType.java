package com.example.idnpproyectogrupo07.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.idnpproyectogrupo07.R;
import com.example.idnpproyectogrupo07.classes.ScanItemsPlastic;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class DBType extends DBHelper {

    Context context;
    private SQLiteDatabase db;

    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInBytes;

    public DBType(@Nullable Context context) {
        super(context);
        this.context = context;
    }


    public DBType OpenDb() {
        DBHelper dbCon = new DBHelper(context);
        db = dbCon.getWritableDatabase();
        initData();


        return this;
    }

    public void initData() {
        if (getSize() == 0) {
            insertType(new ScanItemsPlastic("Botella de Plastico", "e.g. agua, jugos, gaseosas, etc", R.drawable.plastic));
            insertType(new ScanItemsPlastic("Envoltorio de Plastico", "e.g. dulces, caramelos, goma de mascar, etc ", R.drawable.wrapper));
            insertType(new ScanItemsPlastic("Bolsa de Plastico", "e.g. supermercado, ......, ...., etc ", R.drawable.bag));
            insertType(new ScanItemsPlastic("Pote de Plastico", "e.g. mantequilla, helados, nutela, etc ", R.drawable.tub));
            insertType(new ScanItemsPlastic("Tubo de Plastico", "e.g. crema dental, crema de manos, etc ", R.drawable.pastadental));
            insertType(new ScanItemsPlastic("Paquete de Blisters", "e.g. pastillas, juguetes , usbs, etc ", R.drawable.antihistamines));
            insertType(new ScanItemsPlastic("Funda de Plastico", "e.g. comida de mascota, popcorn microondas, etc ", R.drawable.pouch));
            insertType(new ScanItemsPlastic("Taper de Plastico", "e.g. fruta, ensaladas, comidas, etc ", R.drawable.cramshell));
            insertType(new ScanItemsPlastic("Galonera de Plastico", "e.g. yogurt, limpiador, leche, etc ", R.drawable.galonera));
            insertType(new ScanItemsPlastic("Botella de Spray", "e.g. desinfectante, limpiador de superficies, etc ", R.drawable.spray));
            insertType(new ScanItemsPlastic("Surtidor de Plastico", "e.g. alcohol en gel, jabon, lociones, etc ", R.drawable.pump));
            insertType(new ScanItemsPlastic("Bote de Plastico", "e.g. suplementos, nutella, etc ", R.drawable.potegrande));
            insertType(new ScanItemsPlastic("Aplicador de Plastico", "e.g. desodorante, balsamo labial, etc ", R.drawable.deodorant));
            insertType(new ScanItemsPlastic("Caja de Plastico", "e.g. videojuegos, DVD, etc ", R.drawable.game));
            insertType(new ScanItemsPlastic("Plastico Desconocido", "e.g. Si no conoces el plastico pero puedes dar mas detalles del mismo, etc ", R.drawable.unknown));
        }
    }

    public int getSize() {
        int size = -1;
        try {
            Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM t_type", null);
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

    public long insertType(ScanItemsPlastic type) {
        long id_type = 0;

        try {

            ContentValues values = new ContentValues();

            byte[] imageByte = bigIntToByteArray(type.getImagenid());

            values.put("name_type", type.getNombre());
            values.put("description_type", type.getPlaceholder());
            // BLOB
            values.put("image_type", imageByte);

            id_type = db.insert("t_type", null, values);

        } catch (Exception exception) {
            Toast.makeText(context, exception.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return id_type;
    }


    public ScanItemsPlastic getType(int id_type) {
        ScanItemsPlastic type = new ScanItemsPlastic();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM t_type WHERE id_type=?", new String[]{String.valueOf(id_type)});
            if (cursor.moveToFirst()) {
                do {
                    type.setId_type(cursor.getInt(0));
                    type.setNombre(cursor.getString(1));
                    type.setPlaceholder(cursor.getString(2));
                    byte[] imageByte = cursor.getBlob(3);
                    type.setImagenId(convertByteArrayToInt(imageByte));

                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return type;
    }

    public ArrayList<ScanItemsPlastic> getAllType() {
        ArrayList<ScanItemsPlastic> scanItemsPlastics = new ArrayList<ScanItemsPlastic>();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM t_type", null);
            if (cursor.moveToFirst()) {
                do {
                    ScanItemsPlastic type = new ScanItemsPlastic();
                    type.setId_type(cursor.getInt(0));
                    type.setNombre(cursor.getString(1));
                    type.setPlaceholder(cursor.getString(2));
                    byte[] imageByte = cursor.getBlob(3);
                    type.setImagenId(convertByteArrayToInt(imageByte));

                    scanItemsPlastics.add(type);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return scanItemsPlastics;
    }



}
