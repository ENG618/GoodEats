package com.garciaericn.goodeats.data;

import java.io.Serializable;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/2/14.
 */
public class Restaurant implements Serializable {
    public static final long serialVersionUID = 2357817694738294783L;

    public static final String RESTAURANT = "RESTAURANT";

    // Object fields
    private String name;

    public Restaurant(){

    }

    public Restaurant(String name) {
        setName(name);
    }

    /**
     * Getter and Setter Methods*/
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
