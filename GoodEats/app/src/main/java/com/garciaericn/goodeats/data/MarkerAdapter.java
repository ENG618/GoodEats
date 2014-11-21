package com.garciaericn.goodeats.data;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.garciaericn.goodeats.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/20/14.
 */
public class MarkerAdapter implements GoogleMap.InfoWindowAdapter {

    Context mContext;
    TextView mTextView;

    public MarkerAdapter() {
        if (mTextView == null) {
            mTextView = new TextView(mContext);
        }
    }

    public MarkerAdapter(Context context) {
        mContext = context;
        if (mTextView == null) {
            mTextView = new TextView(mContext);
        }
    }


    @Override
    public View getInfoWindow(Marker marker) {
        mTextView.setText(marker.getTitle());
        return mTextView;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = View.inflate(mContext, R.layout.marker_window, null);

        if (marker.getSnippet() != null) {
            ImageView iconIV = (ImageView) view.findViewById(R.id.window_image);
            iconIV.setImageURI(Uri.parse(marker.getSnippet()));
        }

        TextView titleTV = (TextView) view.findViewById(R.id.window_title);
        titleTV.setText(marker.getTitle());



        return null;
    }
}
