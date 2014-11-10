package com.garciaericn.goodeats.login;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/10/14.
 */
public class LoginActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /*
        * Connection Callbacks
        * */
    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    /*
    * On Connections Failed Listener
    * */
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
