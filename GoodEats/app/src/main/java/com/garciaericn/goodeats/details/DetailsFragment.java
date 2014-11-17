package com.garciaericn.goodeats.details;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

    Restaurant restaurant;

    public DetailsFragment() {

    }

    public static DetailsFragment getInstance(Restaurant restaurant) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle b = new Bundle();
        b.putSerializable(Restaurant.RESTAURANT, restaurant);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null && getArguments().containsKey(Restaurant.RESTAURANT)) {
            restaurant = (Restaurant) getArguments().getSerializable(Restaurant.RESTAURANT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        return view;
    }
}
