package com.udacity.jdnd.course3.critter.domain;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    @Nationalized // a customer could have a name from a foreign country.
    private String name;

    @Column(length = 10) // For US phone number length of 10 characters.
    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    private List<Pet> pets;
}
