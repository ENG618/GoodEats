package com.garciaericn.goodeats.details;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.garciaericn.goodeats.R;
import com.garciaericn.goodeats.data.Restaurant;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/16/14.
 */
public class DetailsFragment extends Fragment {

    public static final String TAG = "com.garciaericn.goodeats.details.DetailsFragment.TAG";

    private Restaurant restaurant;
    private OnFragmentInteractionListener mListener;

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
        mListener.setHomeAsUp();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        if (restaurant != null) {
            TextView titleTV = (TextView) view.findViewById(R.id.restaurant_name);
            titleTV.setText(restaurant.getName());
        }

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().setResult(Activity.RESULT_CANCELED);
                getActivity().finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * DetailsFragment Interface
     */
    public interface OnFragmentInteractionListener {
        public void setHomeAsUp();
    }
 }
