package com.garciaericn.goodeats.settings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import com.garciaericn.goodeats.R;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/11/14.
 */
public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener{

    public static final String FIRST_LAUNCH = "FIRST_LAUNCH";
    private static final String REVOKE_TOKEN = "REVOKE_TOKEN";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);

        findPreference(FIRST_LAUNCH).setOnPreferenceClickListener(this);
        findPreference(REVOKE_TOKEN).setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        String s = preference.getKey();
        if (s.equals(FIRST_LAUNCH)) {
            Toast.makeText(getActivity(), "First launch clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (s.equals(REVOKE_TOKEN)) {
            Toast.makeText(getActivity(), "Delete account clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }

    /**
     * Set up any preferences onClicks
     * */
}
