package com.garciaericn.goodeats.details;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import com.garciaericn.goodeats.R;
import com.garciaericn.goodeats.data.Restaurant;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/16/14.
 */
public class DetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_details);

        if (savedInstanceState == null) {
            DetailsFragment detailsFragment = new DetailsFragment();
            Bundle b = getIntent().getBundleExtra(Restaurant.RESTAURANT);

            if (b != null && b.containsKey(Restaurant.RESTAURANT)) {
                detailsFragment.setArguments(b);
            }

            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.details_container, detailsFragment, DetailsFragment.TAG)
                    .commit();
        }
    }
}
