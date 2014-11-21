package com.garciaericn.goodeats.main;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.garciaericn.goodeats.R;
import com.garciaericn.goodeats.data.DataManager;
import com.garciaericn.goodeats.data.Restaurant;
import com.garciaericn.goodeats.login.LoginFragment;
import com.garciaericn.goodeats.search.SearchActivity;
import com.garciaericn.goodeats.settings.SettingsActivity;
import com.garciaericn.goodeats.settings.SettingsFragment;
import com.google.android.gms.maps.model.LatLng;

public class FavoritesActivity extends Activity implements ActionBar.TabListener {

    private static final int ADD_REQUEST = 112342;
    private static final String TAG = "com.garciaericn.goodeats.main.FavoritesActivity.TAG";
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    private ArrayList<Restaurant> restaurantArrayList;
    private DataManager mgr;
    private SharedPreferences settings;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }

        mgr = DataManager.getInstance(this);
        settings = PreferenceManager.getDefaultSharedPreferences(this);

        if (settings.getBoolean(SettingsFragment.FIRST_LAUNCH, true) && mgr != null) {
            loadDefaultData();
            mgr.writeToDisk(restaurantArrayList);
        } else {
            if (mgr.checkFile(this)) {
                restaurantArrayList = mgr.readFromDisk();
//                Log.i(TAG, restaurantArrayList.toString());
            }
        }
    }

    private void loadDefaultData() {
        if (restaurantArrayList == null) {
            restaurantArrayList = new ArrayList<Restaurant>();
        }

        restaurantArrayList.add(new Restaurant("STRING_ID", "Outback Steakhouse", "ICON_URL", new LatLng(28.647407, -81.266505)));
        restaurantArrayList.add(new Restaurant("STRING_ID", "Olive Garden", "ICON_URL", new LatLng(28.661457, -81.394852)));
        restaurantArrayList.add(new Restaurant("STRING_ID", "Flemings", "ICON_URL", new LatLng(28.605882, -81.365522)));
        restaurantArrayList.add(new Restaurant("STRING_ID", "Cate TuTu Tango", "ICON_URL", new LatLng(28.440639, -81.469897)));
        restaurantArrayList.add(new Restaurant("STRING_ID", "Longhorn", "ICON_URL", new LatLng(28.665178, -81.389495)));

        // Update settings
        settings.edit()
                .putBoolean(SettingsFragment.FIRST_LAUNCH, false)
                .apply();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.favorites, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_add:
                Intent addIntent = new Intent(this, SearchActivity.class);
                startActivityForResult(addIntent, ADD_REQUEST);
                return true;
            case R.id.action_sign_out:
                Toast.makeText(this, "Sign out", Toast.LENGTH_SHORT).show();

                Intent signOutIntent = new Intent();
                setResult(LoginFragment.RC_SIGN_OUT, signOutIntent);
                finish();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // Return fragment for tab selected
            switch (position) {
                case 0:
                    return FavoritesListFragment.getInstance();
                case 1:
                    return FavoritesMapFragment.newInstance(position + 1);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
            }
            return null;
        }
    }
}
