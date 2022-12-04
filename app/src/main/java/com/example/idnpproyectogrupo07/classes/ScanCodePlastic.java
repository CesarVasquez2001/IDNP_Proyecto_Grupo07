package com.example.idnpproyectogrupo07.classes;

public class ScanCodePlastic {
    private String nombre;
    private String placeholder;
    private int imagenId;

    public ScanCodePlastic(){}

    public ScanCodePlastic(String nombre, String placeholder, int imagenId) {
        this.nombre = nombre;
        this.placeholder = placeholder;
        this.imagenId = imagenId;
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
