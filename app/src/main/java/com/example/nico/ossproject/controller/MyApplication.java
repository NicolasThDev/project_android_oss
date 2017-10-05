package com.example.nico.ossproject.controller;

import android.app.Application;
import android.content.Context;

import com.example.nico.ossproject.bean.ColorProject;
import com.google.gson.Gson;

public class MyApplication extends Application {

    //-------------------
    //  ATTRIBUTES
    //-------------------
    public static Gson gson;
    private static MyApplication instance;

    //-------------------
    //  OVERRIDES
    //-------------------
    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
        ColorProject.init(); // INITIALIZE COLOR FOR THE PROJECT
        gson = new Gson(); // INIT GSON
    }

    //-------------------
    //  METHODS
    //-------------------
    public static MyApplication getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }
}
