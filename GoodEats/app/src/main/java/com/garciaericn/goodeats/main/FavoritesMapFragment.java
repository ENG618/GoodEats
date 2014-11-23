package com.garciaericn.goodeats.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.garciaericn.goodeats.data.DataManager;
import com.garciaericn.goodeats.data.MarkerAdapter;
import com.garciaericn.goodeats.data.Restaurant;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/2/14.
 */
public class FavoritesMapFragment extends MapFragment
        implements
        GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMapLongClickListener,
        LocationListener {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final int REQUEST_ENABLE_GPS = 0x52446;
    private static final String LOG_TAG = "com.garciaericn.goodeats.main.FavoritesMapFragment";
    private GoogleMap mGoogleMap;
    private LocationManager mLocationManager;
    private Location mLocation;
    private DataManager dataManager;

    // Temporary ArrayList
    ArrayList<Restaurant> restaurantArrayList;

    public FavoritesMapFragment() {
        if (restaurantArrayList == null) {
            restaurantArrayList = new ArrayList<Restaurant>();
        }
    }

    public static FavoritesMapFragment newInstance(int sectionNumber) {
        FavoritesMapFragment fragment = new FavoritesMapFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(LOG_TAG, "onActivityCreated");

        // TODO: Get arguments once updated

        if (savedInstanceState == null) {
            mGoogleMap = getMap();


            // Set custom marker adapter to this
            mGoogleMap.setInfoWindowAdapter(new MarkerAdapter(getActivity()));
            // Set listeners
            mGoogleMap.setOnInfoWindowClickListener(this);
            mGoogleMap.setOnMapClickListener(this);
            mGoogleMap.setOnMapLongClickListener(this);
            // Enable my location
            mGoogleMap.setMyLocationEnabled(true);

            mLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            enableGps();

            if (mLocation != null) {
                LatLng latLng = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());
                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
            }

            // Load saved restaurants
            if (restaurantArrayList.isEmpty()) {
                dataManager = DataManager.getInstance(getActivity());
                if (dataManager.checkFile(getActivity())) {
                    restaurantArrayList = dataManager.readFromDisk();
                }
            }

            if (restaurantArrayList != null && restaurantArrayList.size() > 0) {
                for (Restaurant restaurant : restaurantArrayList) {
                    mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(restaurant.getLat(), restaurant.getLng())).title(restaurant.getName()));
                }
            }
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "onResume");
        enableGps();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "onPause");
        mLocationManager.removeUpdates(this);
    }

    private void enableGps() {
        Log.i(LOG_TAG, "enableGps");
        if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);

            Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                mLocation = location;
                Toast.makeText(getActivity(), location.toString(), Toast.LENGTH_SHORT);
            }
        } else {
            new AlertDialog.Builder(getActivity())
                    .setTitle("GPS is unavailable")
                    .setMessage("Please enable GPS")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivityForResult(settingsIntent, REQUEST_ENABLE_GPS);
                        }
                    })
                    .show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        enableGps();
    }

    /**
     * Click listeners
     */

    @Override
    public void onInfoWindowClick(Marker marker) {
        // TODO: Open details page for selected marker
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        // TODO: Add new custom pin at LatLng
    }

    /**
     * Location Listener
     */

    @Override
    public void onLocationChanged(Location location) {
        mLocation = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
