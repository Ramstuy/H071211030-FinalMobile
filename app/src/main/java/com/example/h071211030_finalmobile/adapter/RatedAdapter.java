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

import java.util.List;

public class RatedAdapter extends RecyclerView.Adapter<RatedAdapter.ViewHolder>{

    List<Movie> listRated;
    public RatedAdapter(List<Movie> list) {
        this.listRated = list;
    }
    @NonNull
    @Override
    public RatedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RatedAdapter.ViewHolder holder, int position) {
        Movie modelMovie = listRated.get(position);
        holder.title.setText(modelMovie.getTitle());
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500" + modelMovie.getBackdropPath()).into(holder.thumbnail);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("movie_backdrop", modelMovie.getBackdropPath());
            intent.putExtra("movie_poster", modelMovie.getPosterPath());
            intent.putExtra("movie_id", modelMovie.getId());
            intent.putExtra("movie_title", modelMovie.getTitle());
            intent.putExtra("movie_vote_average", modelMovie.getVoteAverage());
            intent.putExtra("movie_release_date", modelMovie.getReleaseDate());
            intent.putExtra("movie_or_tv", "Movie");
            intent.putExtra("movie_overview", modelMovie.getOverview());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listRated.size();
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