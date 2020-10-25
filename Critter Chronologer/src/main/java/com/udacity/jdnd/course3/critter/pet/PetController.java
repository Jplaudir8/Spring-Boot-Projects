package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.domain.Customer;
import com.udacity.jdnd.course3.critter.domain.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerServiceImpl;
import com.udacity.jdnd.course3.critter.service.PetServiceImpl;
import org.hibernate.annotations.Nationalized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetServiceImpl petService;
    CustomerServiceImpl customerService;

    public PetController(PetServiceImpl petService, CustomerServiceImpl customerService) {
        this.petService = petService;
        this.customerService = customerService;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {

        Pet pet = convertPetDTOToPet(petDTO);
        Pet petSaved = petService.savePet(pet);

        Customer customer = customerService.getCustomerById(petDTO.getOwnerId());
        if(customer != null) {
            customer.getPets().add(petSaved);
            customerService.save(customer);
        } else {
            System.out.print("There is no customer associated to this pet");
        }

        petDTO.setId(petSaved.getId());
        return petDTO;
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.getAllPets();
        List<PetDTO> petDTOS = pets.stream().map(this::convertPetToPetDTO).collect(Collectors.toList());
        return petDTOS;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        throw new UnsupportedOperationException();
    }

    /**
     * Helper Method
     * @param petDTO    petDTO object to be converted
     * @return      entity form of pet
     */
    private Pet convertPetDTOToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        pet.setType(petDTO.getType());
        pet.setName(petDTO.getName());
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setNote(petDTO.getNotes());

        Customer customer = customerService.getCustomerById(petDTO.getOwnerId());
        pet.setCustomer(customer);

        return pet;
    }

    private PetDTO convertPetToPetDTO(Pet pet){
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setType(pet.getType());
        petDTO.setName(pet.getName());
        petDTO.setOwnerId(pet.getCustomer().getId());
        petDTO.setBirthDate(pet.getBirthDate());
        petDTO.setNotes(pet.getNote());
        return petDTO;
    }

}
