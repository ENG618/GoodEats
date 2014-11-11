package com.garciaericn.goodeats.login;

import android.app.Fragment;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.garciaericn.goodeats.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/10/14.
 */
public class LoginFragment extends Fragment implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    public static final String TAG = "com.garciaericn.goodeats.login.LoginFragment.TAG";

    /* Request code used to invoke sign in user interactions. */
    private static final int RC_SIGN_IN = 0;
    /* Client used to interact with Google APIs. */
    private GoogleApiClient mGoogleApiClient;
    /* A flag indicating that a PendingIntent is in progress and prevents
     * us from starting further intents.
     */
    private boolean mIntentInProgress;

    public LoginFragment() {

    }

    public static LoginFragment getInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        SignInButton signInButton = (SignInButton) view.findViewById(R.id.g_plus_login);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.g_plus_login:
                Toast.makeText(getActivity(), "Sign in now", Toast.LENGTH_SHORT).show();
                // TODO: Start login process here
                break;
            default:
                // If default action is needed.
                break;
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        // Connection errors resolved
        // Can now access Google APIs on behalf of the user
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (!mIntentInProgress && connectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                getActivity().startIntentSenderForResult(connectionResult.getResolution().getIntentSender(), RC_SIGN_IN, null, 0, 0, 0);


            } catch (IntentSender.SendIntentException e) {
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            mIntentInProgress = false;

            if (!mGoogleApiClient.isConnected()) {
                mGoogleApiClient.connect();
            }
        }
    }
}
