package com.garciaericn.goodeats.search;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/7/14.
 */
public class SearchMapFragment extends MapFragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String LOG_TAG = "com.garciaericn.goodeats.search.SearchMapFragment";
    private GoogleMap mGoogleMap;
    private LocationManager mLocationManager;

    public SearchMapFragment() {

    }

    public static SearchMapFragment getInstance(int sectionNumber) {
        SearchMapFragment fragment = new SearchMapFragment();
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


            // TODO: Create custom marker adapter, and set to this
            // Set listeners
//            mGoogleMap.setOnInfoWindowClickListener(this);
//            mGoogleMap.setOnMapClickListener(this);
//            mGoogleMap.setOnMapLongClickListener(this);

            mLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

//            location = mGoogleMap.getMyLocation();
//            if (mLocation != null) {
//                LatLng latLng = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());
//                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
//            }
        }
    }
}
