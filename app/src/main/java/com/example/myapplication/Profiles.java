package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Profiles {
    public void setUsername(String username) {
        this.username = username;
    }

    private String username;

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    private String UID;


    @Override
    public String toString(){
        return "Profiles{" +
                ", username= " + username + '}';
    }

    public Profiles(){}
    public Profiles(String username){
        this.username = username;
    }


    public String getUsername(){return username;}
}
