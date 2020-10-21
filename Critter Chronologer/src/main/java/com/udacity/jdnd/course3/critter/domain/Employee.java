package com.udacity.jdnd.course3.critter.domain;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized // an employee can have a name from a foreign country.
    private String name;

    @ElementCollection
    @Type(type = "string")
    private Set<EmployeeSkill> skills;

    @ElementCollection
    @Type(type = "string")
    private Set<DayOfWeek> daysAvailable;

    /* Getters and setters to be added after finishing with definition of entity */
}
