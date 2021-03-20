package com.exercise1.JPA.Exercise1.Inventory;

import javax.persistence.Entity;
import javax.persistence.Table;

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
