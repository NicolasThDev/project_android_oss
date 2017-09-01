package com.example.nico.ossproject.bean.beanServer;

import android.os.Parcelable;

public class FishInSpot {
    private int id;
    private int size;
    private int attitude;
    private int existence;
    private transient Spot spot;
    private SiteUser siteUser;
    private Fish fish;
    private transient boolean visible;

    public FishInSpot() {

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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getAttitude() {
        return attitude;
    }

    public void setAttitude(int attitude) {
        this.attitude = attitude;
    }

    public int getExistence() {
        return existence;
    }

    public void setExistence(int existence) {
        this.existence = existence;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public SiteUser getSiteUser() {
        return siteUser;
    }

    public void setSiteUser(SiteUser siteUser) {
        this.siteUser = siteUser;
    }

    public Fish getFish() {
        return fish;
    }

    public void setFish(Fish fish) {
        this.fish = fish;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}