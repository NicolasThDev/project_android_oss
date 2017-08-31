package com.example.nico.ossproject.controller;

import android.app.Application;

import com.example.nico.ossproject.bean.ColorProject;

public class MyApplication extends Application {






    private static MyApplication instance;

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
        ColorProject.init();
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
