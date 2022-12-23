package com.example.idnpproyectogrupo07.classes;

import android.graphics.Bitmap;

public class  Plastic {

    int id_plastic;
    String date_plastic;
    int amount_plastic;
    Bitmap plastic_picture;
    int id_code_column;
    int id_type_column;
    int id_user_column;


    public Plastic(String date_plastic, int amount_plastic, Bitmap plastic_picture, int id_code_column, int id_type_column, int id_user_column) {
        this.date_plastic = date_plastic;
        this.amount_plastic = amount_plastic;
        this.plastic_picture = plastic_picture;
        this.id_code_column = id_code_column;
        this.id_type_column = id_type_column;
        this.id_user_column = id_user_column;
    }
    public Plastic(){
    }

    public int getId_plastic() {
        return id_plastic;
    }

    public void setId_plastic(int id_plastic) {
        this.id_plastic = id_plastic;
    }

    public String getDate_plastic() {
        return date_plastic;
    }

    public void setDate_plastic(String date_plastic) {
        this.date_plastic = date_plastic;
    }

    public int getAmount_plastic() {
        return amount_plastic;
    }

    public void setAmount_plastic(int amount_plastic) {
        this.amount_plastic = amount_plastic;
    }

    public Bitmap getPlastic_picture() {
        return plastic_picture;
    }

    public void setPlastic_picture(Bitmap plastic_picture) {
        this.plastic_picture = plastic_picture;
    }

    public int getId_code_column() {
        return id_code_column;
    }

    public void setId_code_column(int id_code_column) {
        this.id_code_column = id_code_column;
    }

    public int getId_type_column() {
        return id_type_column;
    }

    public void setId_type_column(int id_type_column) {
        this.id_type_column = id_type_column;
    }

    public int getId_user_column() {
        return id_user_column;
    }

    public void setId_user_column(int id_user_column) {
        this.id_user_column = id_user_column;
    }

    @Override
    public String toString() {
        return "Plastic{" +
                "id_plastic=" + id_plastic +
                ", date_plastic='" + date_plastic + '\'' +
                ", amount_plastic=" + amount_plastic +
                ", plastic_picture=" + plastic_picture +
                ", id_code_column=" + id_code_column +
                ", id_type_column=" + id_type_column +
                ", id_user_column=" + id_user_column +
                '}';
    }
}
