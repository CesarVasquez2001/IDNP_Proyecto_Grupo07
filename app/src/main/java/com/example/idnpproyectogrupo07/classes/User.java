package com.example.idnpproyectogrupo07.classes;

import android.graphics.Bitmap;

import java.io.Serializable;

public class User {

    private int id_user;
    private String fullname;
    private String email;
    private String password;
    private String gender;
    private String date_of_birth;
    private Bitmap profile_picture;


    public User(String fullname, String email, String password, String gender, String date_of_Birth,Bitmap profile_picture) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.date_of_birth = date_of_Birth;
        this.profile_picture=profile_picture;
    }

    public User() {
    }
    public long getId_user() {
        return id_user;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public Bitmap getProfile_picture() {
        return profile_picture;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setProfile_picture(Bitmap profile_picture) {
        this.profile_picture = profile_picture;
    }

    @Override
    public String toString() {
        return "User{" +
                "id_user=" + id_user +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", date_of_birth='" + date_of_birth + '\'' +
                '}';
    }


}
