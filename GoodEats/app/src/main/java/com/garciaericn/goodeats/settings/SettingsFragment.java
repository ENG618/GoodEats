package com.garciaericn.goodeats.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.garciaericn.goodeats.R;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/11/14.
 */
public class SettingsFragment extends PreferenceFragment {

    public static final String FIRST_LAUNCH = "FIRST_LAUNCH";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);
    }

    /**
     * Set up any preferences onClicks
     * */
}
