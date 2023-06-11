package com.example.h071211030_finalmobile.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h071211030_finalmobile.R;
import com.example.h071211030_finalmobile.activities.DetailActivity;
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
        holder.rating.setText(String.valueOf(modelMovie.getVoteAverage()));
        holder.title.setText(modelMovie.getName());
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500" + modelMovie.getBackdropPath()).into(holder.thumbnail);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("movie_backdrop", modelMovie.getBackdropPath());
            intent.putExtra("movie_poster", modelMovie.getPosterPath());
            intent.putExtra("movie_id", modelMovie.getId());
            intent.putExtra("movie_title", modelMovie.getName());
            intent.putExtra("movie_vote_average", modelMovie.getVoteAverage());
            intent.putExtra("movie_release_date", modelMovie.getFirstAirDate());
            intent.putExtra("movie_or_tv", "Tv Shows");
            intent.putExtra("movie_overview", modelMovie.getOverview());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listRatedTv.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;
        TextView title, rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.iv_thumbnail);
            title = itemView.findViewById(R.id.tv_title);
            rating = itemView.findViewById(R.id.tvRating);
        }
    }

}
