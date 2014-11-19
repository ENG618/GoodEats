package com.garciaericn.goodeats.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.garciaericn.goodeats.R;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/7/14.
 */
public class SearchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.search_map_container, SearchMapFragment.getInstance(), SearchMapFragment.TAG)
                .commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
