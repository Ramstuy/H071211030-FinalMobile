package com.example.h071211030_finalmobile.SQLite;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class FavoList implements Parcelable {
    private int id;
    private String backdrop, poster, name, rating, release_date, overview;
    public FavoList() {
    }
    public FavoList(int id, String backdrop, String poster, String name, String rating, String release_date, String overview) {
        this.id = id;
        this.backdrop = backdrop;
        this.poster = poster;
        this.name = name;
        this.rating = rating;
        this.release_date = release_date;
        this.overview = overview;
    }
    protected FavoList(Parcel in) {
        id = in.readInt();
        backdrop = in.readString();
        poster = in.readString();
        name = in.readString();
        rating = in.readString();
        release_date = in.readString();
        overview = in.readString();
    }
    public static final Creator<FavoList> CREATOR = new Creator<FavoList>() {
        @Override
        public FavoList createFromParcel(Parcel in) {
            return new FavoList(in);
        }@Override
        public FavoList[] newArray(int size) {
            return new FavoList[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(backdrop);
        parcel.writeString(poster);
        parcel.writeString(name);
        parcel.writeString(rating);
        parcel.writeString(release_date);
        parcel.writeString(overview);
    }
}