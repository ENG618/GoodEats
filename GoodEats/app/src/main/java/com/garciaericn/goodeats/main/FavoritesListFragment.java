package com.garciaericn.goodeats.main;


import android.app.ListFragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

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

    private ArrayList<Restaurant> resaurants;

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
            mgr.writeToDisk(resaurants);
        } else {
            if (mgr.checkFile(getActivity())) {
                resaurants = mgr.readFromDisk();
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

        if (resaurants != null) {
            adapter = new RestaurantListAdapter(getActivity(), R.layout.restaurante_list_item, resaurants);
            setListAdapter(adapter);
        }
    }

    private void loadDefaultData() {
        if (resaurants == null) {
            resaurants = new ArrayList<Restaurant>();
        }

        resaurants.add(new Restaurant("Outback Steakhouse"));
        resaurants.add(new Restaurant("Olive Garden"));
        resaurants.add(new Restaurant("Flemings"));
        resaurants.add(new Restaurant("Cate TuTu Tango"));
        resaurants.add(new Restaurant("Longhorn"));

        // Update settings
        settings.edit()
                .putBoolean(SettingsFragment.FIRST_LAUNCH, false)
                .apply();
    }
}
