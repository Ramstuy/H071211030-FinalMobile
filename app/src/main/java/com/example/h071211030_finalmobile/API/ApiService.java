package com.example.h071211030_finalmobile.API;

import com.example.h071211030_finalmobile.models.MovieResponse;
import com.example.h071211030_finalmobile.models.TvShowsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/now_playing?api_key=39725c0f5856c3e5447f0d93ffc805fc")
    Call<MovieResponse> getNowPlaying();

    @GET("movie/popular?api_key=39725c0f5856c3e5447f0d93ffc805fc")
    Call<MovieResponse> getPopularMovie(@Query("api_key") String api_key);

    @GET("movie/top_rated?api_key=39725c0f5856c3e5447f0d93ffc805fc")
    Call<MovieResponse> getTopRatedMovie(@Query("api_key") String api_key);

    @GET("movie/upcoming?api_key=39725c0f5856c3e5447f0d93ffc805fc")
    Call<MovieResponse> getUpcoming(@Query("api_key") String api_key);

    @GET ("tv/airing_today?api_key=39725c0f5856c3e5447f0d93ffc805fc")
    Call<TvShowsResponse> getAiringToday();

    @GET ("tv/on_the_air?api_key=39725c0f5856c3e5447f0d93ffc805fc")
    Call<TvShowsResponse> getOnTheAir();

    @GET ("tv/popular?api_key=39725c0f5856c3e5447f0d93ffc805fc")
    Call<TvShowsResponse> getPopularTv();

    @GET ("tv/top_rated?api_key=39725c0f5856c3e5447f0d93ffc805fc")
    Call<TvShowsResponse> getTopRatedTv();
}