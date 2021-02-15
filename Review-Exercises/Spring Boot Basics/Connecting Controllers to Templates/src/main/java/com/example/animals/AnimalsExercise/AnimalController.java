package com.example.animals.AnimalsExercise;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AnimalController {

    private AnimalListService animalListService;

    public AnimalController(AnimalListService animalListService) {
        this.animalListService = animalListService;
    }

    @GetMapping("/animal")
    public String getAnimalPage(@ModelAttribute("newAnimal") AnimalForm newAnimal, Model model) {
        return "index";
    }

    @PostMapping("/animal")
    public String postAnimalPage(@ModelAttribute("newAnimal") AnimalForm newAnimal, Model model) {
        animalListService.addAnimal(newAnimal);
        model.addAttribute("animalsList", this.animalListService.getAnimals());
        return "index";
    }
}
