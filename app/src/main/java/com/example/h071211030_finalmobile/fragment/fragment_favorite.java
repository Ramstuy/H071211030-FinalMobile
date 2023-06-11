package com.example.h071211030_finalmobile.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.h071211030_finalmobile.R;
import com.example.h071211030_finalmobile.SQLite.FavoList;
import com.example.h071211030_finalmobile.SQLite.ListHelper;
import com.example.h071211030_finalmobile.SQLite.MappingHelper;
import com.example.h071211030_finalmobile.adapter.FavoriteAdapter;

import java.lang.ref.WeakReference;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class fragment_favorite extends Fragment {

    RecyclerView favorite;

    FavoriteAdapter favoriteAdapter;

    public fragment_favorite() {
    }
    private final ActivityResultLauncher<Intent> resultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        loadList();
                    });
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        favorite = view.findViewById(R.id.rvMovieFav);
        favorite.setHasFixedSize(true);

        loadList();
    }
    private void loadList (){
        new LoadFavoriteAsync(getContext(), favorites -> {
            if (favorites.size() > 0) {
                favorite.setLayoutManager(new LinearLayoutManager(getContext()));
                favoriteAdapter = new FavoriteAdapter(favorites, resultLauncher);
                favorite.setAdapter(favoriteAdapter);
            } else {
                favoriteAdapter = new FavoriteAdapter(favorites, resultLauncher);
                favorite.setAdapter(favoriteAdapter);
            }

        }).execute();
    }
    private static class LoadFavoriteAsync {
        WeakReference <Context> weakContext;
        WeakReference <LoadFavoriteCallback> weakCallback;

        private LoadFavoriteAsync(Context context, LoadFavoriteCallback callback) {
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
                Cursor listCursor = listHelper.queryAll();
                ArrayList<FavoList> favor =
                        MappingHelper.mapCursorToArrayList(listCursor);
                handler.post(() -> weakCallback.get().postExecute(favor));
            });
        }
    }

    interface LoadFavoriteCallback {
        void postExecute(ArrayList<FavoList> favor);
    }

}