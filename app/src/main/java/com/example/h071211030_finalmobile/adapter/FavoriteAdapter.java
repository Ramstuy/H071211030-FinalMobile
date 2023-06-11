package com.example.h071211030_finalmobile.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h071211030_finalmobile.R;
import com.example.h071211030_finalmobile.SQLite.FavoList;
import com.example.h071211030_finalmobile.activities.DetailActivity;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>{

    private final List<FavoList> favoListFavorite;
    private final ActivityResultLauncher<Intent> resultLauncher;
    public FavoriteAdapter(List<FavoList> favorite, ActivityResultLauncher<Intent> resultLauncher){
        this.favoListFavorite = favorite;
        this.resultLauncher = resultLauncher;
    }
    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie3, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
        FavoList favorites = favoListFavorite.get(position);
        holder.tvFavorite.setText(favorites.getName());
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500" + favorites.getPoster()).into(holder.ivFavorite);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("movie_backdrop", favorites.getBackdrop());
            intent.putExtra("movie_poster", favorites.getPoster());
            intent.putExtra("movie_id", favorites.getId());
            intent.putExtra("movie_title", favorites.getName());
            intent.putExtra("movie_vote_average", favorites.getRating());
            intent.putExtra("movie_release_date", favorites.getRelease_date());
            intent.putExtra("movie_or_tv", "Movie");
            intent.putExtra("movie_overview", favorites.getOverview());
            resultLauncher.launch(intent);
        });
    }

    @Override
    public int getItemCount() {
        return favoListFavorite.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivFavorite;
        TextView tvFavorite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivFavorite = itemView.findViewById(R.id.iv_favorite);
            tvFavorite = itemView.findViewById(R.id.tv_favorite);
        }
    }

}
