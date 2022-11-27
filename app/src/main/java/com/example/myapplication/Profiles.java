package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Profiles {
    private String username;

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
