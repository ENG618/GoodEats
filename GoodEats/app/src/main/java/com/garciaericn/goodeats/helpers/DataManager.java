package com.garciaericn.goodeats.helpers;

import android.content.Context;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.garciaericn.goodeats.data.Restaurant;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/16/14.
 */
public class DataManager implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{
    private static final String TAG = "com.garciaericn.goodeats.helpers.DataManager.TAG";
    private static final String FILENAME = "Favorite Restaurants";
    private static DataManager mgr = new DataManager();
    private static Context mContext;
    private ArrayList<Restaurant> restaurants;
    private GoogleApiClient mGoogleApiClient;

    public static DataManager getInstance(Context context) {
        mContext = context;
        if (mgr == null) {
            mgr = new DataManager();
        }
        return mgr;
    }

    private DataManager() {
//        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
//                .addApi(Drive.API)
//                .addScope(Drive.SCOPE_FILE)
//                .addConnectionCallbacks(this)
//                .build();
    }

    public void startGoogleApiClient(){
        mGoogleApiClient.connect();
    }

    public Boolean checkFile(Context context) {
        Log.i(TAG, "checkFile entered");
        // Store data in "protected" directory
        File external = context.getExternalFilesDir(null);
        File file = new File(external, FILENAME);
        return file.exists();
    }

    public void writeToDisk(ArrayList<Restaurant> reviews) {
        Log.i(TAG, "writeToDisk entered");

        File external = mContext.getExternalFilesDir(null);
        File file = new File(external, FILENAME);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(reviews);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Restaurant> readFromDisk() {
        Log.i(TAG, "readFromFile entered");

        if (checkFile(mContext)) {
            File external = mContext.getExternalFilesDir(null);
            File file = new File(external, FILENAME);

            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                restaurants = (ArrayList<Restaurant>) objectInputStream.readObject();
                objectInputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (OptionalDataException e) {
                e.printStackTrace();
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return restaurants;
    }

    /**
     * GoogleApiConnection Callback Methods
     * */

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(mContext, "ConnectionsFailed: " + connectionResult.toString(), Toast.LENGTH_SHORT).show();
//        if (connectionResult.hasResolution()) {
//            try {
//                connectionResult.startResolutionForResult(mContext, );
//                connectionResult.startResolutionForResult(mContext, RESOLVE_CONNECTION_REQUEST_CODE);
//            } catch (IntentSender.SendIntentException e) {
//                // Unable to resolve, message user appropriately
//            }
//        } else {
//            GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(), this, 0).show();
//        }
    }
}
