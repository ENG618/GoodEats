package com.garciaericn.goodeats.data.db;

import android.provider.BaseColumns;

import com.garciaericn.goodeats.data.Restaurant;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/15/14.
 */
public class RestaurantContract {

    public RestaurantContract() {

    }

    public static abstract class Restaurant implements BaseColumns {
        public static final String TABLE_RESTAURANTS = "restaurants";
        public static final String COLUMN_ID = "restaurantID";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "description";
    }
}
