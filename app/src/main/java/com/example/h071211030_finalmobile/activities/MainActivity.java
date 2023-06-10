package com.example.h071211030_finalmobile.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.h071211030_finalmobile.API.ApiConfig;
import com.example.h071211030_finalmobile.R;
import com.example.h071211030_finalmobile.fragment.TvShowsFragment;
import com.example.h071211030_finalmobile.fragment.fragment_favorite;
import com.example.h071211030_finalmobile.fragment.fragment_movie;
import com.example.h071211030_finalmobile.models.MovieResponse;
import com.example.h071211030_finalmobile.models.TvShowsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView tvTop;
    LinearLayout llMovie, llTv, llFavor;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTop = findViewById(R.id.tv_top);
        llMovie = findViewById(R.id.ll_movie);
        llTv = findViewById(R.id.ll_tv);
        llFavor = findViewById(R.id.ll_favorite);

        fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(fragment_movie.class.getSimpleName());

        if (!(fragment instanceof fragment_movie)){
            mooveFragment(new fragment_movie());
            tvTop.setText("Movie");
        }

        llMovie.setOnClickListener(view -> {
            mooveFragment(new fragment_movie());
            tvTop.setText("Movie");
        });

        llTv.setOnClickListener(view -> {
            mooveFragment(new TvShowsFragment());
            tvTop.setText("Tv Shows");
        });

        llFavor.setOnClickListener(view -> {
            mooveFragment(new fragment_favorite());
            tvTop.setText("Favorite");
        });

    }

    private void mooveFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.frame_main, fragment, fragment.getClass().getSimpleName())
                .commit();
    }
}