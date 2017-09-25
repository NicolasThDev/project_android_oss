package com.example.nico.ossproject.controller;

import android.app.Application;
import android.content.Context;

import com.example.nico.ossproject.bean.ColorProject;
import com.google.gson.Gson;

public class MyApplication extends Application {

    public static Gson gson;




    private static MyApplication instance;

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
        ColorProject.init();
        gson = new Gson();
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }
}
