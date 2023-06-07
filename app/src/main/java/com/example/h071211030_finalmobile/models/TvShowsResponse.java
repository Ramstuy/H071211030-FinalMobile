package com.example.h071211030_finalmobile.models;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowsResponse {
    @SerializedName("results")
    private List<TvShows> results;

    public List<TvShows> getResults() {
        return results;
    }
}

