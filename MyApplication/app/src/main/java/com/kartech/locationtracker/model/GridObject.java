package com.kartech.locationtracker.model;

/**
 * Created by kkokate on 17-03-2016.
 */


public class GridObject {

    private String name;
    private int photo;

    public GridObject(String name, int photo) {
        this.name = name;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}

