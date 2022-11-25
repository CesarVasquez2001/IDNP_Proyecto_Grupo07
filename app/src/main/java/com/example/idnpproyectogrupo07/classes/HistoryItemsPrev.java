package com.example.idnpproyectogrupo07.classes;

/* TODO: DELETE*/

public class HistoryItemsPrev {

    public int image;
    public String name;
    public String description;
    public String status;

    public HistoryItemsPrev(int image, String name, String description, String status) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
