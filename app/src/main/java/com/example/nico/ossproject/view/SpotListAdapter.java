package com.example.nico.ossproject.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nico.ossproject.R;
import com.example.nico.ossproject.bean.ColorProject;
import com.example.nico.ossproject.bean.beanServer.Spot;

import java.util.ArrayList;

public class SpotListAdapter extends RecyclerView.Adapter<SpotListAdapter.ViewHolder> {

    private ArrayList<Spot> spotArrayList;
    private ArrayList<Spot> filterSpotArrayList;
    private boolean checkAll = false;
    private String filter = null;
    private OnClickSpot onClickSpot;

    public SpotListAdapter(ArrayList<Spot> spotArrayList, OnClickSpot onClickSpot) {
        this.spotArrayList = spotArrayList;
        this.onClickSpot = onClickSpot;
        filterSpotArrayList = new ArrayList<>();
        filterSpotArrayList.addAll(this.spotArrayList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_spot, parent, false);
        return new SpotListAdapter.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Spot spot = filterSpotArrayList.get(position);
        holder.tv_spotName.setText(spot.getName());
        holder.ib_detail.setColorFilter(ColorProject.COLOR_PRIMARY);
        holder.ib_map.setColorFilter(ColorProject.COLOR_PRIMARY);
        holder.ib_favorite.setColorFilter(ColorProject.COLOR_PRIMARY);
        holder.ib_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSpot.onSpotDetailClick(spot);
            }
        });
        holder.ib_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSpot.onSpotFavoriteClick(spot);
            }
        });
        holder.ib_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSpot.onSpotMapClick(spot);
            }
        });
        holder.cb_selectOneSpot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                spot.setSelected(isChecked);
            }
        });

        holder.cb_selectOneSpot.setChecked(spot.isSelected());


    }

    @Override
    public int getItemCount() {
        return filterSpotArrayList.size();
    }

    public boolean isCheckAll() {
        return checkAll;
    }

    public void setCheckAll(boolean checkAll) {
        this.checkAll = checkAll;
        for(Spot spot : spotArrayList) {
            spot.setSelected(checkAll);
        }
    }

    public void setFilter(String filter) {
        if(filter != null) {
            this.filter = filter.toLowerCase();
        }
        else {
            this.filter = null;
        }
        applyFilter();
    }

    public void applyFilter() {
        filterSpotArrayList.clear();
        for(Spot spot : spotArrayList) {
            if (filter == null){
                filterSpotArrayList.add(spot);
            }
            else if (spot.getName().toLowerCase().contains(filter)) {
                filterSpotArrayList.add(spot);
            }
        }

        notifyDataSetChanged();
    }

    public void reset(){
        this.filterSpotArrayList.clear();
        this.filterSpotArrayList.addAll(this.spotArrayList);
        notifyDataSetChanged();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_spotName;
        public ImageButton ib_map, ib_detail, ib_favorite;
        public CheckBox cb_selectOneSpot;
        public ViewHolder(View itemView) {
            super(itemView);
            ib_map = (ImageButton) itemView.findViewById(R.id.ib_map);
            ib_detail = (ImageButton) itemView.findViewById(R.id.ib_detail);
            ib_favorite = (ImageButton) itemView.findViewById(R.id.ib_favorite);
            tv_spotName = (TextView) itemView.findViewById(R.id.tv_spotName);
            cb_selectOneSpot = (CheckBox) itemView.findViewById(R.id.cb_selectOneSpot);
        }
    }

    public ArrayList<Spot> getFilterSpotArrayList() {
        return filterSpotArrayList;
    }

    public interface OnClickSpot{
        void onSpotMapClick(Spot spot);
        void onSpotDetailClick(Spot spot);
        void onSpotFavoriteClick(Spot spot);
    }
}
