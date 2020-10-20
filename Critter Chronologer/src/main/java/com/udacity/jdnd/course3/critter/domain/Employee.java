package com.udacity.jdnd.course3.critter.domain;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    @Nationalized // an employee can have a name from a foreign country.
    private String name;

    @Type(type = "string")
    private EmployeeSkill skills;

    /* Getters and setters to be added after finishing with definition of entity */
}
