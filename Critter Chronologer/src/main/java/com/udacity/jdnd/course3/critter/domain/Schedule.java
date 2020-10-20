package com.udacity.jdnd.course3.critter.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate date;
    private String activities;

    @ManyToMany
    @JoinTable(
            name = "schedule_pet",
            joinColumns = { @JoinColumn(name = "schedule_id") },
            inverseJoinColumns = { @JoinColumn(name = "pet_id") }
    )
    private List<Pet> pets;

    @ManyToMany
    @JoinTable(
            name = "schedule_employee",
            joinColumns = { @JoinColumn(name = "schedule_id") },
            inverseJoinColumns = { @JoinColumn(name = "employee_id") }
    )
    private List<Employee> employees;
}