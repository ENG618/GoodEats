package com.garciaericn.goodeats.settings;

import android.app.Activity;
import android.os.Bundle;
import com.garciaericn.goodeats.R;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/11/14.
 */
public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container, new SettingsFragment())
                .commit();
    }
}
