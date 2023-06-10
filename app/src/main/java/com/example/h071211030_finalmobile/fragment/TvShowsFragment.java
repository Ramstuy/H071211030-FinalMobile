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
import android.widget.TextView;

import com.example.h071211030_finalmobile.API.ApiConfig;
import com.example.h071211030_finalmobile.R;
import com.example.h071211030_finalmobile.adapter.PosterAdapter;
import com.example.h071211030_finalmobile.adapter.RatedAdapter;
import com.example.h071211030_finalmobile.adapter.TvPosterAdapter;
import com.example.h071211030_finalmobile.adapter.TvRatedAdapter;
import com.example.h071211030_finalmobile.models.Movie;
import com.example.h071211030_finalmobile.models.MovieResponse;
import com.example.h071211030_finalmobile.models.TvShows;
import com.example.h071211030_finalmobile.models.TvShowsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowsFragment extends Fragment {


    public TvShowsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv_shows, container, false);
    }
    RecyclerView rvPoster;
    ImageView ivVerPost;
    RecyclerView rvTop;
    ImageView ivBackdrop;
    TextView tvName;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvPoster = view.findViewById(R.id.rvPopPosterTS);
        ivVerPost = view.findViewById(R.id.iv_verticalpost);

        rvTop = view.findViewById(R.id.rvTopRatedTS);
        ivBackdrop = view.findViewById(R.id.iv_thumbnail);
        tvName = view.findViewById(R.id.tv_title);

        rvPoster.setHasFixedSize(true);
        rvTop.setHasFixedSize(true);
        ApiConfig.getApiService().getPopularTv().enqueue(new Callback<TvShowsResponse>() {
            @Override
            public void onResponse(Call<TvShowsResponse> call, Response<TvShowsResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<TvShows> list = response.body().getResults();
                    TvPosterAdapter posterAdapter = new TvPosterAdapter(list);
                    rvPoster.setAdapter(posterAdapter);
                }

            }

            @Override
            public void onFailure(Call<TvShowsResponse> call, Throwable t) {
                System.out.println("yahaha turu dek");
            }
        });

        ApiConfig.getApiService().getTopRatedTv().enqueue(new Callback<TvShowsResponse>() {
            @Override
            public void onResponse(Call<TvShowsResponse> call, Response<TvShowsResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<TvShows> list = response.body().getResults();
                    TvRatedAdapter ratedAdapter = new TvRatedAdapter(list);
                    rvTop.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                    rvTop.setAdapter(ratedAdapter);
                }
            }
            @Override
            public void onFailure(Call<TvShowsResponse> call, Throwable t) {
                System.out.println("yahaha turu dek");
            }
        });
    }
}