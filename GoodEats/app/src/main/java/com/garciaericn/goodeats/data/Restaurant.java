package com.garciaericn.goodeats.data;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/2/14.
 */
public class Restaurant implements Serializable {
    public static final long serialVersionUID = 2357817694738294783L;

    public static final String RESTAURANT = "RESTAURANT";
    public static final int SAVE_CODE = 12345;

    // Object fields
    private String placeID;
    private String name;
    private String iconURL;
    private boolean openNow;
    private LatLng mLatLng;

    public Restaurant(){

    }

    public Restaurant(String id, String name, String iconURL) {
        setPlaceID(id);
        setName(name);
        setIconURL(iconURL);
    }

    public Restaurant(String id, String name, String iconURL, LatLng latLng) {
        setPlaceID(id);
        setName(name);
        setIconURL(iconURL);
        setmLatLng(latLng);
    }

    public Restaurant(String id, String name, String iconURL, boolean openNow) {
        setPlaceID(id);
        setName(name);
        setIconURL(iconURL);
        setOpenNow(openNow);
    }

    /**
     * Getter and Setter Methods
     * */

    public String getPlaceID() {
        return placeID;
    }

    public void setPlaceID(String placeID) {
        this.placeID = placeID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    public boolean isOpenNow() {
        return openNow;
    }

    public void setOpenNow(boolean openNow) {
        this.openNow = openNow;
    }

    public LatLng getmLatLng() {
        return mLatLng;
    }

    public void setmLatLng(LatLng mLatLng) {
        this.mLatLng = mLatLng;
    }
}
