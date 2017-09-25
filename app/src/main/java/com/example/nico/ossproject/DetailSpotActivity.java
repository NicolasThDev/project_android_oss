package com.example.nico.ossproject;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
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

        String jsonSpot = getIntent().getStringExtra("spot");
        this.spot = MyApplication.gson.fromJson(jsonSpot, Spot.class);
        if (spot == null){
            Log.w("tag", "spot est null");
        }
        findViews();
        if (spot.getFish_in_spot() != null){
            fishInSpotAdapter = new FishInSpotAdapter(spot.getFish_in_spot());
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(fishInSpotAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0, R.string.myAccount);
        menu.add(0,2,0, R.string.disconnect);

        Drawable searchIcon = getDrawable(android.R.drawable.ic_search_category_default);
        searchIcon.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);

        Drawable mapIcon = getDrawable(android.R.drawable.ic_dialog_map);
        mapIcon.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);

        menu.add(0,3,0, R.string.search).setIcon(searchIcon).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(0,4,0, R.string.map).setIcon(mapIcon).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 3:
                Intent intentSearch = new Intent(this, SearchSpotActivity.class);
                startActivity(intentSearch);
                break;
            case 4:
                Intent intentMap = new Intent(this, MapsActivity.class);
                startActivity(intentMap);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public class AT extends AsyncTask {



        @Override
        protected Object doInBackground(Object[] params) {
            try {

                //Log.w("tag", "temp size : " + temp.size() );
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
//            if (temp != null){
//
//            } else{
//                Log.w("tag", "la liste recu est null");
//            }

        }
    }
}
