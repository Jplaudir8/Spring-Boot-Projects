package com.udacity.EntitiesExercise.data;

import javax.persistence.*;

@Entity
@Table(name = "plant")
public class Flower extends Plant {

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
