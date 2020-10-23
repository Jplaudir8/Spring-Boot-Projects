package com.udacity.jdnd.course3.critter.domain;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized // a customer could have a name from a foreign country.
    private String name;

    @Column(length = 10) // For US phone number length of 10 characters.
    private String phoneNumber;

    @Nationalized // They could mention foreign words.
    private String note;

    // It's a good practice to mark the @ManytoOne side as the owning side following the JPA specification, considering that the owning side of the relation tracked by Hibernate is the side of the relation that owns the foreign key in the database.
    // So we set the mappedBy option in Entity Customer since this option marks the Customer class as the inverse side and the Pet class as the owning side.
    @OneToMany(mappedBy = "customer", targetEntity = Pet.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Pet> pets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
