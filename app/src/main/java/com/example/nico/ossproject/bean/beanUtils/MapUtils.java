package com.example.nico.ossproject.bean.beanUtils;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Polygon;

import java.util.List;

/**
 * Created by nico on 30/08/2017.
 */

public class MapUtils {
    public static void polygonZoomIn(Polygon polygon, GoogleMap googleMap){
        LatLngBounds.Builder latLngBounds = new LatLngBounds.Builder();
        List<LatLng> points = polygon.getPoints();
        for (int i = 0; i<points.size(); i++){
            latLngBounds.include(points.get(i));
        }
        int padding = 50; // offset from edges of the map in pixels
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds.build(), padding));
    }
}
