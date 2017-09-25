package com.example.nico.ossproject.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nico.ossproject.R;
import com.example.nico.ossproject.bean.beanServer.FishInSpot;

import java.util.ArrayList;



public class FishInSpotAdapter extends RecyclerView.Adapter<FishInSpotAdapter.ViewHolder> {

    private ArrayList<FishInSpot> fishInSpotArrayList;

    public FishInSpotAdapter(ArrayList<FishInSpot> fishInSpotArrayList) {
        this.fishInSpotArrayList = fishInSpotArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_fish_in_spot, parent, false);
        return new FishInSpotAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final FishInSpot fishInSpot = fishInSpotArrayList.get(position);
        holder.rb_size.setRating(fishInSpot.getSize());
        holder.rb_curiosity.setRating(fishInSpot.getAttitude());
        holder.rb_presence.setRating(fishInSpot.getExistence());
        if (fishInSpot.getFish() != null){
            Glide.with(holder.iv_pictureFish.getContext()).load(fishInSpot.getFish().getPicture()).into(holder.iv_pictureFish);
            holder.tv_fishName.setText(fishInSpot.getFish().getFish_name());
        }

        holder.detailFish.setVisibility(fishInSpot.isVisible() ? View.VISIBLE :  View.GONE);
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fishInSpot.setVisible(!fishInSpot.isVisible());
                for(int i =0; i< fishInSpotArrayList.size(); i++) {
                    FishInSpot f = fishInSpotArrayList.get(i);
                    if(f != fishInSpot && f.isVisible()) {
                        f.setVisible(false);
                        notifyItemChanged(i);
                    }
                }
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fishInSpotArrayList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_fishName;
        public View root, detailFish;
        public ImageView iv_pictureFish;
        public RatingBar rb_size, rb_curiosity, rb_presence;
        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            detailFish = itemView.findViewById(R.id.detailFish);
            iv_pictureFish = (ImageView) itemView.findViewById(R.id.iv_pictureFish);
            rb_size = (RatingBar) itemView.findViewById(R.id.rb_size);
            rb_curiosity = (RatingBar) itemView.findViewById(R.id.rb_curiosity);
            rb_presence = (RatingBar) itemView.findViewById(R.id.rb_presence);
            tv_fishName = (TextView) itemView.findViewById(R.id.tv_fishName);
        }
    }
}
