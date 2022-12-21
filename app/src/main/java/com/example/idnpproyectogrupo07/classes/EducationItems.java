package com.example.idnpproyectogrupo07.classes;

public class EducationItems {
    private int id_code;
    private String nombre;
    private String placeholder;
    private int imagenId;

    public EducationItems(){}

    public EducationItems(String nombre, String placeholder, int imagenId) {
        this.nombre = nombre;
        this.placeholder = placeholder;
        this.imagenId = imagenId;
    }

    public int getId_code() {
        return id_code;
    }

    public void setId_code(int id_code) {
        this.id_code = id_code;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public int getImagenId() {
        return imagenId;
    }

    public void setImagenId(int imagenId) {
        this.imagenId = imagenId;
    }
}
