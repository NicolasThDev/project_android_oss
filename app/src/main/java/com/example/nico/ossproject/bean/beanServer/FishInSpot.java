package com.example.nico.ossproject.bean.beanServer;

public class FishInSpot {
    private int id;
    private int size;
    private int attitude;
    private int existence;
    private transient Spot spot;
    private SiteUser site_user;
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

    public SiteUser getSite_user() {
        return site_user;
    }

    public void setSite_user(SiteUser site_user) {
        this.site_user = site_user;
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