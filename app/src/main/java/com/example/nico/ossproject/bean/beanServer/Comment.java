package com.example.nico.ossproject.bean.beanServer;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nico on 31/08/2017.
 */

public class Comment implements Parcelable {

    protected Comment(Parcel in) {
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
