package com.garciaericn.goodeats.search;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.Filterable;

import com.garciaericn.goodeats.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/7/14.
 */
public class SearchMapFragment extends MapFragment implements LocationListener{

    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String OUT_JSON = "/json";
    private static final String API_KEY = "AIzaSyCnTiA61PWedSWuinMpWgeeTrSBiaWHg9I";

    public static final String TAG = "com.garciaericn.goodeats.search.SearchMapFragment";
    private static final int RC_ENABLE_GPS = 5553245;
    private GoogleMap mGoogleMap;
    private LocationManager mLocationManager;
    private LatLng mCurrentLocation;
    private String placesSearchURL;

    public interface SearchFragmentCallbacks {
        public void searchPlaces(String searchUrlString);
    }

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


    /**
     * Auto complete methods
     * */

     private ArrayList<String> autocomplete(String input) {
         ArrayList<String> resultList = null;

         HttpURLConnection conn = null;
         StringBuilder jsonResults = new StringBuilder();
         try {
             StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON);
             sb.append("?key=" + API_KEY);
             sb.append("&components=country:uk");
             sb.append("&input=" + URLEncoder.encode(input, "utf8"));

             URL url = new URL(sb.toString());
             conn = (HttpURLConnection) url.openConnection();
             InputStreamReader in = new InputStreamReader(conn.getInputStream());

             // Load the results into a StringBuilder
             int read;
             char[] buff = new char[1024];
             while ((read = in.read(buff)) != -1) {
                 jsonResults.append(buff, 0, read);
             }
         } catch (MalformedURLException e) {
             Log.e(TAG, "Error processing Places API URL", e);
             return resultList;
         } catch (IOException e) {
             Log.e(TAG, "Error connecting to Places API", e);
             return resultList;
         } finally {
             if (conn != null) {
                 conn.disconnect();
             }
         }

         try {
             // Create a JSON object hierarchy from the results
             JSONObject jsonObj = new JSONObject(jsonResults.toString());
             JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

             // Extract the Place descriptions from the results
             resultList = new ArrayList<String>(predsJsonArray.length());
             for (int i = 0; i < predsJsonArray.length(); i++) {
                 resultList.add(predsJsonArray.getJSONObject(i).getString("description"));
             }
         } catch (JSONException e) {
             Log.e(TAG, "Cannot process JSON results", e);
         }

         return resultList;
     }








    // Fetch URL
    /*private static String getResponse(URL url) {
        // Log message
        Log.i(TAG, "getResponse entered");

        String response;
        try {
            //noinspection UnusedAssignment
            response = null;
            URLConnection conn = url.openConnection();
            BufferedInputStream bin = new BufferedInputStream(conn.getInputStream());
            byte[] contextByte = new byte[1024];
            //noinspection UnusedAssignment
            int byteRead = 0;
            //StringBuffer was producing an error so I switched it to String Builder
            StringBuilder responseBuilder = new StringBuilder();


            while ((byteRead = bin.read(contextByte)) != -1) {
                response = new String(contextByte, 0, byteRead);
                responseBuilder.append(response);
            }
            response = responseBuilder.toString();
            Log.i(TAG, "URL Response: " + response);
        } catch (IOException e) {
            response = "Something isn't right.  We didn't receive a response";
            Log.e(TAG, "Something went wrong", e);
        }

        return response;
    }*/

    // Obtain data from api
    /*private class getData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            // Declare local variable response string
            String responseString;

            try { // Try URL
                URL url = new URL(placesSearchURL);
                responseString = getResponse(url);
            } catch (Exception e) { // If error show response string and error
                responseString = "Something isn't right";
                Log.e(TAG, "ERROR: ", e);
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.i(TAG, "onPostExecute entered");
            Log.i(TAG, "Post Execute String: " + s);

            // Parse and send to fragments

            super.onPostExecute(s);
        }
    }*/


    private class PlacesAutoCompleteAdapter extends ArrayAdapter<String> implements Filterable {
        private ArrayList<String> resultList;

        public PlacesAutoCompleteAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        @Override
        public int getCount() {
            return resultList.size();
        }

        @Override
        public String getItem(int index) {
            return resultList.get(index);
        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults filterResults = new FilterResults();
                    if (constraint != null) {
                        // Retrieve the autocomplete results.
                        resultList = autocomplete(constraint.toString());

                        // Assign the data to the FilterResults
                        filterResults.values = resultList;
                        filterResults.count = resultList.size();
                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
                    if (results != null && results.count > 0) {
                        notifyDataSetChanged();
                    }
                    else {
                        notifyDataSetInvalidated();
                    }
                }};
            return filter;
        }
    }
}
