package com.example.nico.ossproject.bean.beanUtils;

import com.example.nico.ossproject.bean.beanServer.Area;
import com.example.nico.ossproject.bean.beanServer.LoginApp;
import com.example.nico.ossproject.bean.beanServer.SiteUser;
import com.example.nico.ossproject.bean.beanServer.Spot;
import com.example.nico.ossproject.controller.MyApplication;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;



public class WSUtils {

    private static final String URL_SERVER = "http://192.168.1.14:8000/";
    private static final String GET_ALL_SPOTS = URL_SERVER + "getSpot";
    private static final String GET_ALL_AREA = URL_SERVER + "getAreas";
    private static final String LOGIN_APP = URL_SERVER + "loginApp";
    private static final String GET_SPOT_IN_AREA = URL_SERVER + "getSpotInArea/";


    public static ArrayList<Spot> getAllSpots() throws Exception {
        String json = OkHttpUtils.sendGetOkHttpRequest(GET_ALL_SPOTS);
        return MyApplication.gson.fromJson(json, new TypeToken<ArrayList<Spot>>(){}.getType());
    }

    public static SiteUser loginAppServer(String login, String password) throws Exception {
        LoginApp loginApp = new LoginApp(login, password);
        String jsonSend = MyApplication.gson.toJson(loginApp);
        String jsonResult = OkHttpUtils.sendPostOkHttpRequest(LOGIN_APP, jsonSend);
        return MyApplication.gson.fromJson(jsonResult, SiteUser.class);
    }

    public static ArrayList<Spot> getSpotInArea(int areaId) throws Exception {
        String json = OkHttpUtils.sendGetOkHttpRequest(GET_SPOT_IN_AREA + areaId);
        return MyApplication.gson.fromJson(json, new TypeToken<ArrayList<Spot>>(){}.getType());
    }

    public static ArrayList<Area> getAllAreas() throws Exception {
        String json = OkHttpUtils.sendGetOkHttpRequest(GET_ALL_AREA);
        return MyApplication.gson.fromJson(json, new TypeToken<ArrayList<Area>>(){}.getType());
    }
}
