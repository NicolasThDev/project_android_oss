package com.example.nico.ossproject;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.nico.ossproject.bean.PolygonArea;
import com.example.nico.ossproject.bean.beanServer.Area;
import com.example.nico.ossproject.bean.beanServer.Spot;
import com.example.nico.ossproject.bean.beanUtils.MapUtils;
import com.example.nico.ossproject.bean.beanUtils.SharedPreferenceUtils;
import com.example.nico.ossproject.bean.beanUtils.WSUtils;
import com.example.nico.ossproject.controller.MyApplication;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnPolygonClickListener, View.OnClickListener, LocationListener {

    public final static int COARSE_LOCATION_REQ_CODE = 10;
    public final static int MENU_ID_MAP = 4;
    public final static int MENU_ID_SEARCH = 3;
    public final static int MENU_ID_DISCONNECT = 2;
    public final static int MENU_ID_MYACCOUNT = 1;

    //------------------
    //  ENUM
    //------------------
    public enum AsyncType {
        GET_ALL_SPOT,
        GET_SPOTS_AREA,
        GET_ALL_AREA
    }

    //------------------
    //  ATTRIBUTES
    //------------------
    private GoogleMap mMap;
    private Polygon polygonArea4, polygonArea3, polygonArea2, polygonArea1;
    private Button area1, area2, area3, area4;
    private EditText et_aroundMe;
    private ImageButton btn_search;
    private ArrayList<Marker> markerArrayList;
    private LocationManager mlocationMgr;
    private Location myLocation;
    private ArrayList<Area> areaArrayList;

    //------------------
    //  METHODS
    //------------------
    private void findViews() {
        area1 = (Button) findViewById(R.id.area1);
        area2 = (Button) findViewById(R.id.area2);
        area3 = (Button) findViewById(R.id.area3);
        area4 = (Button) findViewById(R.id.area4);
        et_aroundMe = (EditText) findViewById(R.id.et_aroundMe);
        btn_search = (ImageButton) findViewById(R.id.btn_search);

        area1.setOnClickListener(this);
        area2.setOnClickListener(this);
        area3.setOnClickListener(this);
        area4.setOnClickListener(this);
        btn_search.setOnClickListener(this);

        markerArrayList = new ArrayList<>();
        areaArrayList = new ArrayList<>();
        myLocation = new Location("myLocation");
    }

    private void clearMarker() { // delete all marker on map
        for (Marker marker : markerArrayList) {
            marker.remove();
        }
    }

    private void clickOnArea(int at, Polygon polygon) { // click on an area, launch asyntask and zoom on polygon
        clearMarker();
        AsyncTaskUtils asyncTaskUtils = new AsyncTaskUtils(AsyncType.GET_SPOTS_AREA, at);
        asyncTaskUtils.execute();
        MapUtils.polygonZoomIn(polygon, mMap); // zoom on area
    }

    private void displaySpotAroundMe(ArrayList<Spot> listSpot) {
        Log.w("tag", "launch function : displaySpotAroundMe");

        Location target = new Location("target"); // create new Location
        ArrayList<Spot> spots = new ArrayList<>();
        for (Spot spot : listSpot) {
            target.setLatitude(spot.getLattitude()); // set latitude of location
            target.setLongitude(spot.getLongitude()); // set longitude of location
            if (this.myLocation.distanceTo(target) < Integer.parseInt(et_aroundMe.getText().toString()) * 1000) { // compare my location and spot location to distance value
                spots.add(spot);
            }
        }
        if (!spots.isEmpty()) {
            markerArrayList.clear();
            // display and zoom on spots markers
            markerArrayList.addAll(MapUtils.spotsZoomIn(spots, mMap, this.myLocation));

            // hid the virtual keyboard
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } else {
            // spots list empty in perimeter
            Toast.makeText(this, "Not spot in this perimeter !", Toast.LENGTH_SHORT).show();
        }
    }

    //------------------
    //  OVERRIDES
    //------------------
    @Override
    public void onClick(View v) {
        if (v == area1) {
            clickOnArea(1, polygonArea1);
        } else if (v == area2) {
            clickOnArea(2, polygonArea2);
        } else if (v == area3) {
            clickOnArea(3, polygonArea3);
        } else if (v == area4) {
            clickOnArea(4, polygonArea4);
        } else if (v == btn_search) {
            try {
                Integer.parseInt(et_aroundMe.getText().toString());
                AsyncTaskUtils asyncTaskUtils = new AsyncTaskUtils(AsyncType.GET_ALL_SPOT);
                asyncTaskUtils.execute();
            } catch (Exception e) {
                if (et_aroundMe.getText().length() < 1) {
                    Toast.makeText(this, "Value is empty" + et_aroundMe.getText().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Wrong value : " + et_aroundMe.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        findViews(); // init component
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mlocationMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Log.w("tag", "location ok");
        } else {
            Log.w("tag", "not ok location");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, COARSE_LOCATION_REQ_CODE); // ask location permission
        }
        if (mlocationMgr.getAllProviders().contains(LocationManager.NETWORK_PROVIDER))
            mlocationMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 200, this);
        if (mlocationMgr.getAllProviders().contains(LocationManager.GPS_PROVIDER))
            mlocationMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 200, this);

//        Toast.makeText(this, "Pseudo : " + SharedPreferenceUtils.getUser().getLast_name(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == COARSE_LOCATION_REQ_CODE) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                // location enable
                if (mMap != null) {
                    mMap.setMyLocationEnabled(true);
                }
            }
        } else {
            // location disable
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Drawable searchIcon = getDrawable(android.R.drawable.ic_search_category_default); //  get icon
        searchIcon.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN); // change color filter
        Drawable mapIcon = getDrawable(android.R.drawable.ic_dialog_map);
        mapIcon.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_IN);

        menu.add(0, MENU_ID_MYACCOUNT, 0, R.string.myAccount);
        menu.add(0, MENU_ID_DISCONNECT, 0, R.string.disconnect);
        menu.add(0, MENU_ID_SEARCH, 0, R.string.search).setIcon(searchIcon).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(0, MENU_ID_MAP, 0, R.string.map).setIcon(mapIcon).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case MENU_ID_SEARCH: // when search menu item selected
                Intent intent = new Intent(this, SearchSpotActivity.class);
                startActivity(intent);
                break;
            case MENU_ID_DISCONNECT: // when disconnect menu item selected
                SharedPreferenceUtils.deleteUser(); // delete user in shared preference
                Intent intentLogin = new Intent(this, LoginActivity.class);
                startActivity(intentLogin); // launch login activity
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // location enable
            mMap.setMyLocationEnabled(true);
        }

        //-----------------
        // Test polygone
        //-------------------------------------------------
        polygonArea2 = mMap.addPolygon(new PolygonArea(
                new LatLng(47.238, -3.879),
                new LatLng(47.950, -3.879),
                new LatLng(47.950, -2.705),
                new LatLng(47.238, -2.705),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.area2))
                .createPolygon());
        polygonArea1 = mMap.addPolygon(new PolygonArea(
                new LatLng(48.159, -4.9),
                new LatLng(48.159, -3.879),
                new LatLng(47.700, -3.879),
                new LatLng(47.700, -4.9),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.area1))
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
        // ------------------------------------------------------------

        String jsonSpots = getIntent().getStringExtra("spots");// get spot list to display
        if (jsonSpots != null) {

            clearMarker();// clean all markers on map
            final ArrayList<Spot> spotArrayList = MyApplication.gson.fromJson(jsonSpots, new TypeToken<ArrayList<Spot>>() {
            }.getType());// convert json to list of spot
            mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                @Override
                public void onMapLoaded() {
                    markerArrayList.clear(); // clear markers list
                    markerArrayList.addAll(MapUtils.spotsZoomIn(spotArrayList, mMap, null));// zoom on spot marker and add them in markers list
                }
            });
        } else {
            LatLng bretagne = new LatLng(48.25, -4);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bretagne, 7));// move camera on Bretagne
        }
        mMap.setOnPolygonClickListener(this); // add listener on polygon
    }

    @Override
    public void onPolygonClick(Polygon polygon) {
        if (polygon.equals(polygonArea1)) {
            clickOnArea(1, polygonArea1); // click on an area, launch AsyncTask and zoom on polygon
        } else if (polygon.equals(polygonArea2)) {
            clickOnArea(2, polygonArea2);
        } else if (polygon.equals(polygonArea3)) {
            clickOnArea(3, polygonArea3);
        } else if (polygon.equals(polygonArea4)) {
            clickOnArea(4, polygonArea4);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        myLocation.set(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    //------------------
    //  INNER CLASS
    //------------------


    public class AsyncTaskUtils extends AsyncTask {

        //------------------
        //  ATTRIBUTES
        //------------------
        private AsyncType asyncType;
        private int areaId;

        //------------------
        //  CONSTRUCTOR
        //------------------
        public AsyncTaskUtils(AsyncType asyncType) {
            this.asyncType = asyncType;
        }

        public AsyncTaskUtils(AsyncType asyncType, int areaId) {
            this.areaId = areaId;
            this.asyncType = asyncType;
        }


        //------------------
        //  OVERRIDES
        //------------------
        @Override
        protected Object doInBackground(Object[] objects) {

            Object temp = null;
            switch (asyncType) {
                case GET_ALL_SPOT:
                    // LAUNCH REQUEST HERE !
                    try {
                        temp = WSUtils.getAllSpots(); // get all spots to server
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case GET_ALL_AREA:
                    // LAUNCH REQUEST HERE !
                    try {
                        temp = WSUtils.getAllAreas(); // get all areas to server
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case GET_SPOTS_AREA:
                    // LAUNCH REQUEST HERE !
                    try {
                        temp = WSUtils.getSpotInArea(areaId); // get all spots in area to server
//                        Log.w("tag", "temp size : " + temp.size() );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    temp = null;
                    break;
            }
            return temp;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            switch (asyncType) {
                case GET_ALL_SPOT:
                    if (o != null) {
                        displaySpotAroundMe((ArrayList<Spot>) o); // launch function display spot around me with receive list to server
                    } else {
                        Log.w("tag", "receive list is empty");
                        Toast.makeText(MapsActivity.this, "Error loading data", Toast.LENGTH_LONG).show();
                    }
                    break;
                case GET_ALL_AREA:

                    break;
                case GET_SPOTS_AREA:
                    if (o != null) {
                        markerArrayList.clear(); // clear markers list
                        for (Spot spot : (ArrayList<Spot>) o) {
                            Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(spot.getLattitude(), spot.getLongitude())).title(spot.getName())); // marker on map
                            marker.setTag(spot); // attribute spot to a marker
                            markerArrayList.add(marker); // add marker in the list
                        }
                    } else {
                        Log.w("tag", "receive list is empty");
                        Toast.makeText(MapsActivity.this, "Error loading data", Toast.LENGTH_LONG).show();
                    }
                    break;
                default:
                    break;
            }
        }
    }
}