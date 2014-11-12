package com.garciaericn.goodeats.data;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/2/14.
 */
public class Restaurant {

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
