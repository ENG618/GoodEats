package com.garciaericn.goodeats.details;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.garciaericn.goodeats.R;
import com.garciaericn.goodeats.data.Restaurant;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/16/14.
 */
public class DetailsFragment extends Fragment {

    public static final String TAG = "com.garciaericn.goodeats.details.DetailsFragment.TAG";

    Restaurant restaurant;

    public DetailsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        Bundle b = getArguments();
        if (b != null && b.containsKey(Restaurant.RESTAURANT)) {
            restaurant = (Restaurant) b.getSerializable(Restaurant.RESTAURANT);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null && getArguments().containsKey(Restaurant.RESTAURANT)) {
            restaurant = (Restaurant) getArguments().getSerializable(Restaurant.RESTAURANT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        return view;
    }
}
