package com.example.h071211030_finalmobile.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.h071211030_finalmobile.R;
import com.example.h071211030_finalmobile.adapter.MovieAdapter;
import com.example.h071211030_finalmobile.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class fragment_movie extends Fragment {

    public fragment_movie() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }
}
