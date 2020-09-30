package com.udacity.DogRestAPI.service;

import com.udacity.DogRestAPI.entity.Dog;

import java.util.List;

public interface DogService {
    List<Dog> getAllDogs();
    List<String> getAllBreeds();
    String getBreedById(long id);
    List<String> getAllDogNames();
}
