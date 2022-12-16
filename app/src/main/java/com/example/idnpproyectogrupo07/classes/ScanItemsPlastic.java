package com.example.idnpproyectogrupo07.classes;

public class ScanItemsPlastic {

    private int id_type;
    private String nombre;
    private String placeholder;
    private int imagenid;

    public ScanItemsPlastic(){}

    public ScanItemsPlastic(String nombre, String placeholder, int imagenid) {
        this.nombre = nombre;
        this.placeholder = placeholder;
        this.imagenid = imagenid;
    }
    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
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

    public int getImagenid() {
        return imagenid;
    }

    public void setImagenId(int imagenid) {
        this.imagenid = imagenid;
    }
}
