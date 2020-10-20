package com.udacity.jdnd.course3.critter.domain;

import com.udacity.jdnd.course3.critter.pet.PetType;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Pet {
    @Id
    @GeneratedValue
    private Long id;

    @Type(type = "string")
    private PetType type;

    @Nationalized // a pet could have a name from a foreign country or any other name like X Æ A-12 lol.
    private String name;

    LocalDate birthDate;

    @Column(length = 600) // Setting max length up to 600 characters.
    private String notes;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /* Getters and setters to be added after finishing with definition of entity */
}
