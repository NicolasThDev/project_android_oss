package com.example.nico.ossproject.bean.beanUtils;

import android.content.SharedPreferences;

import com.example.nico.ossproject.bean.beanServer.SiteUser;
import com.example.nico.ossproject.controller.MyApplication;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferenceUtils {

    private static SharedPreferences getSharedPreference() {
        return MyApplication.getContext().getSharedPreferences("Register", MODE_PRIVATE);
    }

    // ---------------------------------
    //  SAVE USER
    // ---------------------------------
    private static final String USER_KEY = "USER_KEY";

    public static SiteUser getUser() {
        String json = getSharedPreference().getString(USER_KEY, "");
        return MyApplication.gson.fromJson(json, SiteUser.class);
    }

    public static void saveUser(SiteUser siteUser) {
        String jsonUser = MyApplication.gson.toJson(siteUser);
        getSharedPreference().edit().putString(USER_KEY, jsonUser).apply();
    }

    public static void deleteUser(){
        getSharedPreference().edit().remove(USER_KEY).apply();
    }
}
