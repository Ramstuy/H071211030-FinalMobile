package com.example.h071211030_finalmobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h071211030_finalmobile.R;
import com.example.h071211030_finalmobile.models.Movie;
import com.example.h071211030_finalmobile.models.TvShows;

import java.util.List;

public class TvRatedAdapter extends RecyclerView.Adapter<TvRatedAdapter.ViewHolder>{

    List<TvShows> listRatedTv;
    public TvRatedAdapter(List<TvShows> list) {
        this.listRatedTv = list;
    }
    @NonNull
    @Override
    public TvRatedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TvShows modelMovie = listRatedTv.get(position);
        holder.title.setText(modelMovie.getName());
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500" + modelMovie.getBackdropPath()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return listRatedTv.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.iv_thumbnail);
            title = itemView.findViewById(R.id.tv_title);
        }
    }

}
