package com.exercise3.EntitiesExerciseJSONView.Exercise_3_JSONView;

import com.exercise3.EntitiesExerciseJSONView.Exercise_3_JSONView.Inventory.Plant;
import org.springframework.stereotype.Service;

@Service
public class PlantService {
    public Plant getPlantByName(String name) {
        return new Plant();
    }
}
