package com.example.h071211030_finalmobile.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.h071211030_finalmobile.API.ApiConfig;
import com.example.h071211030_finalmobile.R;
import com.example.h071211030_finalmobile.fragment.fragment_movie;
import com.example.h071211030_finalmobile.models.MovieResponse;
import com.example.h071211030_finalmobile.models.TvShowsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Call<TvShowsResponse> getNowPlaying = ApiConfig.getApiService().getAiringToday();
        getNowPlaying.enqueue(new Callback<TvShowsResponse>() {
            @Override
            public void onResponse(@NonNull Call<TvShowsResponse> call, @NonNull Response<TvShowsResponse> response) {
                System.out.println("Done");
            }

            @Override
            public void onFailure(@NonNull Call<TvShowsResponse> call, @NonNull Throwable t) {
                System.out.println("engga done");
            }
        });

        fragmentManager = getSupportFragmentManager();
        Fragment fragment = new fragment_movie();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_main, fragment);
        fragmentTransaction.commit();
    }
}