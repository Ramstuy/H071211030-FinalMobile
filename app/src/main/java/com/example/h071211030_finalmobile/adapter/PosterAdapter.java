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

import java.util.List;

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.ViewHolder>{

    List<Movie> listPoster;
    public PosterAdapter(List<Movie> list) {
        this.listPoster = list;
    }
    @NonNull
    @Override
    public PosterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PosterAdapter.ViewHolder holder, int position) {
        Movie modelMovie = listPoster.get(position);
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500" + modelMovie.getPosterPath()).into(holder.popPoster);

    }

    @Override
    public int getItemCount() {
        return listPoster.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView popPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            popPoster = itemView.findViewById(R.id.iv_verticalpost);

        }
    }

}