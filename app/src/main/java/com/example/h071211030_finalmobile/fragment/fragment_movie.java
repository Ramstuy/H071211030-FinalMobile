package com.example.h071211030_finalmobile.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.h071211030_finalmobile.API.ApiConfig;
import com.example.h071211030_finalmobile.R;
import com.example.h071211030_finalmobile.adapter.PosterAdapter;
import com.example.h071211030_finalmobile.adapter.RatedAdapter;
import com.example.h071211030_finalmobile.models.Movie;
import com.example.h071211030_finalmobile.models.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment_movie extends Fragment {
    RecyclerView rvPoster;
    ImageView ivVerPost;
    RecyclerView rvTop;
    ImageView ivBackdrop;
    TextView tvName;
    public fragment_movie() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvPoster = view.findViewById(R.id.rvPopPoster);
        ivVerPost = view.findViewById(R.id.iv_verticalpost);

        rvTop = view.findViewById(R.id.rvTopRated);
        ivBackdrop = view.findViewById(R.id.iv_thumbnail);
        tvName = view.findViewById(R.id.tv_title);

        rvPoster.setHasFixedSize(true);
        rvTop.setHasFixedSize(true);


        ApiConfig.getApiService().getPopularMovie().enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Movie> list = response.body().getResults();
                    PosterAdapter posterAdapter = new PosterAdapter(list);
                    rvPoster.setAdapter(posterAdapter);
                }
            }
            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                System.out.println("yahaha turu dek 1");
            }
        });

        ApiConfig.getApiService().getTopRatedMovie().enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Movie> list = response.body().getResults();
                    RatedAdapter ratedAdapter = new RatedAdapter(list);
                    rvTop.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                    rvTop.setAdapter(ratedAdapter);
                }
            }
            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                System.out.println("yahaha turu dek 2");
            }
        });
    }
}
