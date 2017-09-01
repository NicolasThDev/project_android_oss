package com.example.nico.ossproject.bean.beanUtils;

import com.example.nico.ossproject.bean.beanServer.Spot;
import com.example.nico.ossproject.controller.MyApplication;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class WSUtils {

    private static final String URL_SERVER = "http://192.168.42.31:8000/";
    private static final String GET_ALL_SPOTS = URL_SERVER + "getSpot";


    public static ArrayList<Spot> getAllSpots() throws Exception {
        String json = OkHttpUtils.sendGetOkHttpRequest(GET_ALL_SPOTS);
        return MyApplication.gson.fromJson(json, new TypeToken<ArrayList<Spot>>(){}.getType());
    }
}
