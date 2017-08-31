package com.example.nico.ossproject;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.nico.ossproject.bean.ColorProject;
import com.example.nico.ossproject.bean.beanServer.Spot;
import com.example.nico.ossproject.view.SpotListAdapter;

import java.util.ArrayList;

public class SearchSpotActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SpotListAdapter.OnClickSpot {
    private ImageView iv_map, iv_heart;
    private CheckBox cbSelectAll;
    private Button btnAddFav;
    private Button btnViewMap;
    private EditText et_search;
    private ImageButton btnSearch;
    private ArrayList<Spot> spotArrayList;
    private ArrayList<Spot> spotListMap;
    private SpotListAdapter spotListAdapter;

    private RecyclerView recyclerView;

    private void findViews() {
        cbSelectAll = (CheckBox)findViewById( R.id.cb_selectAll );
        btnAddFav = (Button)findViewById( R.id.btn_addFav );
        btnViewMap = (Button)findViewById( R.id.btn_viewMap );
        btnSearch = (ImageButton)findViewById( R.id.btn_search );
        iv_map = (ImageView)findViewById( R.id.iv_map );
        iv_heart = (ImageView)findViewById( R.id.iv_heart );
        et_search = (EditText) findViewById( R.id.et_search );

        recyclerView = (RecyclerView) findViewById(R.id.rv_spots);

        iv_heart.setColorFilter(ColorProject.COLOR_WHITE);
        iv_map.setColorFilter(ColorProject.COLOR_WHITE);
        btnSearch.setColorFilter(ColorProject.COLOR_WHITE);

        cbSelectAll.setOnCheckedChangeListener(this);

        btnSearch.setOnClickListener(this);
        btnAddFav.setOnClickListener( this );
        btnViewMap.setOnClickListener( this );

        et_search.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                spotListAdapter.setFilter(et_search.getText().toString());
            }
        });
    }

    @Override
    public void onClick(View v) {
        if ( v == btnAddFav ) {

        } else if ( v == btnViewMap ) {
            spotListMap = new ArrayList<>();
            spotListMap.clear();
            for (int i = 0; i < spotListAdapter.getFilterSpotArrayList().size(); i++){
                if (spotListAdapter.getFilterSpotArrayList().get(i).isSelected()){
                    spotListMap.add(spotListAdapter.getFilterSpotArrayList().get(i));
                }
            }
            Intent intent = new Intent(this, MapsActivity.class);
            intent.putParcelableArrayListExtra("spots", spotListMap);
            startActivity(intent);
        } else if (v == btnSearch ) {
            spotListAdapter.setFilter(et_search.getText().toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_spot);
        findViews();
        spotArrayList = new ArrayList<>();

        Spot spot1 = new Spot("Bénodet Letty");
        spot1.setLatittude(47.861);
        spot1.setLongitude(-4.088);
        spot1.setSpotAcces(3);
        spot1.setSpotType("Port");

        Spot spot2 = new Spot("Bénodet chenal");
        spot2.setLatittude(47.863);
        spot2.setLongitude(-4.107);
        spot2.setSpotAcces(5);
        spot2.setSpotType("Mer");

        Spot spot3 = new Spot("Bénodet plage");
        spot3.setLatittude(47.871);
        spot3.setLongitude(-4.110);
        spot3.setSpotAcces(1);
        spot3.setSpotType("Plage");

        spotArrayList.add(spot1);
        spotArrayList.add(spot2);
        spotArrayList.add(spot3);

        spotListAdapter = new SpotListAdapter(spotArrayList, this);
        recyclerView.setAdapter(spotListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0, R.string.myAccount);
        menu.add(0,2,0, R.string.disconnect);
        Drawable searchIcon = getDrawable(android.R.drawable.ic_search_category_default);
        searchIcon.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_IN);

        Drawable mapIcon = getDrawable(android.R.drawable.ic_dialog_map);
        mapIcon.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);

        menu.add(0,3,0, R.string.search).setIcon(searchIcon).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(0,4,0, R.string.map).setIcon(mapIcon).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 4:
                Intent intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            spotListAdapter.setCheckAll(isChecked);
            spotListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSpotMapClick(Spot spot) {
        spotListMap = new ArrayList<>();
        spotListMap.add(spot);
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putParcelableArrayListExtra("spots", spotListMap);
        startActivity(intent);
    }

    @Override
    public void onSpotDetailClick(Spot spot) {
        Intent intent = new Intent(this, DetailSpotActivity.class);
        intent.putExtra("spot", spot);
        startActivity(intent);
    }

    @Override
    public void onSpotFavoriteClick(Spot spot) {

    }
}
