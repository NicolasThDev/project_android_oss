package com.example.nico.ossproject.bean;


import com.example.nico.ossproject.R;
import com.example.nico.ossproject.controller.MyApplication;

public class ColorProject {
    public static int COLOR_PRIMARY;
    public static int COLOR_PRIMARY_DARK;
    public static int COLOR_WHITE;
    public static int COLOR_TEXT_GREY;
    public static int COLOR_TRANSPARENT;

    public static void init(){
        COLOR_PRIMARY = MyApplication.getInstance().getResources().getColor(R.color.colorPrimary);
        COLOR_PRIMARY_DARK = MyApplication.getInstance().getResources().getColor(R.color.colorPrimaryDark);
        COLOR_WHITE = MyApplication.getInstance().getResources().getColor(R.color.white);
        COLOR_TEXT_GREY = MyApplication.getInstance().getResources().getColor(R.color.textGrey);
        COLOR_TRANSPARENT = MyApplication.getInstance().getResources().getColor(R.color.transparent);
    }
}
