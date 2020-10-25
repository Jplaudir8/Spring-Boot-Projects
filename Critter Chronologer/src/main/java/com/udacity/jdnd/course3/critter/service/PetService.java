package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.domain.Pet;

import java.util.List;

public interface PetService {

    List<Pet> getAllPetsByIds(List<Long> petIds);
    Pet getPetById(Long petId);
    Pet savePet(Pet pet);
    List<Pet> getAllPets();
}
