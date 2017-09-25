package com.example.nico.ossproject.bean.beanServer;

import java.util.ArrayList;

public class Fish{
    private int id;
    private String wiki_link;
    private String picture;
    private String fish_name;
    private SiteUser site_user;
    private ArrayList<FishInSpot> fishInSpotArrayList;

    public Fish() {

    }

    ////////////////////////
    //   GETTER /SETTER
    ////////////////////////

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWiki_link() {
        return wiki_link;
    }

    public void setWiki_link(String wiki_link) {
        this.wiki_link = wiki_link;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getFish_name() {
        return fish_name;
    }

    public void setFish_name(String fish_name) {
        this.fish_name = fish_name;
    }

    public SiteUser getSite_user() {
        return site_user;
    }

    public void setSite_user(SiteUser site_user) {
        this.site_user = site_user;
    }

    public ArrayList<FishInSpot> getFishInSpotArrayList() {
        return fishInSpotArrayList;
    }

    public void setFishInSpotArrayList(ArrayList<FishInSpot> fishInSpotArrayList) {
        this.fishInSpotArrayList = fishInSpotArrayList;
    }
}