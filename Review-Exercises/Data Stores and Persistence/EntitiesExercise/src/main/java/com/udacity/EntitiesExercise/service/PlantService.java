package com.udacity.EntitiesExercise.service;

import com.udacity.EntitiesExercise.data.Plant;
import org.springframework.stereotype.Service;

@Service
public class PlantService {
    public Plant getPlantByName(String name) {
        return new Plant();
    }
}
