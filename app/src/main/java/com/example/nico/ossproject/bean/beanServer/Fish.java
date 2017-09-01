package com.example.nico.ossproject.bean.beanServer;

import java.util.ArrayList;

public class Fish{
    private int id;
    private String wikiLink;
    private String picture;
    private String fishName;
    private SiteUser siteUser;
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

    public String getWikiLink() {
        return wikiLink;
    }

    public void setWikiLink(String wikiLink) {
        this.wikiLink = wikiLink;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getFishName() {
        return fishName;
    }

    public void setFishName(String fishName) {
        this.fishName = fishName;
    }

    public SiteUser getSiteUser() {
        return siteUser;
    }

    public void setSiteUser(SiteUser siteUser) {
        this.siteUser = siteUser;
    }

    public ArrayList<FishInSpot> getFishInSpotArrayList() {
        return fishInSpotArrayList;
    }

    public void setFishInSpotArrayList(ArrayList<FishInSpot> fishInSpotArrayList) {
        this.fishInSpotArrayList = fishInSpotArrayList;
    }
}