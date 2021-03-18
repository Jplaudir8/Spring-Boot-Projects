package com.exercise3.EntitiesExerciseJSONView.Exercise_3_JSONView;

import com.exercise3.EntitiesExerciseJSONView.Exercise_3_JSONView.Inventory.Plant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plant")
public class PlantController {

    @Autowired
    private PlantService plantService;

    @GetMapping
    public Plant getPlantDTO(String name) {
        Plant plant = plantService.getPlantByName(name);
        return plant;
    }

}
