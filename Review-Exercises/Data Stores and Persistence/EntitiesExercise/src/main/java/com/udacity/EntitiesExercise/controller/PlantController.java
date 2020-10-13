package com.udacity.EntitiesExercise.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.udacity.EntitiesExercise.Views;
import com.udacity.EntitiesExercise.data.Plant;
import com.udacity.EntitiesExercise.service.PlantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/map")
public class PlantController {

    @Autowired
    private PlantService plantService;

    public PlantDTO getPlantDTO(String name) {
        Plant plant = plantService.getPlantByName(name);
        return convertEntityToPlantDTO(plant);
    }

    // The other alternative is using JsonViews, which will involve adding annotations to Plant.java
    @JsonView(Views.Public.class)
    public Plant getFilteredPlant(String name) {
        return plantService.getPlantByName(name);
    }

    private static PlantDTO convertEntityToPlantDTO(Plant plant) {
        PlantDTO plantDTO = new PlantDTO();
        BeanUtils.copyProperties(plant, plantDTO);
        return plantDTO;
    }

    /*private static Plant convertPlantDTOtoEntity (PlantDTO plantDTO) {
        Plant plant = new Plant();
        BeanUtils.copyProperties(plantDTO, plant);
        return plant;
    }*/

}
