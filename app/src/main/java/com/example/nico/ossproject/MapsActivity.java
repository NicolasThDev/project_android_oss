package com.example.nico.ossproject;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.nico.ossproject.bean.PolygonArea;
import com.example.nico.ossproject.bean.beanServer.Spot;
import com.example.nico.ossproject.bean.beanUtils.MapUtils;
import com.example.nico.ossproject.controller.MyApplication;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnPolygonClickListener, View.OnClickListener {


    private GoogleMap mMap;
    private Polygon polygonArea4, polygonArea3, polygonArea2, polygonArea1;
    private Button area1, area2, area3, area4;

    private void findViews() {
        area1 = (Button)findViewById( R.id.area1 );
        area2 = (Button)findViewById( R.id.area2 );
        area3 = (Button)findViewById( R.id.area3 );
        area4 = (Button)findViewById( R.id.area4 );

        area1.setOnClickListener( this );
        area2.setOnClickListener( this );
        area3.setOnClickListener( this );
        area4.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        if ( v == area1 ) {
            MapUtils.polygonZoomIn(polygonArea1, mMap);
        } else if ( v == area2 ) {
            MapUtils.polygonZoomIn(polygonArea2, mMap);
        } else if ( v == area3 ) {
            MapUtils.polygonZoomIn(polygonArea3, mMap);
        } else if ( v == area4 ) {
            MapUtils.polygonZoomIn(polygonArea4, mMap);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        findViews();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0, R.string.myAccount);
        menu.add(0,2,0, R.string.disconnect);
        Drawable searchIcon = getDrawable(android.R.drawable.ic_search_category_default);
        searchIcon.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);

        Drawable mapIcon = getDrawable(android.R.drawable.ic_dialog_map);
        mapIcon.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_IN);

        menu.add(0,3,0, R.string.search).setIcon(searchIcon).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(0,4,0, R.string.map).setIcon(mapIcon).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case 3:
                Intent intent = new Intent(this, SearchSpotActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Test polygone

        polygonArea1 = mMap.addPolygon(new PolygonArea(
                new LatLng(47.238, -3.879),
                new LatLng(47.950, -3.879),
                new LatLng(47.950, -2.705),
                new LatLng(47.238, -2.705),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.area1))
                .createPolygon());
        polygonArea2 = mMap.addPolygon(new PolygonArea(
                new LatLng(48.159, -4.9),
                new LatLng(48.159, -3.879),
                new LatLng(47.700, -3.879),
                new LatLng(47.700, -4.9),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.area2))
                .createPolygon());
        polygonArea3 = mMap.addPolygon(new PolygonArea(
                new LatLng(48.159, -3.879),
                new LatLng(48.159, -5.204),
                new LatLng(48.809, -5.204),
                new LatLng(48.809, -3.879),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.area3))
                .createPolygon());
        polygonArea4 = mMap.addPolygon(new PolygonArea(
                new LatLng(48.448, -3.879),
                new LatLng(48.952, -3.879),
                new LatLng(48.952, -2.705),
                new LatLng(48.448, -2.705),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.area4))
                .createPolygon());

        String jsonSpots = getIntent().getStringExtra("spots");
        if (jsonSpots != null){
            ArrayList<Spot> spotArrayList = MyApplication.gson.fromJson(jsonSpots, new TypeToken<ArrayList<Spot>>(){}.getType());
            MapUtils.spotsZoomIn(spotArrayList, mMap);
        } else {
            LatLng bretagne = new LatLng(48.25, -4);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bretagne, 7));
        }

        mMap.setOnPolygonClickListener(this);
    }

    @Override
    public void onPolygonClick(Polygon polygon) {
        if (polygon.equals(polygonArea1)){
            MapUtils.polygonZoomIn(polygonArea1, mMap);
        } else if (polygon.equals(polygonArea2)){
            MapUtils.polygonZoomIn(polygonArea2, mMap);
        } else  if (polygon.equals(polygonArea3)){
            MapUtils.polygonZoomIn(polygonArea3, mMap);
        } else  if (polygon.equals(polygonArea4)){
            MapUtils.polygonZoomIn(polygonArea4, mMap);
        }
    }
}
