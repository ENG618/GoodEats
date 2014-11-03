package com.garciaericn.goodeats.main;

import android.os.Bundle;

import com.google.android.gms.maps.MapFragment;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/2/14.
 */
public class FavoritesMapFragment extends MapFragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static FavoritesMapFragment newInstance(int sectionNumber) {
        FavoritesMapFragment fragment = new FavoritesMapFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
}
