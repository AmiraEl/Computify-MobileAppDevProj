package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Profiles {

    private String username;
    private String email;
    private String UID;


    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getUsername(){return username;}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail(){return email;}

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString(){
        return "Profiles{" +
                "username= '" + username + '\'' +
                ", email= " + email
                +'}';
    }

    public Profiles(){}
    public Profiles(String username, String email){
        this.username = username;
        this.email = email;
    }



}
