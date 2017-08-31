package com.example.nico.ossproject;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.nico.ossproject.bean.beanServer.Spot;

public class DetailSpotActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvType;
    private RatingBar rbAcces;
    private Spot spot;

    private void findViews() {
        tvName = (TextView)findViewById( R.id.tv_name );
        tvType = (TextView)findViewById( R.id.tv_type );
        rbAcces = (RatingBar)findViewById( R.id.rb_acces );

        if (spot != null){
            tvName.setText(spot.getName());
            tvType.setText(spot.getSpotType());
            rbAcces.setRating(spot.getSpotAcces());
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_spot);

        this.spot = getIntent().getParcelableExtra("spot");
        findViews();
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
}
