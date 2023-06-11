package com.example.h071211030_finalmobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.h071211030_finalmobile.R;

public class DetailActivity extends AppCompatActivity {

    int movie_id;
    ImageView backdrop, poster, favorite, back;
    TextView title, vote_average, release_date, m_or_tv, overview;
    String movie_backdrop, movie_poster , movie_title,  movie_release_date, movie_or_tv, movie_overview;
    Double movie_vote_average;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        movie_backdrop = getIntent().getStringExtra("movie_backdrop");
        movie_poster = getIntent().getStringExtra("movie_poster");
        movie_id = getIntent().getIntExtra("movie_id", 0);
        movie_title = getIntent().getStringExtra("movie_title");
        movie_vote_average = getIntent().getDoubleExtra("movie_vote_average", 0);
        movie_release_date = getIntent().getStringExtra("movie_release_date");
        movie_or_tv = getIntent().getStringExtra("movie_or_tv");
        movie_overview = getIntent().getStringExtra("movie_overview");

        backdrop = findViewById(R.id.iv_detBackdrop);
        poster = findViewById(R.id.iv_poster);
        title = findViewById(R.id.tvName);
        vote_average = findViewById(R.id.tvRating);
        release_date = findViewById(R.id.tvRelease);
        m_or_tv = findViewById(R.id.tvMovieOrTv);
        overview = findViewById(R.id.tvOverview);

        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + movie_backdrop).into(backdrop);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + movie_poster).into(poster);
        title.setText(movie_title);
        vote_average.setText(String.valueOf(movie_vote_average));
        release_date.setText(movie_release_date);
        m_or_tv.setText(movie_or_tv);
        overview.setText(movie_overview);

        back = findViewById(R.id.iv_back);
        back.setOnClickListener(v -> {
            finish();
        });


    }
}