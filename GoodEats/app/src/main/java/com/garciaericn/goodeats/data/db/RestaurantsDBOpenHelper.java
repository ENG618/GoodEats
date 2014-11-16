package com.garciaericn.goodeats.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/14/14.
 */
public class RestaurantsDBOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = "com.garciaericn.goodeats.data.db.RestaurantsDBOpenHelper.TAG";

    private static final String DATABASE_NAME = "Restaurants.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_RESTAURANTS = "restaurants";
    public static final String COLUMN_ID = "restaurantID";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";

    public static final String CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_RESTAURANTS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    ")";

    public RestaurantsDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ENTRIES);
        Log.i(TAG, "Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXITS " + TABLE_RESTAURANTS);
        onCreate(db);
    }
}
