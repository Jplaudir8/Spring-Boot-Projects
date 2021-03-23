package com.exercise2.JPA.Exercise2;

import com.exercise2.JPA.Exercise2.Inventory.Plant;
import org.springframework.stereotype.Service;

@Service
public class PlantService {
    public Plant getPlantByName(String name) {
        return new Plant();
    }
}
