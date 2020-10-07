package com.udacity.DogRestAPI.repository;

import com.udacity.DogRestAPI.entity.Dog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DogRepository extends CrudRepository<Dog, Long> {

    @Query("SELECT d.id, d.breed FROM Dog d")
    List<String> getAllBreeds();

    @Query("SELECT d.id, d.breed FROM Dog d WHERE d.id = :id")
    String getBreedById(Long id);

    @Query("SELECT d.id, d.name FROM Dog d")
    List<String> getAllDogNames();

}
