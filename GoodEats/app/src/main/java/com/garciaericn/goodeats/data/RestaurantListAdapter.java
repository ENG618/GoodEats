package com.garciaericn.goodeats.data;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.garciaericn.goodeats.R;

import java.util.List;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/12/14.
 */
public class RestaurantListAdapter extends ArrayAdapter<Restaurant> {

    private final Context context;
    private final List<Restaurant> restaurantList;

    public RestaurantListAdapter(Context context, int resource, List<Restaurant> restaurantList) {
        super(context, resource, restaurantList);
        this.context = context;
        this.restaurantList = restaurantList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.restaurante_list_item, null);

        Restaurant restaurant = restaurantList.get(position);

        TextView nameTV = (TextView) view.findViewById(R.id.restaurant_name);
        nameTV.setText(restaurant.getName());

        return view;
    }
}
