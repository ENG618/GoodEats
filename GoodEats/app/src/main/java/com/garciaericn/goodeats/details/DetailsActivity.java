package com.garciaericn.goodeats.details;

import android.app.Activity;
import android.os.Bundle;

import com.garciaericn.goodeats.R;
import com.garciaericn.goodeats.data.Restaurant;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/16/14.
 */
public class DetailsActivity extends Activity implements DetailsFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_details);

        if (savedInstanceState == null) {
            DetailsFragment detailsFragment = new DetailsFragment();
            Bundle b = getIntent().getExtras();

            if (b != null && b.containsKey(Restaurant.RESTAURANT)) {
                detailsFragment.setArguments(b);
            }

            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.details_container, detailsFragment, DetailsFragment.TAG)
                    .commit();
        }
    }

    @Override
    public void setHomeAsUp() {
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
