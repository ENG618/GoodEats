package com.garciaericn.goodeats.login;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.garciaericn.goodeats.R;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/11/14.
 */
public class SplashScreenFragment extends Fragment {

    public static final String TAG = "com.garciaericn.goodeats.login.SplashScreenFragment.TAG";

    public SplashScreenFragment() {

    }

    public static SplashScreenFragment getInstance() {
        return new SplashScreenFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }
}
