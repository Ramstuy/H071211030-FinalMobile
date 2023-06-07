package com.example.h071211030_finalmobile.API;

import com.example.h071211030_finalmobile.models.MovieResponse;
import com.example.h071211030_finalmobile.models.TvShowsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/now_playing?api_key=39725c0f5856c3e5447f0d93ffc805fc")
    Call<MovieResponse> getNowPlaying();

    @GET("movie/popular")
    Call<MovieResponse> getPopular(@Query("api_key") String api_key);

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRated(@Query("api_key") String api_key);

    @GET("movie/upcoming")
    Call<MovieResponse> getUpcoming(@Query("api_key") String api_key);

    @GET ("tv/airing_today?api_key=39725c0f5856c3e5447f0d93ffc805fc")
    Call<TvShowsResponse> getAiringToday();
}