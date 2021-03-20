package com.exercise3.EntitiesExerciseDTO.Exercise_3_DTO;

import javax.persistence.Column;
import java.math.BigDecimal;

public class PlantDTO {
    private String name;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
