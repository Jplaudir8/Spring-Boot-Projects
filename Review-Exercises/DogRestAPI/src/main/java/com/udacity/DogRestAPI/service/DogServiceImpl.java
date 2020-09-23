package com.udacity.DogRestAPI.service;

import com.udacity.DogRestAPI.entity.Dog;
import com.udacity.DogRestAPI.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogServiceImpl implements DogService {

    @Autowired
    DogRepository dogRepository;

    @Override
    public List<Dog> getAllDogs() {
        return (List<Dog>) dogRepository.findAll();
    }

    @Override
    public List<String> getAllBreeds() {
        return dogRepository.getAllBreeds();
    }

    @Override
    public String getBreedById(Long id) {
        Optional<String> optionalBreed = Optional.ofNullable(dogRepository.getBreedById(id));
        String breed = optionalBreed.orElseThrow(DogNotFoundException::new);
        return breed;
    }

    @Override
    public List<String> getAllDogNames() {
        return dogRepository.getAllDogNames();
    }


}
