package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.domain.Pet;

import java.util.List;

public interface PetService {

    List<Pet> getAllPetsByIds(List<Long> petIds);

}
