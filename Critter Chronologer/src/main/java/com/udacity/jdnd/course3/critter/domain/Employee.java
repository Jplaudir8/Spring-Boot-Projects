package com.udacity.jdnd.course3.critter.domain;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    @Nationalized // an employee can have a name from a foreign country.
    private String name;

    @Type(type = "string")
    private EmployeeSkill skills;

    @OneToMany(mappedBy = "employee")
    private List<Schedule> schedules;

    /* Getters and setters to be added after finishing with definition of entity */
}
