package com.example.nico.ossproject.bean.beanServer;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Spot implements Parcelable {


    ////////////////////////
    // ATTRIBUTE
    ////////////////////////
    private int id;
    private String name;
    private String spotType;
    private int spotAcces;
    private long dateAdd;
    private double latittude;
    private double longitude;
    private Area area;
    private ArrayList<Comment> comment;
    private SiteUser siteUser;
    private ArrayList<FishInSpot> fishInSpot;

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

    public String getSpotType() {
        return spotType;
    }

    public void setSpotType(String spotType) {
        this.spotType = spotType;
    }

    public int getSpotAcces() {
        return spotAcces;
    }

    public void setSpotAcces(int spotAcces) {
        this.spotAcces = spotAcces;
    }

    public long getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(long dateAdd) {
        this.dateAdd = dateAdd;
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

    public SiteUser getSiteUser() {
        return siteUser;
    }

    public void setSiteUser(SiteUser siteUser) {
        this.siteUser = siteUser;
    }

    public ArrayList<FishInSpot> getFishInSpot() {
        return fishInSpot;
    }

    public void setFishInSpot(ArrayList<FishInSpot> fishInSpot) {
        this.fishInSpot = fishInSpot;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    protected Spot(Parcel in) {
        id = in.readInt();
        name = in.readString();
        spotType = in.readString();
        spotAcces = in.readInt();
        dateAdd = in.readLong();
        latittude = in.readDouble();
        longitude = in.readDouble();
        area = (Area) in.readValue(Area.class.getClassLoader());
        if (in.readByte() == 0x01) {
            comment = new ArrayList<Comment>();
            in.readList(comment, Comment.class.getClassLoader());
        } else {
            comment = null;
        }
        siteUser = (SiteUser) in.readValue(SiteUser.class.getClassLoader());
        if (in.readByte() == 0x01) {
            fishInSpot = new ArrayList<FishInSpot>();
            in.readList(fishInSpot, FishInSpot.class.getClassLoader());
        } else {
            fishInSpot = null;
        }
        selected = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(spotType);
        dest.writeInt(spotAcces);
        dest.writeLong(dateAdd);
        dest.writeDouble(latittude);
        dest.writeDouble(longitude);
        dest.writeValue(area);
        if (comment == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(comment);
        }
        dest.writeValue(siteUser);
        if (fishInSpot == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(fishInSpot);
        }
        dest.writeByte((byte) (selected ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Spot> CREATOR = new Parcelable.Creator<Spot>() {
        @Override
        public Spot createFromParcel(Parcel in) {
            return new Spot(in);
        }

        @Override
        public Spot[] newArray(int size) {
            return new Spot[size];
        }
    };
}