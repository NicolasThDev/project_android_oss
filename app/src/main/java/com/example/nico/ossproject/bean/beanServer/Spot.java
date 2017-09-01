package com.example.nico.ossproject.bean.beanServer;

import java.util.ArrayList;

public class Spot {


    ////////////////////////
    // ATTRIBUTE
    ////////////////////////
    private int id;
    private String name;
    private String spot_type;
    private int spot_acces;
    private long date_add;
    private double latittude;
    private double longitude;
    private Area area;
    private ArrayList<Comment> comment;
    private SiteUser site_user;
    private ArrayList<FishInSpot> fish_in_spot;

    private boolean selected;

    public Spot() {
    }

    public Spot(String name) {
        this.name = name;
    }


    ////////////////////////
    // GETTER / SETTER
    ////////////////////////

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpot_type() {
        return spot_type;
    }

    public void setSpot_type(String spot_type) {
        this.spot_type = spot_type;
    }

    public int getSpot_acces() {
        return spot_acces;
    }

    public void setSpot_acces(int spot_acces) {
        this.spot_acces = spot_acces;
    }

    public long getDate_add() {
        return date_add;
    }

    public void setDate_add(long date_add) {
        this.date_add = date_add;
    }

    public double getLatittude() {
        return latittude;
    }

    public void setLatittude(double latittude) {
        this.latittude = latittude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public ArrayList<Comment> getComment() {
        return comment;
    }

    public void setComment(ArrayList<Comment> comment) {
        this.comment = comment;
    }

    public SiteUser getSite_user() {
        return site_user;
    }

    public void setSite_user(SiteUser site_user) {
        this.site_user = site_user;
    }

    public ArrayList<FishInSpot> getFish_in_spot() {
        return fish_in_spot;
    }

    public void setFish_in_spot(ArrayList<FishInSpot> fish_in_spot) {
        this.fish_in_spot = fish_in_spot;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}