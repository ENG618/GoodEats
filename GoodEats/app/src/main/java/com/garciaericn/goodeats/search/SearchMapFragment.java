package com.garciaericn.goodeats.search;

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

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/7/14.
 */
public class SearchMapFragment extends MapFragment implements LocationListener{

    public static final String TAG = "com.garciaericn.goodeats.search.SearchMapFragment";
    private static final int RC_ENABLE_GPS = 5553245;
    private GoogleMap mGoogleMap;
    private LocationManager mLocationManager;
    private LatLng mCurrentLocation;

    public SearchMapFragment() {

    }

    public static SearchMapFragment getInstance() {
        return new SearchMapFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated");

        if (savedInstanceState == null) {
            mGoogleMap = getMap();
            mGoogleMap.setMyLocationEnabled(true);


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

    @Override
    public void onResume() {
        super.onResume();

        enableGPS();
    }

    private void enableGPS() {
        if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 20000, 10, this);

            Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                mCurrentLocation = new LatLng(location.getLatitude(), location.getLongitude());
            }
        } else {
            new AlertDialog.Builder(getActivity())
                    .setTitle("Search works best with GPS")
                    .setMessage("Please enable GPS in system settings")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivityForResult(settingsIntent, RC_ENABLE_GPS);
                        }
                    })
                    .show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_ENABLE_GPS) {
            enableGPS();
        }
    }

    /**
     * Location Listener
     * */

    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = new LatLng(location.getLatitude(), location.getLongitude());
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
