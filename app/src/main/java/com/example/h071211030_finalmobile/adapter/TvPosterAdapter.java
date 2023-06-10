package com.example.h071211030_finalmobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h071211030_finalmobile.R;
import com.example.h071211030_finalmobile.models.Movie;
import com.example.h071211030_finalmobile.models.TvShows;

import java.util.List;

public class TvPosterAdapter extends RecyclerView.Adapter<TvPosterAdapter.ViewHolder> {

    List<TvShows> listPosterTv;
    public TvPosterAdapter(List<TvShows> list) {
        this.listPosterTv = list;
    }

    @NonNull
    @Override
    public TvPosterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie1, parent, false);
        return new TvPosterAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvPosterAdapter.ViewHolder holder, int position) {
        TvShows modelMovie = listPosterTv.get(position);
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500" + modelMovie.getPosterPath()).into(holder.popPoster);
    }

    @Override
    public int getItemCount() {
        return listPosterTv.size();


    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView popPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            popPoster = itemView.findViewById(R.id.iv_verticalpost);
        }
    }
}
