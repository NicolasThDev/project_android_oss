package com.example.nico.ossproject;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nico.ossproject.bean.beanServer.Spot;
import com.example.nico.ossproject.controller.MyApplication;
import com.example.nico.ossproject.view.FishInSpotAdapter;

public class DetailSpotActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvType;
    private RatingBar rbAcces;
    private Spot spot;
    private FishInSpotAdapter fishInSpotAdapter;
    private RecyclerView recyclerView;

    private void findViews() {
        tvName = (TextView)findViewById( R.id.tv_name );
        tvType = (TextView)findViewById( R.id.tv_type );
        rbAcces = (RatingBar)findViewById( R.id.rb_acces );
        recyclerView = (RecyclerView) findViewById(R.id.rv_fishList);

        if (spot != null){
            tvName.setText(spot.getName());
            tvType.setText(spot.getSpot_type());
            rbAcces.setRating(spot.getSpot_acces());
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_spot);

        String jsonSpot = getIntent().getStringExtra("spot"); // get json spot in intent
        this.spot = MyApplication.gson.fromJson(jsonSpot, Spot.class); // convert json in list of spot
        if (spot == null){
            Log.w("tag", "spot is null");
            Toast.makeText(this, "Spot not selected", Toast.LENGTH_SHORT).show();
        }
        findViews(); // init component
        if (spot.getFish_in_spot() != null){ // spot have fish in spot
            fishInSpotAdapter = new FishInSpotAdapter(spot.getFish_in_spot());
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(fishInSpotAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,MapsActivity.MENU_ID_MYACCOUNT,0, R.string.myAccount);
        menu.add(0,MapsActivity.MENU_ID_DISCONNECT,0, R.string.disconnect);

        // set color of search menu icon
        Drawable searchIcon = getDrawable(android.R.drawable.ic_search_category_default);
        searchIcon.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);

        // set color of map menu icon
        Drawable mapIcon = getDrawable(android.R.drawable.ic_dialog_map);
        mapIcon.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);

        menu.add(0,MapsActivity.MENU_ID_SEARCH,0, R.string.search).setIcon(searchIcon).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(0,MapsActivity.MENU_ID_MAP,0, R.string.map).setIcon(mapIcon).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case MapsActivity.MENU_ID_SEARCH:
                // launch search activity
                Intent intentSearch = new Intent(this, SearchSpotActivity.class);
                startActivity(intentSearch);
                break;
            case MapsActivity.MENU_ID_MAP:
                // launch map activity
                Intent intentMap = new Intent(this, MapsActivity.class);
                startActivity(intentMap);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
