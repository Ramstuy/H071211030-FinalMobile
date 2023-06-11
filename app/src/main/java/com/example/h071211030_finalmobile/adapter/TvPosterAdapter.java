package com.example.h071211030_finalmobile.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h071211030_finalmobile.R;
import com.example.h071211030_finalmobile.activities.DetailActivity;
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

        holder.popPoster.setOnClickListener(v -> {
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
