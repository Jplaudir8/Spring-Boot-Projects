package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.domain.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetServiceImpl implements PetService {

    @Autowired
    PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public List<Pet> getAllPetsByIds(List<Long> petIds) {
        return petRepository.findAllById(petIds);
    }

    @Override
    public Pet getPetById(Long petId) {

        Optional<Pet> pet = petRepository.findById(petId);

        if (pet.isPresent()) {
            return pet.get();
        } else {
            return null;
        }
    }

    @Override
    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }


}
