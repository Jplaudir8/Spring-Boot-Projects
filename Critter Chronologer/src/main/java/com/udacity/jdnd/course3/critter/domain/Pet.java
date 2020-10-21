package com.udacity.jdnd.course3.critter.domain;

import com.udacity.jdnd.course3.critter.pet.PetType;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Pet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "string")
    private PetType type;

    @Nationalized // a pet could have a name from a foreign country or any other name like X Æ A-12 lol.
    private String name;

    LocalDate birthDate;

    @Column(length = 600) // Setting max length up to 600 characters.
    private String notes;

    @ManyToOne(targetEntity = Customer.class)
    private Customer customer;

    /*@OneToMany(mappedBy = "pet")
    private List<Schedule> schedules;*/

    /* Getters and setters to be added after finishing with definition of entity */
}
