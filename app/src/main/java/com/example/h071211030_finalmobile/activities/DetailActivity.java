package com.example.h071211030_finalmobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.h071211030_finalmobile.R;
import com.example.h071211030_finalmobile.SQLite.DatabaseContract;
import com.example.h071211030_finalmobile.SQLite.List;
import com.example.h071211030_finalmobile.SQLite.ListHelper;
import com.example.h071211030_finalmobile.SQLite.MappingHelper;

import java.lang.ref.WeakReference;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DetailActivity extends AppCompatActivity {

    int movie_id;
    ImageView backdrop, poster, favorite, back;
    TextView title, vote_average, release_date, m_or_tv, overview;
    String movie_backdrop;
    String movie_poster;
    String movie_title;
    String movie_release_date;
    String movie_or_tv;
    String movie_overview;
    static String item_id;
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
        item_id = Integer.toString(movie_id);
        System.out.println(item_id);

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

        favorite = findViewById(R.id.iv_favor);
        ambilDatabase();
    }

    private void ambilDatabase() {
        new LoadNotflixAsync(this, favorlist -> {
            if (favorlist.size() == 0) {
                favorite.setOnClickListener(view -> {
                    ContentValues values = new ContentValues();
                    values.put(DatabaseContract.FavoriteColumns.BACKDROP, movie_backdrop);
                    values.put(DatabaseContract.FavoriteColumns.POSTER, movie_poster);
                    values.put(DatabaseContract.FavoriteColumns.NAME, movie_title);
                    values.put(DatabaseContract.FavoriteColumns.RATING, movie_vote_average);
                    values.put(DatabaseContract.FavoriteColumns.RELEASE_DATE, movie_release_date);
                    values.put(DatabaseContract.FavoriteColumns.OVERVIEW, movie_overview);


                    long result = ListHelper.insert(values);
                    if (result > 0) {
                        Toast.makeText(DetailActivity.this, "Berhasil Menambahkan Favorite", Toast.LENGTH_SHORT).show();
                        ambilDatabase();
                    } else {
                        Toast.makeText(DetailActivity.this, "Failed to add data", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                favorite.setOnClickListener(view -> deleteFavorit());
            }
        }).execute();
    }

    public void deleteFavorit() {
        long result = ListHelper.deleteById(String.valueOf(movie_id));
        if (result > 0) {
            Toast.makeText(this, "Berhasil Menghapus Favorite", Toast.LENGTH_SHORT).show();
            ambilDatabase();
        } else {
            Toast.makeText(this, "Failed to delete data", Toast.LENGTH_SHORT).show();
        }
    }

    private static class LoadNotflixAsync {
        private final WeakReference<Context> weakContext;
        private final WeakReference<DetailActivity.LoadNotflixCallback> weakCallback;

        private LoadNotflixAsync(Context context, DetailActivity.LoadNotflixCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        void execute() {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());
            executor.execute(() -> {
                Context context = weakContext.get();
                ListHelper listHelper = ListHelper.getInstance(context);
                listHelper.open();
                Cursor listCursor = ListHelper.queryById(item_id);
                ArrayList<List> favort = MappingHelper.mapCursorToArrayList(listCursor);
                handler.post(() -> weakCallback.get().postExecute(favort));
                System.out.println(favort.size());
            });
        }
    }

    interface LoadNotflixCallback {
        void postExecute(ArrayList<List> favorlist);
    }
}