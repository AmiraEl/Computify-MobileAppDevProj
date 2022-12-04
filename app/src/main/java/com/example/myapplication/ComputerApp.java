package com.example.myapplication;

import android.app.Application;
import android.content.Intent;

public class ComputerApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        startService(new Intent(this, ComputersService.class));
    }
}
