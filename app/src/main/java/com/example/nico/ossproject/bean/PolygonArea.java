package com.example.nico.ossproject.bean;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;

public class PolygonArea {
    private LatLng positionA, positionB, positionC, positionD;
    private int colorBorder, colorFill;

    public PolygonArea(LatLng positionA, LatLng positionB, LatLng positionC, LatLng positionD, int colorBorder, int colorFill) {
        this.positionA = positionA;
        this.positionB = positionB;
        this.positionC = positionC;
        this.positionD = positionD;
        this.colorBorder = colorBorder;
        this.colorFill = colorFill;
    }

    public PolygonOptions createPolygon(){
        PolygonOptions polygonOptions = new PolygonOptions()
                .add(positionA, positionB, positionC, positionD)
                .strokeColor(colorBorder)
                .fillColor(colorFill)
                .strokeWidth(3)
                .clickable(true);
        return polygonOptions;
    }
}
