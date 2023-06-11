package com.example.h071211030_finalmobile.SQLite;

import android.database.Cursor;

import java.util.ArrayList;

public class MappingHelper {public static ArrayList<List> mapCursorToArrayList(Cursor cursor) {
    ArrayList<List> favorite = new ArrayList<>();
    while (cursor.moveToNext()) {
        int id =
                cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns._ID));
        String backdrop =
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.BACKDROP));
        String poster =
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.POSTER));
        String name =
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.NAME));
        String rating =
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.RATING));
        String release_date =
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.RELEASE_DATE));
        String overview =
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.OVERVIEW));

        favorite.add(new List(id, backdrop, poster, name, rating, release_date, overview));
    }
    return favorite;
}

}
