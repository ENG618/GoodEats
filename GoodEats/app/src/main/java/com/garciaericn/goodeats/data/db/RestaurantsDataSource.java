package com.garciaericn.goodeats.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/15/14.
 */
public class RestaurantsDataSource {

    public static final String TAG = "com.garciaericn.goodeats.data.db.RestaurantsDataSource.TAG";

    SQLiteOpenHelper dbhelper;
    SQLiteDatabase database;

    public RestaurantsDataSource(Context context) {
        dbhelper = new RestaurantsDBOpenHelper(context);
        database = dbhelper.getWritableDatabase();
    }

    public void open() {
        Log.i(TAG, "Database opened");
        database = dbhelper.getWritableDatabase();
    }

    public void close() {
        Log.i(TAG, "Database closed");
        dbhelper.close();
    }
}
