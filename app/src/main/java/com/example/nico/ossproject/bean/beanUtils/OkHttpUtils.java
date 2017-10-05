package com.example.nico.ossproject.bean.beanUtils;

import android.util.Log;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

//------------------------------------------------------------
// ADD IN GRADLE : compile 'com.squareup.okhttp3:okhttp:3.8.1'
//------------------------------------------------------------

public class OkHttpUtils {
    public static String sendGetOkHttpRequest(String url) throws Exception{
        Log.w("tag", "Url : " + url);
        OkHttpClient client = new OkHttpClient();
        //CREATE REQUEST
        Request request = new Request.Builder().url(url).build();
        //EXECUTE REQUEST
        Response response = client.newCall(request).execute();
        //ANALYSE RETURN CODE
        if (response.code() != 200) {  // YOU CAN CUSTOMIZE ACCORDING TO THE RESPONSE CODE
            throw new Exception("RESPONSE SERVER ERROR : " + response.code());
        }
        else {
            // REQUEST RESULT
            return response.body().string();
        }
    }

    public static String sendPostOkHttpRequest(String url, String paramJson) throws Exception {
        Log.w("tag", "Url : " + url);
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        //REQUEST CONTENT
        RequestBody body = RequestBody.create(JSON, paramJson);
        //CREATE REQUEST
        Request request = new Request.Builder().url(url).post(body).build();
        //EXECUTE REQUEST
        Response response = client.newCall(request).execute();
        //ANALYSE RETURN CODE
        if (response.code() != 200) { // YOU CAN CUSTOMIZE ACCORDING TO THE RESPONSE CODE
            throw new Exception("RESPONSE SERVER ERROR : " + response.code());
        }
        else {
            // REQUEST RESULT
            return response.body().string();
        }
    }
}