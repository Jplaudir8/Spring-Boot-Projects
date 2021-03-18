package com.exercise3.EntitiesExerciseDTO.Exercise_3_DTO;

import com.exercise3.EntitiesExerciseDTO.Exercise_3_DTO.Inventory.Plant;
import org.springframework.stereotype.Service;

@Service
public class PlantService {
    public Plant getPlantByName(String name) {
        return new Plant();
    }
}
