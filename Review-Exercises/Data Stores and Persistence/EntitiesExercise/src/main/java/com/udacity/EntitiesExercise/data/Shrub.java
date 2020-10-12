package com.udacity.EntitiesExercise.data;

import javax.persistence.Entity;

@Entity
public class Shrub extends Plant {

    private String height;
    private String width;

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }
}
