package com.example.nico.ossproject.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nico.ossproject.R;
import com.example.nico.ossproject.bean.beanServer.FishInSpot;

import java.util.ArrayList;

/**
 * Created by nico on 31/08/2017.
 */

public class FishInSpotAdapter extends RecyclerView.Adapter<FishInSpotAdapter.ViewHolder> {

    private ArrayList<FishInSpot> fishInSpotArrayList;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_fish_in_spot, parent, false);
        return new FishInSpotAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FishInSpot fishInSpot = fishInSpotArrayList.get(position);
        Glide.with(holder.iv_pictureFish.getContext()).load("https://www.msc.org/multimedia-fr/images-fr/especes-certifiees/bar").into(holder.iv_pictureFish);

    }

    @Override
    public int getItemCount() {
        return fishInSpotArrayList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_fishName;
        public ImageView iv_pictureFish;
        public RatingBar rb_size, rb_curiosity, rb_presence;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_pictureFish = (ImageView) itemView.findViewById(R.id.iv_pictureFish);
            rb_size = (RatingBar) itemView.findViewById(R.id.rb_size);
            rb_curiosity = (RatingBar) itemView.findViewById(R.id.rb_curiosity);
            rb_presence = (RatingBar) itemView.findViewById(R.id.rb_presence);
            tv_fishName = (TextView) itemView.findViewById(R.id.tv_fishName);
        }
    }
}
