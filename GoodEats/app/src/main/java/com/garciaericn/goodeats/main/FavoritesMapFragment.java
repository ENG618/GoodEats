package com.garciaericn.goodeats.main;

import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/2/14.
 */
public class FavoritesMapFragment extends MapFragment
        implements
        GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMapLongClickListener{

    private static final String ARG_SECTION_NUMBER = "section_number";
    private GoogleMap mGoogleMap;
    private Location location;

    public FavoritesMapFragment() {

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

        // TODO: Get arguments once updated

        if (savedInstanceState == null) {
            mGoogleMap = getMap();


            // TODO: Create custom marker adapter, and set to this
            // Set listeners
            mGoogleMap.setOnInfoWindowClickListener(this);
            mGoogleMap.setOnMapClickListener(this);
            mGoogleMap.setOnMapLongClickListener(this);

            location = mGoogleMap.getMyLocation();
            if (location != null) {
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
            }
        }

    }



    /**
     * Click listeners
     * */

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
}
