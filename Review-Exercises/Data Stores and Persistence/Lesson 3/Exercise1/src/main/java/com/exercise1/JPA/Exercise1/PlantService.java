package com.exercise1.JPA.Exercise1;

import com.exercise1.JPA.Exercise1.Inventory.Plant;
import org.springframework.stereotype.Service;

@Service
public class PlantService {
    public Plant getPlantByName(String name) {
        return new Plant();
    }
}
