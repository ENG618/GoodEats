package com.garciaericn.goodeats.main;


import android.app.ListFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.garciaericn.goodeats.R;
import com.garciaericn.goodeats.data.DataManager;
import com.garciaericn.goodeats.data.Restaurant;
import com.garciaericn.goodeats.data.RestaurantListAdapter;
import com.garciaericn.goodeats.data.db.RestaurantsDataSource;
import com.garciaericn.goodeats.settings.SettingsFragment;

import java.util.ArrayList;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/8/14.
 */
public class FavoritesListFragment extends ListFragment {

    private static final String TAG = "com.garciaericn.goodeats.main.FavoritesListFragment.TAG";
    private static final int DETAIL_VIEW = 33452246;
    private static ArrayList<Restaurant> restaurants;

    private DataManager mgr;
    private SharedPreferences settings;
    private RestaurantListAdapter adapter;

    RestaurantsDataSource dataSource;

    public FavoritesListFragment() {

    }

    public static FavoritesListFragment getInstance() {
        return new FavoritesListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Un-bundle arguments from fragment using getArguments()

        mgr = DataManager.getInstance(getActivity());
        settings = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if (settings.getBoolean(SettingsFragment.FIRST_LAUNCH, true) && mgr != null) {
            loadDefaultData();
            mgr.writeToDisk(restaurants);
        } else {
            if (mgr.checkFile(getActivity())) {
                restaurants = mgr.readFromDisk();
                Log.i(TAG, restaurants.toString());
            }
        }

        // TODO: set up database
        //dataSource = new RestaurantsDataSource(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        //dataSource.close();
    }

    @Override
    public void onResume() {
        super.onResume();
        //dataSource.open();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (restaurants != null) {
            adapter = new RestaurantListAdapter(getActivity(), R.layout.restaurante_list_item, restaurants);
            setListAdapter(adapter);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Restaurant selectedRestaurant = restaurants.get(position);

        Intent detailsIntent = new Intent(getActivity(), );
        detailsIntent.putExtra(Restaurant.RESTAURANT, selectedRestaurant);
        startActivityForResult(detailsIntent, DETAIL_VIEW);
    }

    private void loadDefaultData() {
        if (restaurants == null) {
            restaurants = new ArrayList<Restaurant>();
        }

        restaurants.add(new Restaurant("Outback Steakhouse"));
        restaurants.add(new Restaurant("Olive Garden"));
        restaurants.add(new Restaurant("Flemings"));
        restaurants.add(new Restaurant("Cate TuTu Tango"));
        restaurants.add(new Restaurant("Longhorn"));

        // Update settings
        settings.edit()
                .putBoolean(SettingsFragment.FIRST_LAUNCH, false)
                .apply();
    }
}
