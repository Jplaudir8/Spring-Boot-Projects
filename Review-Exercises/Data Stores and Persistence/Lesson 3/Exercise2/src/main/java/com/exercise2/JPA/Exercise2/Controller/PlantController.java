package com.exercise2.JPA.Exercise2.Controller;

import com.exercise2.JPA.Exercise2.Inventory.Plant;
import com.exercise2.JPA.Exercise2.PlantDTO;
import com.exercise2.JPA.Exercise2.Service.PlantService;
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
    public PlantDTO getPlantDTO(String name) {
        Plant plant = plantService.getPlantByName(name);
        return convertEntityToPlantDTO(plant);
    }

    private static PlantDTO convertEntityToPlantDTO(Plant plant) {
        PlantDTO plantDTO = new PlantDTO();
        BeanUtils.copyProperties(plant, plantDTO);
        return plantDTO;
    }


}
