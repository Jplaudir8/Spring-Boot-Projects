package com.udacity.jdnd.course3.critter.domain;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ElementCollection
    @Type(type = "string")
    private Set<EmployeeSkill> activities;

    /*@ManyToMany
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
    private List<Employee> employees;*/

    @ManyToMany(targetEntity = Employee.class)
    //@JoinColumn(name = "employee_id")
    private List<Employee> employees;

    @ManyToMany(targetEntity = Pet.class)
    //@JoinColumn(name = "pet_id")
    private List<Pet> pets;

    /* Getters and setters to be added after finishing with definition of entity */
}
