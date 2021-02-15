package com.example.animals.AnimalsExercise;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Service
public class AnimalListService {
    ArrayList<AnimalForm> animals;

    @PostConstruct
    public void postConstruct() {
        this.animals = new ArrayList<>();
    }

    public void addAnimal(AnimalForm animal) {
        System.out.println("Added " + animal + " to your animals list.");
        this.animals.add(animal);
    }

    public ArrayList<AnimalForm> getAnimals() {
        return new ArrayList<>(animals);
    }
}
