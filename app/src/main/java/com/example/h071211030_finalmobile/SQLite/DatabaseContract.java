package com.example.h071211030_finalmobile.SQLite;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static String TABLE_NAME = "favorite";
    public static final class FavoriteColumns implements BaseColumns {
        public static String BACKDROP = "backdrop";
        public static String POSTER = "poster";
        public static String NAME = "name";
        public static String RATING = "rating";
        public static String RELEASE_DATE = "release_date";
        public static String OVERVIEW = "overview";
    }
}
