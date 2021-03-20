package com.exercise3.EntitiesExerciseDTO.Exercise_3_DTO.Inventory;

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
