package com.garciaericn.goodeats.helpers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/22/14.
 */
public class CheckConnection {

    Context mContext;
    ConnectivityManager connectivityManager;

    public CheckConnection(Context context) {
        mContext = context;
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public boolean isConnected() {

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // Return true if connected
            return true;
        } else {
            // Display dialog for user to connect, with intent to wifi settings
            showConnectionFailedDialog();
        }

        // Return false by default
        return false;
    }

    private void showConnectionFailedDialog() {
        // TODO: Display dialog for user to connect, with intent to wifi settings
        new AlertDialog.Builder(mContext)
                .setTitle("No Network Connection")
                .setMessage("Please enable wifi data from.")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent settingsIntent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                        mContext.startActivity(settingsIntent);
                    }
                })
                .show();
    }
}
