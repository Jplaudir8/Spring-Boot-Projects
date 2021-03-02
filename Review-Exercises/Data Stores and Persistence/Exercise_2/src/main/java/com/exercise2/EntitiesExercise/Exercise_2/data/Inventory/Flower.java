package com.exercise2.EntitiesExercise.Exercise_2.data.Inventory;

import javax.persistence.*;

@Entity
@Table(name = "Plant")
public class Flower extends Plant {

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
